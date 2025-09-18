package gay.zharel.botlin.math.units

/*
 * ORIGINAL SOURCE: https://github.com/battery-staple/KMeasure/blob/master/src/commonMain/kotlin/units/SIPrefixes.kt
 */

inline val quetta: Double get() = 1E30
inline val ronna: Double get() = 1E27
inline val yotta: Double get() = 1E24
inline val zetta: Double get() = 1E21
inline val exa: Double get() = 1E18
inline val peta: Double get() = 1E15
inline val tera: Double get() = 1E12
inline val giga: Double get() = 1E9
inline val mega: Double get() = 1E6
inline val kilo: Double get() = 1E3
inline val hecto: Double get() = 1E2
inline val deca: Double get() = 1E1
inline val deci: Double get() = 1E-1
inline val centi: Double get() = 1E-2
inline val milli: Double get() = 1E-3
inline val micro: Double get() = 1E-6
inline val nano: Double get() = 1E-9
inline val pico: Double get() = 1E-12
inline val femto: Double get() = 1E-15
inline val atto: Double get() = 1E-18
inline val zepto: Double get() = 1E-21
inline val yocto: Double get() = 1E-24
@Suppress("SpellCheckingInspection")
inline val ronto: Double get() = 1E-27
@Suppress("SpellCheckingInspection")
inline val quecto: Double get() = 1E-30

inline val Number.quetta: Double get() = this.toDouble() * quetta
inline val Number.ronna: Double get() = this.toDouble() * ronna
inline val Number.yotta: Double get() = this.toDouble() * yotta
inline val Number.zetta: Double get() = this.toDouble() * zetta
inline val Number.exa: Double get() = this.toDouble() * exa
inline val Number.peta: Double get() = this.toDouble() * peta
inline val Number.tera: Double get() = this.toDouble() * tera
inline val Number.giga: Double get() = this.toDouble() * giga
inline val Number.mega: Double get() = this.toDouble() * mega
inline val Number.kilo: Double get() = this.toDouble() * kilo
inline val Number.hecto: Double get() = this.toDouble() * hecto
inline val Number.deca: Double get() = this.toDouble() * deca
inline val Number.deci: Double get() = this.toDouble() * deci
inline val Number.centi: Double get() = this.toDouble() * centi
inline val Number.milli: Double get() = this.toDouble() * milli
inline val Number.micro: Double get() = this.toDouble() * micro
inline val Number.nano: Double get() = this.toDouble() * nano
inline val Number.pico: Double get() = this.toDouble() * pico
inline val Number.femto: Double get() = this.toDouble() * femto
inline val Number.atto: Double get() = this.toDouble() * atto
inline val Number.zepto: Double get() = this.toDouble() * zepto
inline val Number.yocto: Double get() = this.toDouble() * yocto
@Suppress("SpellCheckingInspection")
inline val Number.ronto: Double get() = this.toDouble() * ronto
@Suppress("SpellCheckingInspection")
inline val Number.quecto: Double get() = this.toDouble() * quecto