package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume
import kotlin.time.Duration.Companion.seconds

/**
 * A command that wraps a Kotlin coroutine for use in the command-based framework.
 * This allows for sequential, pausable execution using coroutines instead of state machines.
 */
class CoroutineCommand(
    private val action: suspend () -> Unit,
    requirements: List<Subsystem> = emptyList(),
    private val interruptible: Boolean = true
) : Command() {
    private lateinit var continuation: Continuation<Unit>
    private var isFinished = false

    val completion = object : Continuation<Unit> {
        override val context: CoroutineContext = Dispatchers.Main

        override fun resumeWith(result: Result<Unit>) {
            result.onSuccess {
                isFinished = true
            }.onFailure { exception ->
                // Handle coroutine exception
                println("Coroutine failed: ${exception.message}")
                isFinished = true
            }
        }
    }

    init {
        addRequirements(*requirements.toTypedArray())
    }

    override fun getInterruptionBehavior(): InterruptionBehavior {
        return if (interruptible) {
            InterruptionBehavior.kCancelSelf
        } else {
            InterruptionBehavior.kCancelIncoming
        }
    }

    override fun initialize() {
        isFinished = false

        continuation = action.createCoroutine(completion)
        continuation.resume(Unit)
    }

    override fun execute() {
        continuation.resume(Unit)
    }

    override fun isFinished(): Boolean {
        return isFinished
    }

    override fun end(interrupted: Boolean) {
        isFinished = true
    }
}

/**
 * Extension function to create a CoroutineCommand from a suspend function
 */
fun createCoroutineCommand(
    requirements: List<Subsystem> = emptyList(),
    interruptible: Boolean = true,
    block: suspend () -> Unit
): CoroutineCommand {
    return CoroutineCommand(block, requirements, interruptible)
}

val example = createCoroutineCommand {
    val timer = Timer()
    while (timer.get().seconds < 5.0.seconds) {
        println("timer running")
        yield()
    }
}