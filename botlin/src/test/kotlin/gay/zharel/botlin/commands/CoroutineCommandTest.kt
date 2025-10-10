package gay.zharel.botlin.commands

import gay.zharel.botlin.units.seconds
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CoroutineCommandTest : CommandTestBase() {

    @Test
    fun `test CoroutineCommandBuilder + CoroutineCommand`() {

        println("test beginning")

        val myCORCommand by coroutineCommandDelegate(runsWhileDisabled = true) {
            var x = 0
            while(x < 10) {
                x++
                println("myCORCommand: $x")
                wait(0.5.seconds)
            }
            println("Hello, world!")
        }

        val my2ndCORCommand = CoroutineCommand(runsWhileDisabled = true) {
            var x = 0
            while(x < 10) {
                x++
                println("my2ndCORCommand: $x")
                if(x == 5) {
                    await(myCORCommand)
                }
                wait(0.5.seconds)
            }
            println("Hello, world!")
        }

        Scheduler.schedule(my2ndCORCommand)
        println("command scheduled")

        while(Scheduler.isScheduled(my2ndCORCommand)) {
            Scheduler.run()
        }

        println("test ended")

    }

    @Test
    fun `test command scheduler`() {
        val myCommand = CoroutineCommand(runsWhileDisabled = true) {
            yield()
            yield()
            yield()
            println("test")
        }

        Scheduler.schedule(myCommand)

        Scheduler.run()

        assertTrue(Scheduler.isScheduled(myCommand))
    }

    @Test
    fun `test iterations`() {
        val list = mutableListOf<Int>()
        val command = CoroutineCommand(runsWhileDisabled = true) {
            repeat(5) {
                list.add(it)
                yield()
            }
        }

        Scheduler.schedule(command)
        Scheduler.run()
        assertEquals(list, listOf(0))
        Scheduler.run()
        assertEquals(list, listOf(0, 1))
        Scheduler.run()
        assertEquals(list, listOf(0, 1, 2))
        Scheduler.run()
        assertEquals(list, listOf(0, 1, 2, 3))
        Scheduler.run()
        assertEquals(list, listOf(0, 1, 2, 3, 4))
    }
}