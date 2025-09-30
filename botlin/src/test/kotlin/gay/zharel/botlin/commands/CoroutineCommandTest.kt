package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.button.Trigger
import gay.zharel.botlin.triggers.ScopedTrigger
import gay.zharel.botlin.units.milliseconds
import gay.zharel.botlin.units.seconds
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CoroutineCommandTest: CommandTestBase() {

    @Test
    fun `test CoroutineCommandBuilder + CoroutineCommand`() {

        println("test beginning")

        val myCORCommand by coroutineCommandDelegate() {
            var x = 0
            while(x < 10) {
                x++
                println("myCORCommand: $x")
                wait(0.5.seconds)
            }
            println("Hello, world!")
        }

        val my2ndCORCommand = CoroutineCommand {
            var x = 0
            while(x < 10) {
                x++
                println("my2ndCORCommand: $x")
                if(x == 5) {
                    await { myCORCommand }
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

        val myCommand = CoroutineCommand {
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
    fun `test scoped triggers`() {

        var x = 0

        fun myCommand(w: Int) = CoroutineCommand {
            println("triggered ($w)")
        }

        // unbindable
        val myOutsideTrigger = Trigger {
            (x % 3) == 0
        }
        // currently unbound
        // uses global event polling
        val myScopedOutsideTrigger = ScopedTrigger {
            (x % 4) == 0
        }
        val my2ndCommand = CoroutineCommand {
            // automatically bound to this command
            // uses internal event polling
            val myInsideTrigger = ScopedTrigger {
                (x % 2) == 0
            }
            // unbound (will continue after my2ndCommand finishes)
            myOutsideTrigger.onTrue(myCommand(3))
            // bound (will not continue after my2ndCommand finishes)
            myInsideTrigger.onTrue(myCommand(2))
            while(x < 10) {
                println("2nd | x: $x")
                x++
                wait(500.milliseconds)
            }
        }
        // bind myScopedOutsideTrigger to my2ndCommand
        myScopedOutsideTrigger.bind(my2ndCommand)
        myScopedOutsideTrigger.onTrue(myCommand(4))

        val my3rdCommand = CoroutineCommand {
            while(x < 20) {
                println("3rd | x: $x")
                x++
                wait(500.milliseconds)
            }
        }

        Scheduler.schedule(my2ndCommand)

        while(Scheduler.isScheduled(my2ndCommand)) {
            Scheduler.run()
        }

        Scheduler.schedule(my3rdCommand)

        while(Scheduler.isScheduled(my3rdCommand)) {
            Scheduler.run()
        }

    }

}