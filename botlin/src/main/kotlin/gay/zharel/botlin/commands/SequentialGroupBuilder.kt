package gay.zharel.botlin.commands

import edu.wpi.first.units.measure.Time
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.WaitCommand
import edu.wpi.first.wpilibj2.command.WaitUntilCommand
import gay.zharel.botlin.units.seconds

/**
 * Builder for creating a sequence of WPILib commands using a fluent DSL.
 * Supports adding commands, instant actions, waits, and conditional waits.
 *
 * Example usage:
 * ```kotlin
 * val sequence = buildSequence {
 *     runOnce { /* do something */ }
 *     waitSeconds(2.0)
 *     buildCommand {
 *         withExecute { /* custom logic */ }
 *         withIsFinished { /* condition */ }
 *     }
 * }
 * ```
 */
class SequenceBuilder {
    private val commands = mutableListOf<Command>()

    /**
     * Adds a command to the sequence.
     * @param command The command to run.
     */
    fun run(command: Command) {
        commands.add(command)
    }

    /**
     * Adds an InstantCommand that runs the given action once.
     * @param action Lambda to run instantly.
     */
    fun runOnce(action: () -> Unit) = run(InstantCommand(action))

    /**
     * Adds a WaitUntilCommand that blocks until the condition is true.
     * @param condition Lambda returning true when wait should end.
     */
    fun until(condition: () -> Boolean) = commands.add(WaitUntilCommand(condition))

    /**
     * Adds a WaitCommand for a specified time duration.
     * @param time The time to wait.
     */
    fun wait(time: Time) = commands.add(WaitCommand(time))

    /**
     * Adds a WaitCommand for a specified number of seconds.
     * @param seconds The number of seconds to wait.
     */
    fun waitSeconds(seconds: Double) = wait(seconds.seconds)

    /**
     * Adds a FunctionalCommand built using the provided builder lambda.
     * @param init Builder lambda for the FunctionalCommand.
     */
    fun buildCommand(init: FunctionalCommandBuilder.() -> Unit) = run(buildFunctionalCommand(init))

    /**
     * Builds the SequentialCommandGroup containing all added commands.
     * @return The constructed SequentialCommandGroup.
     */
    fun build() = SequentialCommandGroup(*commands.toTypedArray())
}

/**
 * DSL function for building a SequentialCommandGroup using a builder lambda.
 * @param init Builder lambda to configure the sequence.
 * @return The constructed SequentialCommandGroup.
 */
fun buildSequence(init: SequenceBuilder.() -> Unit) = SequenceBuilder().apply(init).build()