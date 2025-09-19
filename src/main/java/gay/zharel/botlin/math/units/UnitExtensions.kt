package gay.zharel.botlin.math.units

import edu.wpi.first.units.BaseUnits
import edu.wpi.first.units.DistanceUnit
import edu.wpi.first.units.LinearAccelerationUnit
import edu.wpi.first.units.LinearVelocityUnit
import edu.wpi.first.units.Units
import edu.wpi.first.units.Units.*
import edu.wpi.first.units.Unit
import edu.wpi.first.units.Measure
import edu.wpi.first.units.MultUnit
import edu.wpi.first.units.PerUnit
import edu.wpi.first.units.TimeUnit
import edu.wpi.first.units.measure.*

/*
 * ORIGINAL SOURCE: Team 78 Air Strike
 * https://github.com/frc78/78-Kotlin-Template/blob/main/src/main/java/frc/robot/lib/Units.kt
 */

// DIMENSIONLESS
inline val Dimensionless.Unit
    get() = this.to(BaseUnits.Value)
inline val Dimensionless.Percent
    get() = this.to(Units.Percent)

// LENGTH
inline val Distance.Meters
    get() = this.to(Units.Meters)
inline val Distance.Millimeters
    get() = this.to(Units.Millimeters)
inline val Distance.Centimeters
    get() = this.to(Units.Centimeters)
inline val Distance.Inches
    get() = this.to(Units.Inches)
inline val Distance.Feet
    get() = this.to(Units.Feet)

// TIME
inline val Time.Seconds
    get() = this.to(Units.Seconds)
inline val Time.Milliseconds
    get() = this.to(Units.Milliseconds)
inline val Time.Microseconds
    get() = this.to(Units.Microseconds)
inline val Time.Minutes
    get() = this.to(Units.Minutes)

// ANGLE
inline val Angle.Radians
    get() = this.to(Units.Radians)
inline val Angle.Revolutions
    get() = this.to(Units.Revolutions)
inline val Angle.Rotations
    get() = this.to(Units.Rotations)
inline val Angle.Degrees
    get() = this.to(Units.Degrees)

// LINEAR VELOCITY
inline val LinearVelocity.MetersPerSecond
    get() = this.to(Units.MetersPerSecond)
inline val LinearVelocity.FeetPerSecond
    get() = this.to(Units.FeetPerSecond)
inline val LinearVelocity.InchesPerSecond
    get() = this.to(Units.InchesPerSecond)

// ANGULAR VELOCITY
inline val AngularVelocity.RevolutionsPerSecond
    get() = this.to(Units.RevolutionsPerSecond)
inline val AngularVelocity.RotationsPerSecond
    get() = this.to(Units.RotationsPerSecond)
inline val AngularVelocity.RPM
    get() = this.to(Units.RPM)
inline val AngularVelocity.RadiansPerSecond
    get() = this.to(Units.RadiansPerSecond)
inline val AngularVelocity.DegreesPerSecond
    get() = this.to(Units.DegreesPerSecond)

// FREQUENCY
inline val Frequency.Hertz
    get() = this.to(Units.Hertz)
inline val Frequency.Millihertz
    get() = this.to(Units.Millihertz)

// LINEAR ACCELERATION
inline val LinearAcceleration.MetersPerSecondPerSecond
    get() = this.to(Units.MetersPerSecondPerSecond)
inline val LinearAcceleration.FeetPerSecondPerSecond
    get() = this.to(Units.FeetPerSecondPerSecond)
inline val LinearAcceleration.InchesPerSecondPerSecond
    get() = this.to(Units.InchesPerSecondPerSecond)
inline val LinearAcceleration.Gs
    get() = this.to(Units.Gs)

// ANGULAR ACCELERATION
inline val AngularAcceleration.RotationsPerSecondPerSecond
    get() = this.to(Units.RotationsPerSecondPerSecond)
inline val AngularAcceleration.RadiansPerSecondPerSecond
    get() = this.to(Units.RadiansPerSecondPerSecond)
inline val AngularAcceleration.DegreesPerSecondPerSecond
    get() = this.to(Units.DegreesPerSecondPerSecond)

// MASS
inline val Mass.Kilograms
    get() = this.to(Units.Kilograms)
inline val Mass.Grams
    get() = this.to(Units.Grams)
inline val Mass.Pounds
    get() = this.to(Units.Pounds)
inline val Mass.Ounces
    get() = this.to(Units.Ounces)

// FORCE
inline val Force.Newtons
    get() = this.to(Units.Newtons)
inline val Force.PoundsForce
    get() = this.to(Units.PoundsForce)
inline val Force.OuncesForce
    get() = this.to(Units.OuncesForce)

// TORQUE
inline val Torque.NewtonMeters
    get() = this.to(Units.NewtonMeters)
inline val Torque.PoundFeet
    get() = this.to(Units.PoundFeet)
inline val Torque.PoundInches
    get() = this.to(Units.PoundInches)
inline val Torque.OunceInches
    get() = this.to(Units.OunceInches)

// LINEAR MOMENTUM
inline val LinearMomentum.KilogramMetersPerSecond
    get() = this.to(Units.KilogramMetersPerSecond)

// ANGULAR MOMENTUM
inline val AngularMomentum.KilogramMetersSquaredPerSecond
    get() = this.to(Units.KilogramMetersSquaredPerSecond)

// MOMENT OF INERTIA
inline val MomentOfInertia.KilogramSquareMeters
    get() = this.to(Units.KilogramSquareMeters)

// VOLTAGE
inline val Voltage.Volts
    get() = this.to(Units.Volts)
inline val Voltage.Millivolts
    get() = this.to(Units.Millivolts)

// CURRENT
inline val Current.Amps
    get() = this.to(Units.Amps)
inline val Current.Milliamps
    get() = this.to(Units.Milliamps)

// RESISTANCE
inline val Resistance.Ohms
    get() = this.to(Units.Ohms)
inline val Resistance.KiloOhms
    get() = this.to(Units.KiloOhms)
inline val Resistance.MilliOhms
    get() = this.to(Units.MilliOhm)

// ENERGY
inline val Energy.Joules
    get() = this.to(Units.Joules)
inline val Energy.Kilojoules
    get() = this.to(Units.Kilojoules)
inline val Energy.Millijoules
    get() = this.to(Units.Millijoules)

// POWER
inline val Power.Watts
    get() = this.to(Units.Watts)
inline val Power.Milliwatts
    get() = this.to(Units.Milliwatts)
inline val Power.Horsepower
    get() = this.to(Units.Horsepower)

// TEMPERATURE
inline val Temperature.Kelvin
    get() = this.to(Units.Kelvin)
inline val Temperature.Celsius
    get() = this.to(Units.Celsius)
inline val Temperature.Fahrenheit
    get() = this.to(Units.Fahrenheit)

// UTILITY FUNCTIONS
fun Angle.toDistance(radius: Distance): Distance = radius * this.Radians
fun Distance.toAngle(radius: Distance): Angle = Radians.of((this / radius).magnitude())

fun LinearVelocity.toAngularVelocity(radius: Distance): AngularVelocity =
    RadiansPerSecond.of(this.MetersPerSecond / radius.Meters)
fun AngularVelocity.toLinearVelocity(radius: Distance): LinearVelocity =
    MetersPerSecond.of(this.RadiansPerSecond * radius.Meters)

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