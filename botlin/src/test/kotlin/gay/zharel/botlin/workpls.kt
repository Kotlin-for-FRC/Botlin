package gay.zharel.botlin

import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj2.command.InstantCommand
import org.junit.jupiter.api.Test

class workpls {
    @Test
    fun plswork() {
        val cmd = InstantCommand({ println("pls work") })
        CommandScheduler.getInstance().schedule(cmd)
    }
}