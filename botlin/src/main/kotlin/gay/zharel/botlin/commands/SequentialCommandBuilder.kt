@file:Suppress("unused")

package gay.zharel.botlin.commands

import edu.wpi.first.units.measure.Time
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.Subsystem
import java.util.function.BooleanSupplier
import java.util.function.Supplier
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun buildSequentialCommand(initializer: SequentialCommandChain.() -> Unit): SequentialCommandGroup {
    val chain = SequentialCommandChain()
    chain.initializer()
    return chain.asCommand()
}

class SequentialCommandBuilder(
    initializer: SequentialCommandChain.() -> Unit
): ReadOnlyProperty<Any?, SequentialCommandGroup> {
    private val chain = SequentialCommandChain()
    init {
        chain.initializer()
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): SequentialCommandGroup {
        return chain.asCommand()
    }
}

class SequentialCommandChain {

    var commands: List<Command> = listOf()
    var requirements: Set<Subsystem> = setOf()

    fun requires(vararg requirements: Subsystem) {
        this.requirements = requirements.toSet()
    }

    fun run(action: Runnable) {
        commands += Commands.run(action)
    }

    fun runOnce(action: Runnable) {
        commands += Commands.runOnce(action)
    }

    fun runCommand(command: Command) {
        commands += command
    }

    fun runParallel(vararg commandsToRun: Command) {
        commands += Commands.parallel(*commandsToRun)
    }

    fun runRace(vararg commandsToRun: Command) {
        commands += Commands.race(*commandsToRun)
    }

    fun runWithDeadline(deadline: Command, vararg otherCommands: Command) {
        commands += Commands.deadline(deadline, *otherCommands)
    }
    fun runWithDeadline(deadline: Command, sequence: SequentialCommandChain.() -> Unit) {
        val chain = SequentialCommandChain()
        chain.sequence()
        commands += Commands.deadline(deadline, chain.asCommand())
    }

    fun runWithTimeout(timeout: Time, vararg commandsToRun: Command) {
        commands += Commands.race(Commands.waitTime(timeout), *commandsToRun)
    }
    fun runWithTimeout(timeout: Time, sequence: SequentialCommandChain.() -> Unit) {
        val chain = SequentialCommandChain()
        chain.sequence()
        commands += Commands.race(Commands.waitTime(timeout), chain.asCommand())
    }

    fun runUntil(condition: BooleanSupplier, vararg commandsToRun: Command) {
        commands += Commands.race(Commands.waitUntil(condition), *commandsToRun)
    }
    fun runUntil(condition: BooleanSupplier, sequence: SequentialCommandChain.() -> Unit) {
        val chain = SequentialCommandChain()
        chain.sequence()
        commands += Commands.race(Commands.waitUntil(condition), chain.asCommand())
    }

    fun runWhile(condition: BooleanSupplier, vararg commandsToRun: Command) {
        commands += Commands.race(Commands.waitUntil { !condition.asBoolean }, *commandsToRun)
    }
    fun runWhile(condition: BooleanSupplier, sequence: SequentialCommandChain.() -> Unit) {
        val chain = SequentialCommandChain()
        chain.sequence()
        commands += Commands.race(Commands.waitUntil { !condition.asBoolean }, chain.asCommand())
    }

    fun thenPrint(message: String) {
        commands += Commands.print(message)
    }

    fun waitSeconds(seconds: Double) {
        commands += Commands.waitSeconds(seconds)
    }

    fun waitTime(time: Time) {
        commands += Commands.waitTime(time)
    }

    fun waitUntil(condition: BooleanSupplier) {
        commands += Commands.waitUntil(condition)
    }

    fun asCommand(): SequentialCommandGroup {
        val group = SequentialCommandGroup(*commands.toTypedArray())
        group.addRequirements(*requirements.toTypedArray())
        return group
    }

    fun asCommandSupplier(): Supplier<SequentialCommandGroup> {
        return Supplier<SequentialCommandGroup> {
            val group = SequentialCommandGroup(*commands.toTypedArray())
            group.addRequirements(*requirements.toTypedArray())
            group
        }
    }

}