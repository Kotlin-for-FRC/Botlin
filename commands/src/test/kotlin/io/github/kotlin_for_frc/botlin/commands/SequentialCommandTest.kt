package io.github.kotlin_for_frc.botlin.commands

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SequentialCommandTest: CommandTestBase() {

    @Test
    fun `basic sequential command`() {
        val cmd = buildSequentialCommand {
            thenPrint("hello, world!")
            thenPrint("this is my sequential command!")
            thenPrint("goodbye!")
        }

        Scheduler.schedule(cmd)
        assertTrue(Scheduler.isScheduled(cmd))
        while (cmd.isScheduled) {
            Scheduler.run()
        }
        assertFalse(Scheduler.isScheduled(cmd))
    }

    @Test
    fun `basic delegated sequential command`() {
        val cmd by buildSequentialCommandDelegate {
            thenPrint("hello, world!")
            thenPrint("this is my sequential command!")
            thenPrint("goodbye!")
        }

        val cmdInst = cmd

        Scheduler.schedule(cmdInst)
        assertTrue(Scheduler.isScheduled(cmdInst))
        while (cmdInst.isScheduled) {
            Scheduler.run()
        }
        assertFalse(Scheduler.isScheduled(cmdInst))
    }

    @Test
    fun `sequential command with variable`() {
        val cmd = buildSequentialCommand {
            var x = 0
            repeat(5) {
                runOnce {
                    println(x)
                    x++
                }
            }
            runOnce {
                assertTrue { x == 5 }
            }
        }

        Scheduler.schedule(cmd)
        assertTrue(Scheduler.isScheduled(cmd))
        while (cmd.isScheduled) {
            Scheduler.run()
        }
        assertFalse(Scheduler.isScheduled(cmd))
    }

}