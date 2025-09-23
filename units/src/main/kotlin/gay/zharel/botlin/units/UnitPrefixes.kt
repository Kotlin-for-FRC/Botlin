@file:Suppress("unused", "SpellCheckingInspection")

package gay.zharel.botlin.units

/*
 * ORIGINAL SOURCE: https://github.com/battery-staple/KMeasure/blob/master/src/commonMain/kotlin/units/SIPrefixes.kt
 */

object Prefixes {
    const val QUETTA: Double = 1E30
    const val RONNA: Double = 1E27
    const val YOTTA: Double = 1E24
    const val ZETTA: Double = 1E21
    const val EXA: Double = 1E18
    const val PETA: Double = 1E15
    const val TERA: Double = 1E12
    const val GIGA: Double = 1E9
    const val MEGA: Double = 1E6
    const val KILO: Double = 1E3
    const val HECTO: Double = 1E2
    const val DECA: Double = 1E1
    const val DECI: Double = 1E-1
    const val CENTI: Double = 1E-2
    const val MILLI: Double = 1E-3
    const val MICRO: Double = 1E-6
    const val NANO: Double = 1E-9
    const val PICO: Double = 1E-12
    const val FEMTO: Double = 1E-15
    const val ATTO: Double = 1E-18
    const val ZEPTO: Double = 1E-21
    const val YOCTO: Double = 1E-24
    const val RONTO: Double = 1E-27
    const val QUECTO: Double = 1E-30
}

inline val Number.quetta: Double get() = this.toDouble() * Prefixes.QUETTA
inline val Number.ronna: Double get() = this.toDouble() * Prefixes.RONNA
inline val Number.yotta: Double get() = this.toDouble() * Prefixes.YOTTA
inline val Number.zetta: Double get() = this.toDouble() * Prefixes.ZETTA
inline val Number.exa: Double get() = this.toDouble() * Prefixes.EXA
inline val Number.peta: Double get() = this.toDouble() * Prefixes.PETA
inline val Number.tera: Double get() = this.toDouble() * Prefixes.TERA
inline val Number.giga: Double get() = this.toDouble() * Prefixes.GIGA
inline val Number.mega: Double get() = this.toDouble() * Prefixes.MEGA
inline val Number.kilo: Double get() = this.toDouble() * Prefixes.KILO
inline val Number.hecto: Double get() = this.toDouble() * Prefixes.HECTO
inline val Number.deca: Double get() = this.toDouble() * Prefixes.DECA
inline val Number.deci: Double get() = this.toDouble() * Prefixes.DECI
inline val Number.centi: Double get() = this.toDouble() * Prefixes.CENTI
inline val Number.milli: Double get() = this.toDouble() * Prefixes.MILLI
inline val Number.micro: Double get() = this.toDouble() * Prefixes.MICRO
inline val Number.nano: Double get() = this.toDouble() * Prefixes.NANO
inline val Number.pico: Double get() = this.toDouble() * Prefixes.PICO
inline val Number.femto: Double get() = this.toDouble() * Prefixes.FEMTO
inline val Number.atto: Double get() = this.toDouble() * Prefixes.ATTO
inline val Number.zepto: Double get() = this.toDouble() * Prefixes.ZEPTO
inline val Number.yocto: Double get() = this.toDouble() * Prefixes.YOCTO
inline val Number.ronto: Double get() = this.toDouble() * Prefixes.RONTO
inline val Number.quecto: Double get() = this.toDouble() * Prefixes.QUECTO