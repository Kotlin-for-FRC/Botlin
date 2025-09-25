package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.Subsystem
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LabelCommand(private val label: String) : InstantCommand({ println("Running command: $label") }) {
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

val built = buildFunctionalCommand {
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

class BuilderExampleTest : CommandTestBase() {
    @Test
    fun `test sequence builder`() {
        scheduler.schedule(sequence2)
        assertTrue(scheduler.isScheduled(sequence2))
        while (sequence2.isScheduled) {
            scheduler.run()
        }
    }
}

