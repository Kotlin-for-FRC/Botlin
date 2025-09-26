package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.FunctionalCommand
import edu.wpi.first.wpilibj2.command.Subsystem

/**
 * Builder for creating WPILib FunctionalCommand instances using a fluent DSL.
 * Allows specifying start, execute, end, isFinished logic, and subsystem requirements.
 *
 * Example usage:
 * ```kotlin
 * val command = buildFunctionalCommand {
 *     start { /* setup */ }
 *     execute { /* loop */ }
 *     end { interrupted -> /* cleanup */ }
 *     isFinished { /* condition */ }
 *     requiring(subsystem)
 * }
 * ```
 */
class FunctionalCommandBuilder(
    internal var startAction: () -> Unit = {},
    internal var executeAction: () -> Unit = {},
    internal var endAction: (Boolean) -> Unit = {},
    internal var finished: () -> Boolean = { false },
    internal var requirements: Set<Subsystem> = emptySet()
) {
    /**
     * Sets the start logic for the command.
     * @param start Lambda to run when the command is initialized.
     */
    infix fun start(start: () -> Unit) = apply {
        this.startAction = start
    }

    /**
     * Sets the execute logic for the command.
     * @param execute Lambda to run repeatedly while the command is scheduled.
     */
    infix fun execute(execute: () -> Unit) = apply {
        this.executeAction = execute
    }

    /**
     * Sets the end logic for the command.
     * @param end Lambda to run when the command ends or is interrupted. Receives 'interrupted' flag.
     */
    infix fun end(end: (Boolean) -> Unit) = apply {
        this.endAction = end
    }

    /**
     * Sets the condition for command completion.
     * @param isFinished Lambda returning true when the command should finish.
     */
    infix fun isFinished(isFinished: () -> Boolean) = apply {
        this.finished = isFinished
    }

    /**
     * Adds subsystem requirements to the command.
     * @param requirements Subsystems required by this command.
     */
    fun requiring(vararg requirements: Subsystem) = apply {
        this.requirements += requirements
    }

    /**
     * Adds subsystem requirements to the command.
     * @param requirements Set of subsystems required by this command.
     */
    infix fun requiring(requirements: Set<Subsystem>) = apply {
        this.requirements += requirements
    }

    /**
     * Builds the FunctionalCommand with the specified logic and requirements.
     * @return The constructed Command instance.
     */
    fun build(): Command = FunctionalCommand(
        startAction,
        executeAction,
        endAction,
        finished,
        *requirements.toTypedArray()
    )

    /**
     * Builds and returns the FunctionalCommand. Equivalent to [build()].
     */
    operator fun invoke() = build()
}

/**
 * DSL function for building a FunctionalCommand using a builder lambda.
 * @param init Builder lambda to configure the command.
 * @return The constructed Command instance.
 */
fun buildFunctionalCommand(init: FunctionalCommandBuilder.() -> Unit): Command =
    FunctionalCommandBuilder().apply(init).build()
