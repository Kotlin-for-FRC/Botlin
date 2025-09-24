package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.FunctionalCommand
import edu.wpi.first.wpilibj2.command.Subsystem

infix fun Command.named(name: String): Command = this.withName(name)

class FunctionalCommandBuilder(
    internal var start: () -> Unit = {},
    internal var execute: () -> Unit = {},
    internal var end: (Boolean) -> Unit = {},
    internal var isFinished: () -> Boolean = { false },
    internal var requirements: Set<Subsystem> = emptySet()
) {
    infix fun withStart(start: () -> Unit) = apply {
        this.start = start
    }

    infix fun withExecute(execute: () -> Unit) = apply {
        this.execute = execute
    }

    infix fun withEnd(end: (Boolean) -> Unit) = apply {
        this.end = end
    }

    infix fun withIsFinished(isFinished: () -> Boolean) = apply {
        this.isFinished = isFinished
    }

    fun requiring(vararg requirements: Subsystem) = apply {
        this.requirements += requirements
    }

    infix fun requiring(requirements: Set<Subsystem>) = apply {
        this.requirements += requirements
    }

    fun build(): Command = FunctionalCommand(
        start,
        execute,
        end,
        isFinished,
        *requirements.toTypedArray()
    )

    operator fun invoke() = build()
}

fun buildFunctionalCommand(init: FunctionalCommandBuilder.() -> Unit): Command = FunctionalCommandBuilder().apply(init).build()

