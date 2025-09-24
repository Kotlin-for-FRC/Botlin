package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import java.util.function.Supplier

interface CommandBuilder {

    fun asCommand(): Command

    fun asCommandSupplier(): Supplier<Command>

}

fun builtCommand(builder: CommandBuilder): Command {
    return builder.asCommand()
}

fun builtCommandSupplier(builder: CommandBuilder): Supplier<Command> {
    return builder.asCommandSupplier()
}