package gay.zharel.botlin.math.units

import edu.wpi.first.units.*
import edu.wpi.first.units.Unit
import edu.wpi.first.units.Units.*
import edu.wpi.first.units.measure.*

/*
 * MAJOR SOURCE: Team 78 Air Strike
 * https://github.com/frc78/78-Kotlin-Template/blob/main/src/main/java/frc/robot/lib/Units.kt
 */

// ANY UNIT
inline fun <U : Unit, reified M : Measure<U>> Number.inUnit(unit: U): M =
    unit.of(this.toDouble()) as M

fun <U : Unit, M : Measure<U>> M.to(unit: U): Double = this.`in`(unit)

// DIMENSIONLESS UNITS
inline val Number.asUnit: Dimensionless
    get() = BaseUnits.Value.of(this.toDouble())
inline val Number.percent: Dimensionless
    get() = Percent.of(this.toDouble())

// LENGTH UNITS
inline val Number.meters: Distance
    get() = Meters.of(this.toDouble())
inline val Number.meter: Distance get() = meters

inline val Number.millimeters: Distance
    get() = Millimeters.of(this.toDouble())
inline val Number.millimeter: Distance get() = millimeters
inline val Number.mm: Distance get() = millimeters

inline val Number.centimeters: Distance
    get() = Centimeters.of(this.toDouble())
inline val Number.centimeter: Distance get() = centimeters
inline val Number.cm: Distance get() = centimeters

inline val Number.inches: Distance
    get() = Inches.of(this.toDouble())
inline val Number.inch: Distance get() = inches

inline val Number.feet: Distance
    get() = Feet.of(this.toDouble())
inline val Number.foot: Distance get() = feet

// TIME UNITS
inline val Number.seconds: Time
    get() = Seconds.of(this.toDouble())
inline val Number.second: Time get() = seconds

inline val Number.milliseconds: Time
    get() = Milliseconds.of(this.toDouble())
inline val Number.millisecond: Time get() = milliseconds
inline val Number.ms: Time get() = milliseconds

inline val Number.microseconds: Time
    get() = Microseconds.of(this.toDouble())
inline val Number.microsecond: Time get() = microseconds

inline val Number.minutes: Time
    get() = Minutes.of(this.toDouble())
inline val Number.minute: Time get() = minutes

// ANGLE UNITS
inline val Number.radians: Angle
    get() = Radians.of(this.toDouble())
inline val Number.radian: Angle get() = radians

inline val Number.revolutions: Angle
    get() = Revolutions.of(this.toDouble())
inline val Number.revolution: Angle get() = revolutions

inline val Number.rotations: Angle
    get() = Rotations.of(this.toDouble())
inline val Number.rotation: Angle get() = rotations

inline val Number.degrees: Angle
    get() = Degrees.of(this.toDouble())
inline val Number.degree: Angle get() = degrees

// VELOCITY UNITS
    // LINEAR
inline val Number.metersPerSecond: LinearVelocity
    get() = MetersPerSecond.of(this.toDouble())
inline val Number.mps: LinearVelocity get() = metersPerSecond

inline val Number.feetPerSecond: LinearVelocity
    get() = FeetPerSecond.of(this.toDouble())

inline val Number.inchesPerSecond: LinearVelocity
    get() = InchesPerSecond.of(this.toDouble())

    // ANGULAR
inline val Number.revolutionsPerSecond: AngularVelocity
    get() = RevolutionsPerSecond.of(this.toDouble())

inline val Number.rotationsPerSecond: AngularVelocity
    get() = RotationsPerSecond.of(this.toDouble())
inline val Number.rps: AngularVelocity get() = rotationsPerSecond

inline val Number.rpm
    get() = RPM.of(this.toDouble())

inline val Number.radiansPerSecond: AngularVelocity
    get() = RadiansPerSecond.of(this.toDouble())

inline val Number.degreesPerSecond: AngularVelocity
    get() = DegreesPerSecond.of(this.toDouble())

// FREQUENCY UNITS
inline val Number.hertz: Frequency
    get() = Hertz.of(this.toDouble())

inline val Number.millihertz: Frequency
    get() = Millihertz.of(this.toDouble())

// ACCELERATION UNITS
    // LINEAR
inline val Number.metersPerSecondPerSecond: LinearAcceleration
    get() = MetersPerSecondPerSecond.of(this.toDouble())
inline val Number.mpsSquared: LinearAcceleration get() = metersPerSecondPerSecond

inline val Number.feetPerSecondPerSecond: LinearAcceleration
    get() = FeetPerSecondPerSecond.of(this.toDouble())

inline val Number.inchesPerSecondPerSecond: LinearAcceleration
    get() = InchesPerSecondPerSecond.of(this.toDouble())

inline val Number.Gs: LinearAcceleration
    get() = Units.Gs.of(this.toDouble())

    // ANGULAR
inline val Number.rotationsPerSecondPerSecond: AngularAcceleration
    get() = RotationsPerSecondPerSecond.of(this.toDouble())
inline val Number.rpsSquared: AngularAcceleration get() = rotationsPerSecondPerSecond

inline val Number.radiansPerSecondPerSecond: AngularAcceleration
    get() = RadiansPerSecondPerSecond.of(this.toDouble())

inline val Number.degreesPerSecondPerSecond: AngularAcceleration
    get() = DegreesPerSecondPerSecond.of(this.toDouble())

// MASS UNITS
inline val Number.kilograms: Mass
    get() = Kilograms.of(this.toDouble())
inline val Number.kilogram: Mass get() = kilograms

inline val Number.grams: Mass
    get() = Grams.of(this.toDouble())
inline val Number.gram: Mass get() = grams

inline val Number.pounds: Mass
    get() = Pounds.of(this.toDouble())
inline val Number.pound: Mass get() = pounds

inline val Number.ounces: Mass
    get() = Ounces.of(this.toDouble())
inline val Number.ounce: Mass get() = ounces

// TODO: REST OF UNITS in `Units.java`