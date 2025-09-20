package gay.zharel.botlin

import edu.wpi.first.units.Units.*
import gay.zharel.botlin.math.units.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UnitExtensions {

    @Test
    fun `test times, div, and per`() {
        val a = 5.Newtons * Meters
        val b = Newtons.of(5.0).times(Meters.of(1.0))
        assertEquals(b, a)

        val c = 5.Kelvin/Second
        val d = 5.Kelvin per Second
        val e = Kelvin.of(5.0).div(Seconds.of(1.0))
        assertEquals(e, c)
        assertEquals(e, d)
    }

    @Test
    fun `test square`() {
        val a = 5.square(Meters)
        val b = Meters.of(5.0).times(Meters.of(1.0))
        assertEquals(5.0, a.magnitude(), 0.0)
        assertEquals(a, b)
    }

    @Test
    fun `test velocity and acceleration`() {
        val a = 5.Meters per Second per Second
        val b = 5.Meters per Second.squared
        val c = 5.MetersPerSecond per Second
        val d = 5.MetersPerSecondPerSecond
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
        val b = (Meters / Second).of(1.0)
        assertEquals(a, b)
    }
}