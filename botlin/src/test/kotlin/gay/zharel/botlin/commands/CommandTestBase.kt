package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.simulation.DriverStationSim
import edu.wpi.first.wpilibj2.command.CommandScheduler
import org.junit.jupiter.api.BeforeEach

abstract class CommandTestBase {
    @BeforeEach
    fun commandSetup() {
        Scheduler.cancelAll()
        Scheduler.enable()
        Scheduler.clearComposedCommands()
        Scheduler.unregisterAllSubsystems()

        setDSEnabled(true)
    }

    fun setDSEnabled(enabled: Boolean) {
        DriverStationSim.setDsAttached(true)

        DriverStationSim.setEnabled(enabled)
        DriverStationSim.notifyNewData()
        while (DriverStation.isEnabled() != enabled) {
            try {
                Thread.sleep(1)
            } catch (exception: InterruptedException) {
                exception.printStackTrace()
            }
        }
    }
}