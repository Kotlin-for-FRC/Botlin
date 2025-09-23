@file:Suppress("unused")

package gay.zharel.botlin.math.units

import edu.wpi.first.units.*
import edu.wpi.first.units.Unit
import edu.wpi.first.units.Units.*
import edu.wpi.first.units.measure.*

/*
 * ORIGINAL SOURCE: Team 78 Air Strike
 * https://github.com/frc78/78-Kotlin-Template/blob/main/src/main/java/frc/robot/lib/Units.kt
 */

// DIMENSIONLESS
inline val Dimensionless.unit
    get() = this.to(BaseUnits.Value)
inline val Dimensionless.percent
    get() = this.to(Percent)

// LENGTH
inline val Distance.meters
    get() = this.to(Meters)
inline val Distance.millimeters
    get() = this.to(Millimeters)
inline val Distance.centimeters
    get() = this.to(Centimeters)
inline val Distance.inches
    get() = this.to(Inches)
inline val Distance.feet
    get() = this.to(Feet)

// TIME
inline val Time.seconds
    get() = this.to(Seconds)
inline val Time.milliseconds
    get() = this.to(Milliseconds)
inline val Time.microseconds
    get() = this.to(Microseconds)
inline val Time.minutes
    get() = this.to(Minutes)

// ANGLE
inline val Angle.radians
    get() = this.to(Radians)
inline val Angle.revolutions
    get() = this.to(Revolutions)
inline val Angle.rotations
    get() = this.to(Rotations)
inline val Angle.degrees
    get() = this.to(Degrees)

// LINEAR VELOCITY
inline val LinearVelocity.metersPerSecond
    get() = this.to(MetersPerSecond)
inline val LinearVelocity.feetPerSecond
    get() = this.to(FeetPerSecond)
inline val LinearVelocity.inchesPerSecond
    get() = this.to(InchesPerSecond)

// ANGULAR VELOCITY
inline val AngularVelocity.revolutionsPerSecond
    get() = this.to(RevolutionsPerSecond)
inline val AngularVelocity.rotationsPerSecond
    get() = this.to(RotationsPerSecond)
inline val AngularVelocity.rpm
    get() = this.to(RPM)
inline val AngularVelocity.radiansPerSecond
    get() = this.to(RadiansPerSecond)
inline val AngularVelocity.degreesPerSecond
    get() = this.to(DegreesPerSecond)

// FREQUENCY
inline val Frequency.hertz
    get() = this.to(Hertz)
inline val Frequency.millihertz
    get() = this.to(Millihertz)

// LINEAR ACCELERATION
inline val LinearAcceleration.metersPerSecondPerSecond
    get() = this.to(MetersPerSecondPerSecond)
inline val LinearAcceleration.feetPerSecondPerSecond
    get() = this.to(FeetPerSecondPerSecond)
inline val LinearAcceleration.inchesPerSecondPerSecond
    get() = this.to(InchesPerSecondPerSecond)
inline val LinearAcceleration.Gs
    get() = this.to(Units.Gs)

// ANGULAR ACCELERATION
inline val AngularAcceleration.rotationsPerSecondPerSecond
    get() = this.to(RotationsPerSecondPerSecond)
inline val AngularAcceleration.radiansPerSecondPerSecond
    get() = this.to(RadiansPerSecondPerSecond)
inline val AngularAcceleration.degreesPerSecondPerSecond
    get() = this.to(DegreesPerSecondPerSecond)

// MASS
inline val Mass.kilograms
    get() = this.to(Kilograms)
inline val Mass.grams
    get() = this.to(Grams)
inline val Mass.pounds
    get() = this.to(Pounds)
inline val Mass.ounces
    get() = this.to(Ounces)

// FORCE
inline val Force.newtons
    get() = this.to(Newtons)
inline val Force.poundsForce
    get() = this.to(PoundsForce)
inline val Force.ouncesForce
    get() = this.to(OuncesForce)

// TORQUE
inline val Torque.newtonMeters
    get() = this.to(NewtonMeters)
