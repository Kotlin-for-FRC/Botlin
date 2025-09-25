@file:Suppress("unused")

package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.FunctionalCommand
import edu.wpi.first.wpilibj2.command.Subsystem
import java.util.function.BooleanSupplier
import java.util.function.Consumer
import java.util.function.Supplier
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun buildFunctionalCommand(initializer: FunctionalCommandChain.() -> Unit): Command {
    val chain = FunctionalCommandChain()
    chain.initializer()
    return chain.asCommand()
}

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

class FunctionalCommandChain {

    var requirements: Set<Subsystem> = setOf()

    var startAction: Runnable = Runnable {}
    var executeAction: Runnable = Runnable {}
    var endAction: Consumer<Boolean> = Consumer<Boolean> {}
    var finishedCondition: BooleanSupplier = BooleanSupplier { true }

    fun requires(vararg requirements: Subsystem) {
        this.requirements = requirements.toSet()
    }

    fun start(action: Runnable) {
        startAction = action
    }

    fun execute(action: Runnable) {
        executeAction = action
    }

    fun end(action: Consumer<Boolean>) {
        endAction = action
    }

    fun isFinished(condition: BooleanSupplier) {
        finishedCondition = condition
    }

    fun asCommand(): FunctionalCommand {
        return FunctionalCommand(
            startAction,
            executeAction,
            endAction,
            finishedCondition,
            *requirements.toTypedArray()
        )
    }

    fun asCommandSupplier(): Supplier<FunctionalCommand> {
        return Supplier<FunctionalCommand> {
            FunctionalCommand(
                startAction,
                executeAction,
                endAction,
                finishedCondition,
                *requirements.toTypedArray()
            )
        }
    }

}