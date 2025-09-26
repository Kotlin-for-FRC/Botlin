package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.Subsystem
import org.junit.jupiter.api.Assertions.assertTrue
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
    start { println("Hello, World!") }
    execute { println("Hello, World!") }
    end { println("Goodbye, World!") }
    isFinished(::someCondition)
    requiring(A, B)
}

val sequence2 = buildSequence {
    run(LabelCommand("First"))
    run(LabelCommand("Second"))
    until { someCondition() }
    buildCommand {
        start { println("Hello, World!") }
        execute { println("Hello, World!") }
        end { println("Goodbye, World!") }
        isFinished(::someCondition)
        requiring(A, B)
    }
}

class BuilderExampleTest : CommandTestBase() {
    @Test
    fun `test sequence builder`() {
        Scheduler.schedule(sequence2)
        assertTrue(Scheduler.isScheduled(sequence2))
        while (sequence2.isScheduled) {
            Scheduler.run()
        }
    }
}

