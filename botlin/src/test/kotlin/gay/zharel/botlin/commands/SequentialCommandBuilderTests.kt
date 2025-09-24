package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Commands
import gay.zharel.botlin.units.milliseconds
import org.junit.jupiter.api.Test

class SequentialCommandBuilderTests {

    @Test
    fun `test SequentialCommandBuilder syntax`() {

        val myTestCommand = Commands.runOnce({ println("test command") })
        val myCommand = builtCommand(
            SequentialCommandBuilder()
                requires setOf()
                runOnce { println("hello, world") }
                runCommand myTestCommand
        )
        val my2ndCommand = builtCommandSupplier(
            SequentialCommandBuilder()
                requires setOf()
                runOnce { println("hello, world pt 2") }
                runCommand myTestCommand
                waitTime 500.milliseconds
                print "print command testing"
        )
    }

}