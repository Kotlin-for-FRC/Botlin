@file:Suppress("unused")

package gay.zharel.botlin.commands

import edu.wpi.first.units.measure.Time
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.event.EventLoop
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj2.command.Subsystem
import edu.wpi.first.wpilibj2.command.button.Trigger
import gay.zharel.botlin.units.seconds
import java.util.function.BooleanSupplier
import java.util.function.Supplier
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.*
import kotlin.coroutines.resume

/**
 * Create a new [CoroutineCommand] delegate
 *
 * @param requirements (optional) The command subsystem requirements
 * @param runsWhileDisabled (optional) A flag to run the command while the robot is disabled
 * @param block The code block used for execution
 */
fun coroutineCommandDelegate(
    requirements: Set<Subsystem> = setOf(),
    runsWhileDisabled: Boolean = false,
    block: suspend CoroutineCommandIteratorScope.() -> Unit
): CommandDelegate {
    return CommandDelegate { CoroutineCommand(requirements, runsWhileDisabled, block) }
}

/**
 * A command that executes in a coroutine-styled fashion.
 * It executes until reaching a `yield()`.
 *
 * To create a delegated CoroutineCommand, use [coroutineCommandDelegate].
 *
 * @param requirements (optional) The command subsystem requirements
 * @param runsWhileDisabled (optional) A flag to run the command while the robot is disabled
 * @param block The code block used for execution
 */
class CoroutineCommand(
    requirements: Set<Subsystem> = setOf(),
    private val runsWhileDisabled: Boolean = false,
    private val block: suspend CoroutineCommandIteratorScope.() -> Unit
): Command() {

    private var coroutine: CoroutineCommandIterator = CoroutineCommandIterator()

    init {
        addRequirements(*requirements.toTypedArray())
    }

    /**
     * (INTERNAL) Command initialization function
     */
    override fun initialize() {
        // create continuation and start executing
        coroutine = CoroutineCommandIterator()
        coroutine.nextStep = block.createCoroutineUnintercepted(coroutine, coroutine)
    }

    /**
     * (INTERNAL) Command execution function
     */
    override fun execute() {
        coroutine.iterate()
    }

    /**
     * (INTERNAL) Command finishing condition
     */
    override fun isFinished(): Boolean = coroutine.finished

    /**
     * (INTERNAL) Command runs when disabled
     */
    override fun runsWhenDisabled(): Boolean = runsWhileDisabled
}

/**
 * (INTERNAL) coroutine state enum
 */
private enum class CoroutineState {
    NOT_READY,
    READY,
    FINISHED,
    FAILED
}

/**
 * (INTERNAL) Functional scope of a coroutine command
 *
 * Gives methods such as `yield`, `waitUntil`, `wait`, and `await`,
 * while allowing other internal iterator functions to remain hidden.
 */
abstract class CoroutineCommandIteratorScope {

    /**
     * Creates a new trigger based on the given condition.
     *
     * Polled by the default local event loop
     *
     * @param condition The condition represented by this trigger
     */
    abstract fun Trigger(condition: BooleanSupplier): Trigger

    /**
     * Yield execution back to the command scheduler.
     */
    abstract suspend fun yield()

    /**
     * Wait until the given condition is true.
     *
     * @param condition The condition to wait for
     */
    suspend fun waitUntil(condition: BooleanSupplier) {
        while(!condition.asBoolean) {
            yield()
        }
    }

    /**
     * Wait while the given condition is true.
     *
     * @param condition The condition to wait during
     */
    suspend fun waitWhile(condition: BooleanSupplier) {
        while(condition.asBoolean) {
            yield()
        }
    }

    /**
     * Wait a set amount of time.
     *
     * @param time The time to wait
     */
    suspend fun wait(time: Time) {
        val timer = Timer()
        val duration = time.seconds
        timer.start()
        while(!timer.hasElapsed(duration)) {
            yield()
        }
    }

    /**
     * Await another command.
     *
     * Schedules the given command, then waits for it to finish execution.
     *
     * @param command The command to await
     */
    suspend fun await(command: Supplier<Command>) {
        val cmd = command.get()
        CommandScheduler.getInstance().schedule(cmd)
        while(!cmd.isFinished) {
            yield()
        }
    }
}

/**
 * (INTERNAL) Coroutine command iterator
 *
 * Essentially a sequence<Unit>
 */
private class CoroutineCommandIterator: CoroutineCommandIteratorScope(), Continuation<Unit> {

    private var state = CoroutineState.NOT_READY
    var nextStep: Continuation<Unit>? = null

    var localEventLoop: EventLoop = EventLoop()

    /**
     * (INTERNAL) perform one iteration
     */
    fun iterate() {
        // poll local event loop
        localEventLoop.poll()

        // until iterator ready
        while(true) {
            when(state) {
                CoroutineState.NOT_READY -> {}
                CoroutineState.READY     -> {
                    // initialize state for next iteration
                    state = CoroutineState.NOT_READY
                    return
                }
                CoroutineState.FINISHED  -> { return }
                CoroutineState.FAILED    -> {
                    throw IllegalStateException("CoroutineCommandIterator has failed!")
                }
            }

            // if state isn't mutated during the next step, something has gone very wrong
            state = CoroutineState.FAILED

            // if nextStep is null, skip to a failed state
            if(nextStep != null) {

                // get non-nullable nextStep
                val step = nextStep!!

                // initialize nextStep for current iteration
                nextStep = null

                // resume coroutine
                step.resume(Unit)
            }
        }
    }

    /**
     * (INTERNAL) Bounded trigger creation
     */
    override fun Trigger(condition: BooleanSupplier): Trigger {
        return Trigger(localEventLoop, condition)
    }

    /**
     * (INTERNAL) Coroutine yield logic
     */
    override suspend fun yield() {
        state = CoroutineState.READY
        return suspendCoroutineUninterceptedOrReturn {
            cont -> nextStep = cont
            COROUTINE_SUSPENDED
        }
    }

    /**
     * (INTERNAL) Coroutine finishing logic
     */
    override fun resumeWith(result: Result<Unit>) {
        state = CoroutineState.FINISHED
    }

    /**
     * (INTERNAL) Coroutine context
     */
    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    /**
     * (INTERNAL) Coroutine finished state
     */
    val finished: Boolean
        get() = state == CoroutineState.FINISHED

}