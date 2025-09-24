package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.Subsystem
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LabelCommand(private val label: String) : InstantCommand() {
    init {
        this.name = label
    }
}

object A : Subsystem
object B : Subsystem
object C : Subsystem

fun someCondition(): Boolean = true

val sequence = buildSequence {
    runOnce { println("Hello, World!") }
    run(LabelCommand("First"))
    run(LabelCommand("Second"))
    until { someCondition() }
}

val built = buildCommand {
    withStart { println("Hello, World!") }
    withExecute { println("Hello, World!") }
    withEnd { println("Goodbye, World!") }
    withIsFinished(::someCondition)
    requiring(A, B)
}

val sequence2 = buildSequence {
    run(LabelCommand("First"))
    run(LabelCommand("Second"))
    until { someCondition() }
    buildCommand {
        withStart { println("Hello, World!") }
        withExecute { println("Hello, World!") }
        withEnd { println("Goodbye, World!") }
        withIsFinished(::someCondition)
        requiring(A, B)
    }
}

class BuilderExampleTest {
    val scheduler get() = CommandScheduler.getInstance()

    @AfterEach
    fun afterEach() {
        scheduler.cancelAll()
    }

    @Test
    fun `test sequence builder`() {
        scheduler.schedule(sequence)
    }
}