inline val Torque.poundFeet
    get() = this.to(PoundFeet)
inline val Torque.poundInches
    get() = this.to(PoundInches)
inline val Torque.ounceInches
    get() = this.to(OunceInches)

// LINEAR MOMENTUM
inline val LinearMomentum.kilogramMetersPerSecond
    get() = this.to(KilogramMetersPerSecond)

// ANGULAR MOMENTUM
inline val AngularMomentum.kilogramMetersSquaredPerSecond
    get() = this.to(KilogramMetersSquaredPerSecond)

// MOMENT OF INERTIA
inline val MomentOfInertia.kilogramSquareMeters
    get() = this.to(KilogramSquareMeters)

// VOLTAGE
inline val Voltage.volts
    get() = this.to(Volts)
inline val Voltage.millivolts
    get() = this.to(Millivolts)

// CURRENT
inline val Current.amps
    get() = this.to(Amps)
inline val Current.milliamps
    get() = this.to(Milliamps)

// RESISTANCE
inline val Resistance.ohms
    get() = this.to(Ohms)
inline val Resistance.kiloOhms
    get() = this.to(KiloOhms)
inline val Resistance.milliOhms
    get() = this.to(MilliOhm)

// ENERGY
inline val Energy.joules
    get() = this.to(Joules)
inline val Energy.kilojoules
    get() = this.to(Kilojoules)
inline val Energy.millijoules
    get() = this.to(Millijoules)

// POWER
inline val Power.watts
    get() = this.to(Watts)
inline val Power.milliwatts
    get() = this.to(Milliwatts)
inline val Power.horsepower
    get() = this.to(Horsepower)

// TEMPERATURE
inline val Temperature.kelvin
    get() = this.to(Kelvin)
inline val Temperature.celsius
    get() = this.to(Celsius)
inline val Temperature.fahrenheit
    get() = this.to(Fahrenheit)

// ALIASES
val percent: DimensionlessUnit = Percent

val meters: DistanceUnit = Meters
val meter: DistanceUnit = Meter
val millimeters: DistanceUnit = Millimeters
val millimeter: DistanceUnit = Millimeter
val mm: DistanceUnit = Millimeters
val centimeters: DistanceUnit = Centimeters
val centimeter: DistanceUnit = Centimeter
val cm: DistanceUnit = Centimeters
val inches: DistanceUnit = Inches
val inch: DistanceUnit = Inch
val feet: DistanceUnit = Feet
val foot: DistanceUnit = Foot

val seconds: TimeUnit = Seconds
val second: TimeUnit = Second
val milliseconds: TimeUnit = Milliseconds
val millisecond: TimeUnit = Millisecond
val microseconds: TimeUnit = Microseconds
val microsecond: TimeUnit = Microsecond
val minutes: TimeUnit = Minutes
val minute: TimeUnit = Minute

val radians: AngleUnit = Radians
val radian: AngleUnit = Radian
val revolutions: AngleUnit = Revolutions
val revolution: AngleUnit = Revolution
val rotations: AngleUnit = Rotations
val rotation: AngleUnit = Rotation
val degrees: AngleUnit = Degrees
val degree: AngleUnit = Degree

val metersPerSecond: LinearVelocityUnit = MetersPerSecond
val mps: LinearVelocityUnit = MetersPerSecond
val feetPerSecond: LinearVelocityUnit = FeetPerSecond
val inchesPerSecond: LinearVelocityUnit = InchesPerSecond

val revolutionsPerSecond: AngularVelocityUnit = RevolutionsPerSecond
val rotationsPerSecond: AngularVelocityUnit = RotationsPerSecond
val rps: AngularVelocityUnit = RotationsPerSecond
val rpm: AngularVelocityUnit = RPM
val radiansPerSecond: AngularVelocityUnit = RadiansPerSecond
val degreesPerSecond: AngularVelocityUnit = DegreesPerSecond

val hertz: FrequencyUnit = Hertz
val millihertz: FrequencyUnit = Millihertz

