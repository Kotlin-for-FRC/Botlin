package gay.zharel.botlin

import edu.wpi.first.units.Units.*
import gay.zharel.botlin.math.units.*
import org.junit.jupiter.api.Test

infix fun Int.`^`(other: Int): Int = this xor other

class BotlinLibTest {

    @Test
    fun botlinLibTest() {

        println(123.Kelvin per Second)
        println(1.Percent per Minute)

        println(123.Kelvin / Second)
        println(1.Percent / Minute)

        println(123.Watt * Minutes)

        println(12.nano.Meters / Prefixes.NANO.Second)

    }

}