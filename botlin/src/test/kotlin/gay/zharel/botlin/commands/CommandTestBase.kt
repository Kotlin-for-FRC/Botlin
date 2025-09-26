package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.simulation.DriverStationSim
import org.junit.jupiter.api.BeforeEach

open class CommandTestBase {
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

        var i = 0
        while (DriverStation.isEnabled() != enabled && i < 10) {
            try {
                Thread.sleep(1)
            } catch (exception: InterruptedException) {
                exception.printStackTrace()
            }
            i++
        }
    }
}