package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.FunctionalCommand
import edu.wpi.first.wpilibj2.command.Subsystem
import java.util.function.BooleanSupplier
import java.util.function.Consumer
import java.util.function.Supplier

fun buildFunctionalCommand(init: FunctionalCommandBuilder.() -> Unit): Command {
    val builder = FunctionalCommandBuilder()
    builder.init()
    return builder.asCommand()
}
fun buildFunctionalCommandSupplier(init: FunctionalCommandBuilder.() -> Unit): Supplier<Command> {
    val builder = FunctionalCommandBuilder()
    builder.init()
    return builder.asCommandSupplier()
}

class FunctionalCommandBuilder {

    var requirements: Set<Subsystem> = setOf()

    var startAction: Runnable = Runnable {}
    var executeAction: Runnable = Runnable {}
    var endAction: Consumer<Boolean> = Consumer<Boolean> {}
    var finishedCondition: BooleanSupplier = BooleanSupplier { true }

    fun requires(vararg requirements: Subsystem): FunctionalCommandBuilder {
        this.requirements = requirements.toSet()
        return this
    }

    fun start(action: Runnable): FunctionalCommandBuilder {
        startAction = action
        return this
    }

    fun execute(action: Runnable): FunctionalCommandBuilder {
        executeAction = action
        return this
    }

    fun end(action: Consumer<Boolean>): FunctionalCommandBuilder {
        endAction = action
        return this
    }

    fun isFinished(condition: BooleanSupplier): FunctionalCommandBuilder {
        finishedCondition = condition
        return this
    }

    fun asCommand(): Command {
        return FunctionalCommand(
            startAction,
            executeAction,
            endAction,
            finishedCondition,
            *requirements.toTypedArray()
        )
    }

    fun asCommandSupplier(): Supplier<Command> {
        return Supplier<Command> {
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