val metersPerSecondPerSecond: LinearAccelerationUnit = MetersPerSecondPerSecond
val mpsSquared: LinearAccelerationUnit = MetersPerSecondPerSecond
val feetPerSecondPerSecond: LinearAccelerationUnit = FeetPerSecondPerSecond
val inchesPerSecondPerSecond: LinearAccelerationUnit = InchesPerSecondPerSecond

val rotationsPerSecondPerSecond: AngularAccelerationUnit = RotationsPerSecondPerSecond
val rpsSquared: AngularAccelerationUnit = RotationsPerSecondPerSecond
val radiansPerSecondPerSecond: AngularAccelerationUnit = RadiansPerSecondPerSecond
val degreesPerSecondPerSecond: AngularAccelerationUnit = DegreesPerSecondPerSecond

val kilograms: MassUnit = Kilograms
val kilogram: MassUnit = Kilogram
val grams: MassUnit = Grams
val gram: MassUnit = Gram
val pounds: MassUnit = Pounds
val pound: MassUnit = Pound
val ounces: MassUnit = Ounces
val ounce: MassUnit = Ounce

val newtons: ForceUnit = Newtons
val newton: ForceUnit = Newton
val poundsForce: ForceUnit = PoundsForce
val poundForce: ForceUnit = PoundForce
val ouncesForce: ForceUnit = OuncesForce
val ounceForce: ForceUnit = OunceForce

val newtonMeters: TorqueUnit = NewtonMeters
val newtonMeter: TorqueUnit = NewtonMeter
val poundFeet: TorqueUnit = PoundFeet
val poundFoot: TorqueUnit = PoundFoot
val poundInches: TorqueUnit = PoundInches
val poundInch: TorqueUnit = PoundInch
val ounceInches: TorqueUnit = OunceInches
val ounceInch: TorqueUnit = OunceInch

val kilogramMetersPerSecond: LinearMomentumUnit = KilogramMetersPerSecond
val kgMpS: LinearMomentumUnit = KilogramMetersPerSecond

val kilogramMetersSquaredPerSecond: AngularMomentumUnit = KilogramMetersSquaredPerSecond
val kgM2pS: AngularMomentumUnit = KilogramMetersSquaredPerSecond

val kilogramSquareMeters: MomentOfInertiaUnit = KilogramSquareMeters
val kgM2: MomentOfInertiaUnit = KilogramSquareMeters

val volts: VoltageUnit = Volts
val volt: VoltageUnit = Volt
val millivolts: VoltageUnit = Millivolts
val millivolt: VoltageUnit = Millivolt

val amps: CurrentUnit = Amps
val amp: CurrentUnit = Amp
val milliamps: CurrentUnit = Milliamps
val milliamp: CurrentUnit = Milliamp

val ohms: ResistanceUnit = Ohms
val ohm: ResistanceUnit = Ohm
val kiloOhms: ResistanceUnit = KiloOhms
val kiloOhm: ResistanceUnit= KiloOhm
val milliOhms: ResistanceUnit = MilliOhms
val milliOhm: ResistanceUnit = MilliOhm

val joules: EnergyUnit = Joules
val joule: EnergyUnit = Joule
val kilojoules: EnergyUnit = Kilojoules
val kilojoule: EnergyUnit = Kilojoule
val millijoules: EnergyUnit = Millijoules
val millijoule: EnergyUnit = Millijoule

val watts: PowerUnit = Watts
val watt: PowerUnit = Watt
val milliwatts: PowerUnit = Milliwatts
val milliwatt: PowerUnit = Milliwatt
val horsepower: PowerUnit = Horsepower

val kelvin: TemperatureUnit = Kelvin
val celsius: TemperatureUnit = Celsius
val fahrenheit: TemperatureUnit = Fahrenheit

