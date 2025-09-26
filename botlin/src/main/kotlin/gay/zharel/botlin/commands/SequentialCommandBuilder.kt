@file:Suppress("unused")

package gay.zharel.botlin.commands

import edu.wpi.first.units.measure.Time
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.Subsystem
import java.util.function.BooleanSupplier
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Create a new [SequentialCommandGroup] using a builder.
 *
 * @param initializer The builder body
 */
fun buildSequentialCommand(initializer: SequentialCommandChain.() -> Unit): SequentialCommandGroup {
    val chain = SequentialCommandChain()
    chain.initializer()
    return chain.asCommand()
}

/**
 * Delegated [SequentialCommandGroup] builder.
 *
 * Creates a delegated property that generates a new command each time.
 *
 * @param initializer The builder body
 */
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

/**
 * (INTERNAL) Sequential Command Builder Scope
 */
class SequentialCommandChain {

    private var commands: List<Command> = listOf()
    private var requirements: Set<Subsystem> = setOf()

    /**
     * Add subsystem requirements to the command.
     *
     * @param requirements The subsystems required
     */
    fun requires(vararg requirements: Subsystem) {
        this.requirements += requirements.toSet()
    }

    /**
     * Run an action until interruption.
     *
     * @param action The action to run
     */
    fun run(action: Runnable) {
        commands += Commands.run(action)
    }

    /**
     * Run an action once.
     *
     * @param action The action to run
     */
    fun runOnce(action: Runnable) {
        commands += Commands.runOnce(action)
    }

    /**
     * Run another command.
     *
     * @param command The command to run
     */
    fun runCommand(command: Command) {
        commands += command
    }

    /**
     * Run a group of commands at the same time. Ends once all commands in the group finish.
     *
     * @param commandsToRun The commands to run
     */
    fun runParallel(vararg commandsToRun: Command) {
        commands += Commands.parallel(*commandsToRun)
    }

    /**
     * Run a group of commands at the same time. Ends once any command in the group finishes, and cancels the others.
     *
     * @param commandsToRun The commands to run
     */
    fun runRace(vararg commandsToRun: Command) {
        commands += Commands.race(*commandsToRun)
    }

    /**
     * Run a group of commands at the same time. Ends once a specific command finishes, and cancels the others.
     *
     * @param deadline The deadline command
     * @param otherCommands The other commands to run
     */
    fun runWithDeadline(deadline: Command, vararg otherCommands: Command) {
        commands += Commands.deadline(deadline, *otherCommands)
    }

    /**
     * Run a command sequence that ends once a specific command finishes.
     *
     * @param deadline The deadline command
     * @param sequence The sequence to run
     */
    fun runWithDeadline(deadline: Command, sequence: SequentialCommandChain.() -> Unit) {
        val chain = SequentialCommandChain()
        chain.sequence()
        commands += Commands.deadline(deadline, chain.asCommand())
    }

    /**
     * Run a group of commands at the same time. Ends once the timeout is exceeded.
     *
     * @param timeout The timeout length
     * @param commandsToRun The commands to run
     */
    fun runWithTimeout(timeout: Time, vararg commandsToRun: Command) {
        commands += Commands.race(Commands.waitTime(timeout), *commandsToRun)
    }

    /**
     * Run a command sequence that ends once the timeout is exceeded.
     *
     * @param timeout The timeout length
     * @param sequence The sequence to run
     */
    fun runWithTimeout(timeout: Time, sequence: SequentialCommandChain.() -> Unit) {
        val chain = SequentialCommandChain()
        chain.sequence()
        commands += Commands.race(Commands.waitTime(timeout), chain.asCommand())
    }

    /**
     * Run a group of commands at the same time. Ends once the condition is met.
     *
     * @param condition The timeout condition
     * @param commandsToRun The commands to run
     */
    fun runUntil(condition: BooleanSupplier, vararg commandsToRun: Command) {
        commands += Commands.race(Commands.waitUntil(condition), *commandsToRun)
    }

    /**
     * Run a command sequence that ends once the condition is met.
     *
     * @param condition The timeout condition
     * @param sequence The sequence to run
     */
    fun runUntil(condition: BooleanSupplier, sequence: SequentialCommandChain.() -> Unit) {
        val chain = SequentialCommandChain()
        chain.sequence()
        commands += Commands.race(Commands.waitUntil(condition), chain.asCommand())
    }

    /**
     * Run a group of commands at the same time. Ends once the condition is no longer met.
     *
     * @param condition The execution condition
     * @param commandsToRun The commands to run
     */
    fun runWhile(condition: BooleanSupplier, vararg commandsToRun: Command) {
        commands += Commands.race(Commands.waitUntil { !condition.asBoolean }, *commandsToRun)
    }

    /**
     * Run a command sequence that ends once the condition is no longer met.
     *
     * @param condition The execution condition
     * @param sequence The sequence to run
     */
    fun runWhile(condition: BooleanSupplier, sequence: SequentialCommandChain.() -> Unit) {
        val chain = SequentialCommandChain()
        chain.sequence()
        commands += Commands.race(Commands.waitUntil { !condition.asBoolean }, chain.asCommand())
    }

    /**
     * Print a message to the standard output.
     *
     * @param message The message to print
     */
    fun thenPrint(message: String) {
        commands += Commands.print(message)
    }

    /**
     * Wait an amount of time in seconds.
     *
     * @param seconds The time to wait in seconds
     */
    fun waitSeconds(seconds: Double) {
        commands += Commands.waitSeconds(seconds)
    }

    /**
     * Wait an amount of time.
     *
     * @param time The time to wait
     */
    fun waitTime(time: Time) {
        commands += Commands.waitTime(time)
    }

    /**
     * Wait until a condition is met.
     *
     * @param condition The condition to wait for
     */
    fun waitUntil(condition: BooleanSupplier) {
        commands += Commands.waitUntil(condition)
    }

    /**
     * Wait until a condition is no longer met.
     *
     * @param condition The condition to wait during
     */
    fun waitWhile(condition: BooleanSupplier) {
        commands += Commands.waitUntil { !condition.asBoolean }
    }

    /**
     * (INTERNAL) Get the builder as a [SequentialCommandGroup]
     */
    fun asCommand(): SequentialCommandGroup {
        val group = SequentialCommandGroup(*commands.toTypedArray())
        group.addRequirements(*requirements.toTypedArray())
        return group
    }
}