package gay.zharel.botlin.commands

import edu.wpi.first.util.function.BooleanConsumer
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.FunctionalCommand
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.Subsystem
import java.util.function.BooleanSupplier
import java.util.function.Consumer
import java.util.function.Supplier

class FunctionalCommandBuilder: CommandBuilder {

    var requirements: Set<Subsystem> = setOf()

    var startAction: Runnable = Runnable {}
    var executeAction: Runnable = Runnable {}
    var endAction: Consumer<Boolean> = Consumer<Boolean> {}
    var finishedCondition: BooleanSupplier = BooleanSupplier { true }

    infix fun requires(requirements: Set<Subsystem>): FunctionalCommandBuilder {
        this.requirements = requirements
        return this
    }

    infix fun start(action: Runnable): FunctionalCommandBuilder {
        startAction = action
        return this
    }

    infix fun execute(action: Runnable): FunctionalCommandBuilder {
        executeAction = action
        return this
    }

    infix fun end(action: Consumer<Boolean>): FunctionalCommandBuilder {
        endAction = action
        return this
    }

    infix fun isFinished(condition: BooleanSupplier): FunctionalCommandBuilder {
        finishedCondition = condition
        return this
    }

    override fun asCommand(): Command {
        return FunctionalCommand(
            startAction,
            executeAction,
            endAction,
            finishedCondition,
            *requirements.toTypedArray()
        )
    }

    override fun asCommandSupplier(): Supplier<Command> {
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