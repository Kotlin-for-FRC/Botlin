package gay.zharel.botlin.commands

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestFunctionalBuilder : CommandTestBase() {
    @Test
    fun `basic functional command`() {
        val cmd = buildFunctionalCommand {
            start { println("Starting") }
            execute { println("Executing") }
            end { interrupted -> println("Ending, interrupted: $interrupted") }
            isFinished { true }
        }

        Scheduler.schedule(cmd)
        assertTrue(Scheduler.isScheduled(cmd))
        while (cmd.isScheduled) {
            Scheduler.run()
        }
        assertFalse(Scheduler.isScheduled(cmd))
    }

    @Test
    fun `functional command with variable`() {
        val cmd = buildFunctionalCommand {
            var i = 0
            start { i = 0 }
            execute { println(i++) }
            end { interrupted -> println("Ended with i = $i, interrupted: $interrupted") }
            isFinished { i >= 10 }
        }

        Scheduler.schedule(cmd)
        assertTrue(Scheduler.isScheduled(cmd))
        while (cmd.isScheduled) {
            Scheduler.run()
        }
        assertFalse(Scheduler.isScheduled(cmd))
    }
}