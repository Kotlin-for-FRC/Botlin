package gay.zharel.botlin.commands

import edu.wpi.first.units.Units.*
import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj2.command.Commands
import gay.zharel.botlin.units.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CommandBuilderTests {

    @Test
    fun `test SequentialCommandBuilder syntax`() {

        val myTestCommand = Commands.runOnce({ println("test command") })
        val myCommand = buildSequentialCommand {
            requires()
            runOnce { println("hello, world") }
            runCommand(myTestCommand)
        }
        val my2ndCommand by SequentialCommandBuilder {
            requires()
            runOnce { println("hello, world pt 2") }
            runCommand(myTestCommand)
            waitTime(500.milliseconds)
            thenPrint("print command testing")
        }
        val my3rdCommand by SequentialCommandBuilder {
            requires()

            for(i in 1..5) {
                thenPrint("Hello, world ($i)")
            }

            var x = 0
            runUntil({ x > 500 }) {
                run { x++ }
            }

            runWithTimeout(500.milliseconds) {
                runOnce { x++ }
                thenPrint("$x")
            }
        }
    }

    @Test
    fun `test FunctionalCommandBuilder syntax`() {
        val myCommand = buildFunctionalCommand {
            start { println("Starting") }
            execute { println("Executing") }
            end { interrupted -> println("Ended. Interrupted: $interrupted") }
            isFinished { true }
        }
        val my2ndCommand by FunctionalCommandBuilder {
            start { println("Starting") }
            execute { println("Executing") }
            end { interrupted -> println("Ended. Interrupted: $interrupted") }
            isFinished { true }
        }
    }

    @Test
    fun `test CoroutineCommandBuilder + CoroutineCommand`() {

        println("test beginning")

        val myCORCommand by CoroutineCommandBuilder(runsWhileDisabled = true) {
            var x = 0
            while(x < 10) {
                x++
                println("myCORCommand: $x")
                wait(1.second)
            }
            println("Hello, world!")
        }

        val my2ndCORCommand = CoroutineCommand(runsWhileDisabled = true) {
            var x = 0
            while(x < 10) {
                x++
                println("my2ndCORCommand: $x")
                if(x == 5) {
                    await { myCORCommand }
                }
                wait(1.second)
            }
            println("Hello, world!")
        }

        CommandScheduler.getInstance().schedule(my2ndCORCommand)
        println("command scheduled")

        while(CommandScheduler.getInstance().isScheduled(my2ndCORCommand)) {
            CommandScheduler.getInstance().run()
        }

        println("test ended")

    }

}