val voltsPerMeterPerSecond: PerUnit<VoltageUnit, LinearVelocityUnit> = VoltsPerMeterPerSecond
val voltsPerMeterPerSecondSquared: PerUnit<VoltageUnit, LinearAccelerationUnit> = VoltsPerMeterPerSecondSquared
val voltsPerRadianPerSecond: PerUnit<VoltageUnit, AngularVelocityUnit> = VoltsPerRadianPerSecond
val voltsPerRadianPerSecondSquare: PerUnit<VoltageUnit, LinearAccelerationUnit> = VoltsPerMeterPerSecondSquared

// UTILITY FUNCTIONS
fun Angle.toDistance(radius: Distance): Distance = radius * this.radians
fun Distance.toAngle(radius: Distance): Angle = Radians.of((this / radius).magnitude())

fun LinearVelocity.toAngularVelocity(radius: Distance): AngularVelocity =
    RadiansPerSecond.of(this.metersPerSecond / radius.meters)
fun AngularVelocity.toLinearVelocity(radius: Distance): LinearVelocity =
    MetersPerSecond.of(this.radiansPerSecond * radius.meters)

operator fun <U: Unit, P: Unit> Measure<U>.times(unit: P): Measure<*> = this * unit.of(1.0)
operator fun <U: Unit, P: Unit> Measure<U>.div(unit: P): Measure<*> = this / unit.of(1.0)
infix fun <U: Unit, P: Unit> Measure<U>.per(unit: P): Measure<*> = this / unit.of(1.0)

operator fun <U: Unit, P: Unit> U.times(unit: P): MultUnit<U, P> =
    MultUnit.combine(this, unit)

operator fun <U: Unit, P: Unit> U.div(unit: P): PerUnit<U, P> =
    PerUnit.combine(this, unit)

infix fun <U: Unit, P: Unit> U.per(unit: P): Unit = this / unit

fun <U: Unit> Number.square(unit: U): Measure<*> = unit.of(this.toDouble()) * unit.of(1.0)
fun <U: Unit> square(unit: U): Unit = (unit.of(1.0) * unit.of(1.0)).unit()

@Suppress("UNCHECKED_CAST")
val <U: Unit> U.squared: MultUnit<U, U> get() = square(this) as MultUnit<U, U>

// more specific utility functions

operator fun Distance.div(unit: TimeUnit): LinearVelocity = this / unit.of(1.0)

operator fun DistanceUnit.div(unit: TimeUnit): LinearVelocityUnit =
    LinearVelocityUnit.combine(this, unit)

infix fun Distance.per(unit: TimeUnit): LinearVelocity = this / unit.of(1.0)

infix fun DistanceUnit.per(unit: TimeUnit): LinearVelocityUnit =
    LinearVelocityUnit.combine(this, unit)

operator fun Distance.div(unit: MultUnit<TimeUnit, TimeUnit>): LinearAcceleration = (this.unit() / unit).of(this.magnitude())

operator fun DistanceUnit.div(unit: MultUnit<TimeUnit, TimeUnit>): LinearAccelerationUnit =
    LinearAccelerationUnit.combine(LinearVelocityUnit.combine(this, unit.unitA()), unit.unitB())

infix fun Distance.per(unit: MultUnit<TimeUnit, TimeUnit>): LinearAcceleration = (this.unit() / unit).of(this.magnitude())

infix fun DistanceUnit.per(unit: MultUnit<TimeUnit, TimeUnit>): LinearAccelerationUnit =
    LinearAccelerationUnit.combine(LinearVelocityUnit.combine(this, unit.unitA()), unit.unitB())

operator fun LinearVelocity.div(unit: TimeUnit): LinearAcceleration = this / unit.of(1.0)

operator fun LinearVelocityUnit.div(unit: TimeUnit): LinearAccelerationUnit =
    LinearAccelerationUnit.combine(this, unit)

infix fun LinearVelocity.per(unit: TimeUnit): LinearAcceleration = this.div(unit.of(1.0))

infix fun LinearVelocityUnit.per(unit: TimeUnit): LinearAccelerationUnit =
    LinearAccelerationUnit.combine(this, unit)