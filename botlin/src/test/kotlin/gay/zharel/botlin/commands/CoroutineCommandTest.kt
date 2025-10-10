package gay.zharel.botlin.commands

import gay.zharel.botlin.units.seconds
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CoroutineCommandTest {

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

        Scheduler.cancelAll()
        Scheduler.enable()

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

}