package gay.zharel.botlin.math.units

/*
 * ORIGINAL SOURCE: https://github.com/battery-staple/KMeasure/blob/master/src/commonMain/kotlin/units/SIPrefixes.kt
 */

object Prefixes {
    const val quetta: Double = 1E30
    const val ronna: Double = 1E27
    const val yotta: Double = 1E24
    const val zetta: Double = 1E21
    const val exa: Double = 1E18
    const val peta: Double = 1E15
    const val tera: Double = 1E12
    const val giga: Double = 1E9
    const val mega: Double = 1E6
    const val kilo: Double = 1E3
    const val hecto: Double = 1E2
    const val deca: Double = 1E1
    const val deci: Double = 1E-1
    const val centi: Double = 1E-2
    const val milli: Double = 1E-3
    const val micro: Double = 1E-6
    const val nano: Double = 1E-9
    const val pico: Double = 1E-12
    const val femto: Double = 1E-15
    const val atto: Double = 1E-18
    const val zepto: Double = 1E-21
    const val yocto: Double = 1E-24
    @Suppress("SpellCheckingInspection") const val ronto: Double = 1E-27
    @Suppress("SpellCheckingInspection") const val quecto: Double = 1E-30
}

inline val Number.quetta: Double
    get() = this.toDouble() * Prefixes.quetta
inline val Number.ronna: Double
    get() = this.toDouble() * Prefixes.ronna
inline val Number.yotta: Double
    get() = this.toDouble() * Prefixes.yotta
inline val Number.zetta: Double
    get() = this.toDouble() * Prefixes.zetta
inline val Number.exa: Double
    get() = this.toDouble() * Prefixes.exa
inline val Number.peta: Double
    get() = this.toDouble() * Prefixes.peta
inline val Number.tera: Double
    get() = this.toDouble() * Prefixes.tera
inline val Number.giga: Double
    get() = this.toDouble() * Prefixes.giga
inline val Number.mega: Double
    get() = this.toDouble() * Prefixes.mega
inline val Number.kilo: Double
    get() = this.toDouble() * Prefixes.kilo
inline val Number.hecto: Double
    get() = this.toDouble() * Prefixes.hecto
inline val Number.deca: Double
    get() = this.toDouble() * Prefixes.deca
inline val Number.deci: Double
    get() = this.toDouble() * Prefixes.deci
inline val Number.centi: Double
    get() = this.toDouble() * Prefixes.centi
inline val Number.milli: Double
    get() = this.toDouble() * Prefixes.milli
inline val Number.micro: Double
    get() = this.toDouble() * Prefixes.micro
inline val Number.nano: Double
    get() = this.toDouble() * Prefixes.nano
inline val Number.pico: Double
    get() = this.toDouble() * Prefixes.pico
inline val Number.femto: Double
    get() = this.toDouble() * Prefixes.femto
inline val Number.atto: Double
    get() = this.toDouble() * Prefixes.atto
inline val Number.zepto: Double
    get() = this.toDouble() * Prefixes.zepto
inline val Number.yocto: Double
    get() = this.toDouble() * Prefixes.yocto
@Suppress("SpellCheckingInspection")
inline val Number.ronto: Double
    get() = this.toDouble() * Prefixes.ronto
@Suppress("SpellCheckingInspection")
inline val Number.quecto: Double
    get() = this.toDouble() * Prefixes.quecto
