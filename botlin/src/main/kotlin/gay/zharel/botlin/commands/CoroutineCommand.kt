package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import edu.wpi.first.wpilibj2.command.SubsystemBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

/**
 * A command that wraps a Kotlin coroutine for use in the command-based framework.
 * This allows for sequential, pausable execution using coroutines instead of state machines.
 */
class CoroutineCommand(
    private val action: suspend () -> Unit,
    requirements: List<Subsystem> = emptyList(),
    private val interruptible: Boolean = true
) : Command() {
    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private lateinit var job: Job
    private var isFinished = false

    val completion = object : Continuation<Unit> {
        override val context: CoroutineContext = scope.coroutineContext

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

        val continuation = action.createCoroutine(completion)
        job = scope.launch {
            continuation.resume(Unit)
        }
    }

    override fun execute() {
        // The coroutine execution is handled by the continuation
        // This method is called every scheduler run, but the actual
        // coroutine progression happens through yields
    }

    override fun isFinished(): Boolean {
        return isFinished
    }

    override fun end(interrupted: Boolean) {
        job.cancel()
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

class FakeMotor() {
    val currentPosition: Double = 0.0
    val currentVelocity: Double = 0.0

    fun setVoltage(voltage: Double) {}
}

class FakePIDController() {
    var setpoint: Double = 0.0

    fun calculate(currentPosition: Double): Double = 0.0
    fun atTolerance(currentPosition: Double, tolerance: Double) = false
}

object MySubsystem : SubsystemBase() {
    val motor = FakeMotor()
    val pid = FakePIDController()

    fun goToTarget(position: Double) = createCoroutineCommand {
        pid.setpoint = position
        while (!pid.atTolerance(motor.currentPosition, 10.0)) {
            motor.setVoltage(pid.calculate(motor.currentPosition))
            yield()
        }
        motor.setVoltage(0.0)
    }
}

