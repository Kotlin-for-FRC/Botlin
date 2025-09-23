package gay.zharel.botlin

import edu.wpi.first.units.Units.*
import gay.zharel.botlin.math.units.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UnitExtensions {

    @Test
    fun `test times, div, and per`() {
        val a = 5.newtons * meters
        val b = Newtons.of(5.0).times(Meters.of(1.0))
        assertEquals(b, a)

        val c = 5.kelvin/second
        val d = 5.kelvin per second
        val e = Kelvin.of(5.0).div(Seconds.of(1.0))
        assertEquals(e, c)
        assertEquals(e, d)
    }

    @Test
    fun `test square`() {
        val a = 5.square(meters)
        val b = Meters.of(5.0).times(Meters.of(1.0))
        assertEquals(5.0, a.magnitude(), 0.0)
        assertEquals(a, b)
    }

    @Test
    fun `test velocity and acceleration`() {
        val a = 5.meters per second per second
        val b = 5.meters per second.squared
        val c = 5.metersPerSecond per second
        val d = 5.metersPerSecondPerSecond
        assertEquals(5.0, a.magnitude(), 0.0)
        assertEquals(5.0, b.magnitude(), 0.0)
        assertEquals(5.0, c.magnitude(), 0.0)
        assertEquals(5.0, d.magnitude(), 0.0)
        assertEquals(d, a)
        assertEquals(d, b)
        assertEquals(d, c)
    }

    @Test
    fun `test actual unit math`() {
        val a = MetersPerSecond.of(1.0)
        val b = (meters / second).of(1.0)
        assertEquals(a, b)
    }
}