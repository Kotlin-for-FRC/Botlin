package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Commands
import gay.zharel.botlin.units.milliseconds
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

}