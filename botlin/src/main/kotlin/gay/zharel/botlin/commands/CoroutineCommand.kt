@file:Suppress("unused")

package gay.zharel.botlin.commands

import edu.wpi.first.units.measure.Time
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import gay.zharel.botlin.commands.CoroutineCommandIterator
import gay.zharel.botlin.units.seconds
import java.util.function.BooleanSupplier
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.*
import kotlin.coroutines.resume

class CoroutineCommand(
    requirements: Set<Subsystem> = setOf(),
    private val runsWhileDisabled: Boolean = false,
    private val block: suspend CoroutineCommandIteratorScope.() -> Unit
): Command() {

    private var coroutine: CoroutineCommandIterator = CoroutineCommandIterator()

    init {
        println("CoroutineCommand created")
        addRequirements(*requirements.toTypedArray())
    }

    override fun initialize() {
        println("CoroutineCommand initialized")
        // create continuation and start executing
        coroutine = CoroutineCommandIterator()
        coroutine.nextStep = block.createCoroutineUnintercepted(coroutine, coroutine)
    }

    override fun execute() = coroutine.iterate()

    override fun isFinished(): Boolean = coroutine.finished

    override fun runsWhenDisabled(): Boolean = runsWhileDisabled
}

private enum class CoroutineState {
    NOT_READY,
    READY,
    FINISHED,
    FAILED
}

abstract class CoroutineCommandIteratorScope {
    abstract suspend fun yield()

    suspend fun waitUntil(condition: BooleanSupplier) {
        while(!condition.asBoolean) {
            yield()
        }
    }

    suspend fun wait(time: Time) {
        val timer = Timer()
        val duration = time.seconds
        timer.start()
        while(!timer.hasElapsed(duration)) {
            yield()
        }
    }
}

private class CoroutineCommandIterator: CoroutineCommandIteratorScope(), Continuation<Unit> {

    private var state = CoroutineState.NOT_READY
    var nextStep: Continuation<Unit>? = null

    fun iterate() {
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

    override suspend fun yield() {
        state = CoroutineState.READY
        return suspendCoroutineUninterceptedOrReturn {
            cont -> nextStep = cont
            COROUTINE_SUSPENDED
        }
    }

    override fun resumeWith(result: Result<Unit>) {
        state = CoroutineState.FINISHED
    }

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    val finished: Boolean
        get() = state == CoroutineState.FINISHED

}