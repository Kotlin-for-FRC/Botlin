@file:Suppress("unused")

package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.FunctionalCommand
import edu.wpi.first.wpilibj2.command.Subsystem
import java.util.function.BooleanSupplier
import java.util.function.Consumer
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Create a new [FunctionalCommand] using a builder.
 *
 * @param initializer The builder body
 */
fun buildFunctionalCommand(initializer: FunctionalCommandChain.() -> Unit): Command {
    val chain = FunctionalCommandChain()
    chain.initializer()
    return chain.asCommand()
}

/**
 * Delegated [FunctionalCommand] builder.
 *
 * Creates a delegated property that generates a new command each time.
 *
 * @param initializer The builder body
 */
class FunctionalCommandBuilder(
    initializer: FunctionalCommandChain.() -> Unit
): ReadOnlyProperty<Any?, FunctionalCommand> {

    private val chain = FunctionalCommandChain()
    init {
        chain.initializer()
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): FunctionalCommand {
        return chain.asCommand()
    }

}

/**
 * (INTERNAL) Function Command Builder Scope
 */
class FunctionalCommandChain {

    private var requirements: Set<Subsystem> = setOf()

    private var startAction: Runnable = Runnable {}
    private var executeAction: Runnable = Runnable {}
    private var endAction: Consumer<Boolean> = Consumer<Boolean> {}
    private var finishedCondition: BooleanSupplier = BooleanSupplier { true }

    /**
     * Add subsystem requirements to the command.
     *
     * @param requirements The subsystems required
     */
    fun requires(vararg requirements: Subsystem) {
        this.requirements += requirements.toSet()
    }

    /**
     * Set the start action of the command.
     *
     * @param action The start action
     */
    fun start(action: Runnable) {
        startAction = action
    }

    /**
     * Set the execute action of the command.
     *
     * @param action The execute action
     */
    fun execute(action: Runnable) {
        executeAction = action
    }

    /**
     * Set the end action of the command.
     *
     * @param action The end action
     */
    fun end(action: Consumer<Boolean>) {
        endAction = action
    }

    /**
     * Set the finishing condition of the command.
     *
     * @param condition The finishing condition
     */
    fun isFinished(condition: BooleanSupplier) {
        finishedCondition = condition
    }

    /**
     * (INTERNAL) Get the builder as a [FunctionalCommand]
     */
    fun asCommand(): FunctionalCommand {
        return FunctionalCommand(
            startAction,
            executeAction,
            endAction,
            finishedCondition,
            *requirements.toTypedArray()
        )
    }
}