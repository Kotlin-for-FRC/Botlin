package gay.zharel.botlin.commands

import edu.wpi.first.units.measure.Time
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.Subsystem
import java.util.function.BooleanSupplier
import java.util.function.Supplier

class SequentialCommandBuilder: CommandBuilder {

    var commands: List<Command> = listOf()
    var requirements: Set<Subsystem> = setOf()

    infix fun requires(requirements: Set<Subsystem>): SequentialCommandBuilder {
        this.requirements = requirements
        return this
    }

    infix fun runOnce(action: Runnable): SequentialCommandBuilder {
        commands += Commands.runOnce(action)
        return this
    }

    infix fun runCommand(command: Command): SequentialCommandBuilder {
        commands += command
        return this
    }

    infix fun runParallel(commandSet: Set<Command>): SequentialCommandBuilder {
        commands += Commands.parallel(*commandSet.toTypedArray())
        return this
    }

    infix fun runRace(commandSet: Set<Command>): SequentialCommandBuilder {
        commands += Commands.race(*commandSet.toTypedArray())
        return this
    }

    infix fun print(message: String): SequentialCommandBuilder {
        commands += Commands.print(message)
        return this
    }

    infix fun waitSeconds(seconds: Double): SequentialCommandBuilder {
        commands += Commands.waitSeconds(seconds)
        return this
    }

    infix fun waitTime(time: Time): SequentialCommandBuilder {
        commands += Commands.waitTime(time)
        return this
    }

    infix fun waitUntil(condition: BooleanSupplier): SequentialCommandBuilder {
        commands += Commands.waitUntil(condition)
        return this
    }

    override fun asCommand(): Command {
        val group = SequentialCommandGroup(*commands.toTypedArray())
        group.addRequirements(*requirements.toTypedArray())
        return group
    }

    override fun asCommandSupplier(): Supplier<Command> {
        return Supplier<Command> {
            val group = SequentialCommandGroup(*commands.toTypedArray())
            group.addRequirements(*requirements.toTypedArray())
            group
        }
    }

}