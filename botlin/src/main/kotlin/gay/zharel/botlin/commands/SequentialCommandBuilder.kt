package gay.zharel.botlin.commands

import edu.wpi.first.units.measure.Time
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.Subsystem
import java.util.function.BooleanSupplier
import java.util.function.Supplier

fun buildSequentialCommand(init: SequentialCommandBuilder.() -> Unit): Command {
    val builder = SequentialCommandBuilder()
    builder.init()
    return builder.asCommand()
}
fun buildSequentialCommandSupplier(init: SequentialCommandBuilder.() -> Unit): Supplier<Command> {
    val builder = SequentialCommandBuilder()
    builder.init()
    return builder.asCommandSupplier()
}

class SequentialCommandBuilder {

    var commands: List<Command> = listOf()
    var requirements: Set<Subsystem> = setOf()

    fun requires(vararg requirements: Subsystem): SequentialCommandBuilder {
        this.requirements = requirements.toSet()
        return this
    }

    fun runOnce(action: Runnable): SequentialCommandBuilder {
        commands += Commands.runOnce(action)
        return this
    }

    fun runCommand(command: Command): SequentialCommandBuilder {
        commands += command
        return this
    }

    fun runParallel(commandSet: Set<Command>): SequentialCommandBuilder {
        commands += Commands.parallel(*commandSet.toTypedArray())
        return this
    }

    fun runRace(commandSet: Set<Command>): SequentialCommandBuilder {
        commands += Commands.race(*commandSet.toTypedArray())
        return this
    }

    fun print(message: String): SequentialCommandBuilder {
        commands += Commands.print(message)
        return this
    }

    fun waitSeconds(seconds: Double): SequentialCommandBuilder {
        commands += Commands.waitSeconds(seconds)
        return this
    }

    fun waitTime(time: Time): SequentialCommandBuilder {
        commands += Commands.waitTime(time)
        return this
    }

    fun waitUntil(condition: BooleanSupplier): SequentialCommandBuilder {
        commands += Commands.waitUntil(condition)
        return this
    }

    fun asCommand(): Command {
        val group = SequentialCommandGroup(*commands.toTypedArray())
        group.addRequirements(*requirements.toTypedArray())
        return group
    }

    fun asCommandSupplier(): Supplier<Command> {
        return Supplier<Command> {
            val group = SequentialCommandGroup(*commands.toTypedArray())
            group.addRequirements(*requirements.toTypedArray())
            group
        }
    }

}