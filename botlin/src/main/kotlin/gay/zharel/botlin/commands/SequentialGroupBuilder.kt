package gay.zharel.botlin.commands

import edu.wpi.first.units.measure.Time
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.WaitCommand
import edu.wpi.first.wpilibj2.command.WaitUntilCommand
import gay.zharel.botlin.units.seconds

class SequenceBuilder {
    private val commands = mutableListOf<Command>()

    fun run(command: Command) {
        commands.add(command)
    }

    fun runOnce(action: () -> Unit) = run(InstantCommand(action))


    fun until(condition: () -> Boolean) = commands.add(WaitUntilCommand(condition))


    fun wait(time: Time) = commands.add(WaitCommand(time))
    fun waitSeconds(seconds: Double) = wait(seconds.seconds)

    fun buildCommand(init: FunctionalCommandBuilder.() -> Unit) = run(buildFunctionalCommand(init))

    fun build() = SequentialCommandGroup(*commands.toTypedArray())
}

fun buildSequence(init: SequenceBuilder.() -> Unit) = SequenceBuilder().apply(init).build()