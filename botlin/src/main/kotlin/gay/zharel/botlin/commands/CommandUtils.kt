package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * More convenient access to the CommandScheduler singleton.
 */
inline val Scheduler: CommandScheduler get() = CommandScheduler.getInstance()

/**
 * Operator function to schedule a command.
 */
operator fun Command.invoke() = Scheduler.schedule(this)

/**
 * Infix function to set the name of a command.
 */
infix fun Command.named(name: String): Command = this.withName(name)

/**
 * Property delegate to initialize a new command instance on every access.
 */
class CommandSupplier(private val supplier: () -> Command) : ReadOnlyProperty<Any?, Command> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Command = supplier()
}


