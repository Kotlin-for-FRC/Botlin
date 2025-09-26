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

fun buildFunctionalCommand(initializer: FunctionalCommandBuilderScope.() -> Unit): Command {
    val chain = FunctionalCommandBuilderScope()
    chain.initializer()
    return chain.asCommand()
}

class FunctionalCommandBuilder(
    initializer: FunctionalCommandBuilderScope.() -> Unit
): ReadOnlyProperty<Any?, FunctionalCommand> {

    private val chain = FunctionalCommandBuilderScope()
    init {
        chain.initializer()
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): FunctionalCommand {
        return chain.asCommand()
    }

}

class FunctionalCommandBuilderScope {

    var requirements: Set<Subsystem> = setOf()

    var startAction: Runnable = Runnable {}
    var executeAction: Runnable = Runnable {}
    var endAction: Consumer<Boolean> = Consumer<Boolean> {}
    var finishedCondition: BooleanSupplier = BooleanSupplier { true }

    fun requires(vararg requirements: Subsystem): FunctionalCommandBuilderScope {
        this.requirements = requirements.toSet()
        return this
    }

    fun start(action: Runnable): FunctionalCommandBuilderScope {
        startAction = action
        return this
    }

    fun execute(action: Runnable): FunctionalCommandBuilderScope {
        executeAction = action
        return this
    }

    fun end(action: Consumer<Boolean>): FunctionalCommandBuilderScope {
        endAction = action
        return this
    }

    fun isFinished(condition: BooleanSupplier): FunctionalCommandBuilderScope {
        finishedCondition = condition
        return this
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