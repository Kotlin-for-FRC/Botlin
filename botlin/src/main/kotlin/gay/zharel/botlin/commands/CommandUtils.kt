package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler

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



