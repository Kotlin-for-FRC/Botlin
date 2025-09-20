package gay.zharel.botlin.math.units

import edu.wpi.first.units.*
import edu.wpi.first.units.Unit
import edu.wpi.first.units.Units.*
import edu.wpi.first.units.measure.*

/*
 * ORIGINAL SOURCE: Team 78 Air Strike
 * https://github.com/frc78/78-Kotlin-Template/blob/main/src/main/java/frc/robot/lib/Units.kt
 */

// ANY UNIT
inline fun <U : Unit, reified M : Measure<U>> Number.inUnit(unit: U): M =
    unit.of(this.toDouble()) as M

fun <U : Unit, M : Measure<U>> M.to(unit: U): Double = this.`in`(unit)

// DIMENSIONLESS
inline val Number.asUnit: Dimensionless
    get() = BaseUnits.Value.of(this.toDouble())
inline val Number.Percent: Dimensionless
    get() = Units.Percent.of(this.toDouble())

// LENGTH
inline val Number.Meters: Distance
    get() = Units.Meters.of(this.toDouble())
inline val Number.Meter: Distance get() = Meters

inline val Number.Millimeters: Distance
    get() = Units.Millimeters.of(this.toDouble())
inline val Number.Millimeter: Distance get() = Millimeters
inline val Number.mm: Distance get() = Millimeters

inline val Number.Centimeters: Distance
    get() = Units.Centimeters.of(this.toDouble())
inline val Number.Centimeter: Distance get() = Centimeters
inline val Number.cm: Distance get() = Centimeters

inline val Number.Inches: Distance
    get() = Units.Inches.of(this.toDouble())
inline val Number.Inch: Distance get() = Inches

inline val Number.Feet: Distance
    get() = Units.Feet.of(this.toDouble())
inline val Number.Foot: Distance get() = Feet

// TIME
inline val Number.Seconds: Time
    get() = Units.Seconds.of(this.toDouble())
inline val Number.Second: Time get() = Seconds

inline val Number.Milliseconds: Time
    get() = Units.Milliseconds.of(this.toDouble())
inline val Number.Millisecond: Time get() = Milliseconds
inline val Number.ms: Time get() = Milliseconds

inline val Number.Microseconds: Time
    get() = Units.Microseconds.of(this.toDouble())
inline val Number.Microsecond: Time get() = Microseconds

inline val Number.Minutes: Time
    get() = Units.Minutes.of(this.toDouble())
inline val Number.Minute: Time get() = Minutes

// ANGLE
inline val Number.Radians: Angle
    get() = Units.Radians.of(this.toDouble())
inline val Number.Radian: Angle get() = Radians

inline val Number.Revolutions: Angle
    get() = Units.Revolutions.of(this.toDouble())
inline val Number.Revolution: Angle get() = Revolutions

inline val Number.Rotations: Angle
    get() = Units.Rotations.of(this.toDouble())
inline val Number.Rotation: Angle get() = Rotations

inline val Number.Degrees: Angle
    get() = Units.Degrees.of(this.toDouble())
inline val Number.Degree: Angle get() = Degrees

// LINEAR VELOCITY
inline val Number.MetersPerSecond: LinearVelocity
    get() = Units.MetersPerSecond.of(this.toDouble())
inline val Number.mps: LinearVelocity get() = MetersPerSecond

inline val Number.FeetPerSecond: LinearVelocity
    get() = Units.FeetPerSecond.of(this.toDouble())

inline val Number.InchesPerSecond: LinearVelocity
    get() = Units.InchesPerSecond.of(this.toDouble())

// ANGULAR VELOCITY
inline val Number.RevolutionsPerSecond: AngularVelocity
    get() = Units.RevolutionsPerSecond.of(this.toDouble())

inline val Number.RotationsPerSecond: AngularVelocity
    get() = Units.RotationsPerSecond.of(this.toDouble())
inline val Number.rps: AngularVelocity get() = RotationsPerSecond

inline val Number.RPM: AngularVelocity
    get() = Units.RPM.of(this.toDouble())

inline val Number.RadiansPerSecond: AngularVelocity
    get() = Units.RadiansPerSecond.of(this.toDouble())

inline val Number.DegreesPerSecond: AngularVelocity
    get() = Units.DegreesPerSecond.of(this.toDouble())

// FREQUENCY
inline val Number.Hertz: Frequency
    get() = Units.Hertz.of(this.toDouble())

inline val Number.Millihertz: Frequency
    get() = Units.Millihertz.of(this.toDouble())

// LINEAR ACCELERATION
inline val Number.MetersPerSecondPerSecond: LinearAcceleration
    get() = Units.MetersPerSecondPerSecond.of(this.toDouble())
inline val Number.mpsSquared: LinearAcceleration get() = MetersPerSecondPerSecond

inline val Number.FeetPerSecondPerSecond: LinearAcceleration
    get() = Units.FeetPerSecondPerSecond.of(this.toDouble())

inline val Number.InchesPerSecondPerSecond: LinearAcceleration
    get() = Units.InchesPerSecondPerSecond.of(this.toDouble())

inline val Number.Gs: LinearAcceleration
    get() = Units.Gs.of(this.toDouble())

// ANGULAR ACCELERATION
inline val Number.RotationsPerSecondPerSecond: AngularAcceleration
    get() = Units.RotationsPerSecondPerSecond.of(this.toDouble())
inline val Number.rpsSquared: AngularAcceleration get() = RotationsPerSecondPerSecond

inline val Number.RadiansPerSecondPerSecond: AngularAcceleration
    get() = Units.RadiansPerSecondPerSecond.of(this.toDouble())

inline val Number.DegreesPerSecondPerSecond: AngularAcceleration
    get() = Units.DegreesPerSecondPerSecond.of(this.toDouble())

// MASS
inline val Number.Kilograms: Mass
    get() = Units.Kilograms.of(this.toDouble())
inline val Number.Kilogram: Mass get() = Kilograms

inline val Number.Grams: Mass
    get() = Units.Grams.of(this.toDouble())
inline val Number.Gram: Mass get() = Grams

inline val Number.Pounds: Mass
    get() = Units.Pounds.of(this.toDouble())
inline val Number.Pound: Mass get() = Pounds

inline val Number.Ounces: Mass
    get() = Units.Ounces.of(this.toDouble())
inline val Number.Ounce: Mass get() = Ounces

// FORCE
inline val Number.Newtons: Force
    get() = Units.Newtons.of(this.toDouble())
inline val Number.Newton: Force get() = Newtons

inline val Number.PoundsForce: Force
    get() = Units.PoundsForce.of(this.toDouble())
inline val Number.PoundForce: Force get() = PoundsForce

inline val Number.OuncesForce: Force
    get() = Units.OuncesForce.of(this.toDouble())
inline val Number.OunceForce: Force get() = OuncesForce

// TORQUE
inline val Number.NewtonMeters: Torque
    get() = Units.NewtonMeters.of(this.toDouble())
inline val Number.NewtonMeter: Torque get() = NewtonMeters

inline val Number.PoundFeet: Torque
    get() = Units.PoundFeet.of(this.toDouble())
inline val Number.PoundFoot: Torque get() = PoundFeet

inline val Number.PoundInches: Torque
    get() = Units.PoundInches.of(this.toDouble())
inline val Number.PoundInch: Torque get() = PoundInches

inline val Number.OunceInches: Torque
    get() = Units.OunceInches.of(this.toDouble())
inline val Number.OunceInch: Torque get() = OunceInches

// LINEAR MOMENTUM
inline val Number.KilogramMetersPerSecond: LinearMomentum
    get() = Units.KilogramMetersPerSecond.of(this.toDouble())
inline val Number.kgMpS: LinearMomentum get() = KilogramMetersPerSecond

// ANGULAR MOMENTUM
inline val Number.KilogramMetersSquaredPerSecond: AngularMomentum
    get() = Units.KilogramMetersSquaredPerSecond.of(this.toDouble())
inline val Number.kgM2pS: AngularMomentum get() = KilogramMetersSquaredPerSecond

// MOMENT OF INERTIA
inline val Number.KilogramSquareMeters: MomentOfInertia
    get() = Units.KilogramSquareMeters.of(this.toDouble())
inline val Number.kgM2: MomentOfInertia get() = KilogramSquareMeters

// VOLTAGE
inline val Number.Volts: Voltage
    get() = Units.Volts.of(this.toDouble())
inline val Number.Volt: Voltage get() = Volts

inline val Number.Millivolts: Voltage
    get() = Units.Millivolts.of(this.toDouble())
inline val Number.Millivolt: Voltage get() = Millivolts

// CURRENT
inline val Number.Amps: Current
    get() = Units.Amps.of(this.toDouble())
inline val Number.Amp: Current get() = Amps

inline val Number.Milliamps: Current
    get() = Units.Milliamps.of(this.toDouble())
inline val Number.Milliamp: Current get() = Milliamps

// RESISTANCE
inline val Number.Ohms: Resistance
    get() = Units.Ohms.of(this.toDouble())
inline val Number.Ohm: Resistance get() = Ohms

inline val Number.KiloOhms: Resistance
    get() = Units.KiloOhms.of(this.toDouble())
inline val Number.KiloOhm: Resistance get() = KiloOhms

inline val Number.MilliOhms: Resistance
    get() = Units.MilliOhms.of(this.toDouble())
inline val Number.MilliOhm: Resistance get() = MilliOhms

// ENERGY
inline val Number.Joules: Energy
    get() = Units.Joules.of(this.toDouble())
inline val Number.Joule: Energy get() = Joules

inline val Number.Kilojoules: Energy
    get() = Units.Kilojoules.of(this.toDouble())
inline val Number.Kilojoule: Energy get() = Kilojoules

inline val Number.Millijoules: Energy
    get() = Units.Millijoules.of(this.toDouble())
inline val Number.Millijoule: Energy get() = Millijoules

// POWER
inline val Number.Watts: Power
    get() = Units.Watts.of(this.toDouble())
inline val Number.Watt: Power get() = Watts

inline val Number.Milliwatts: Power
    get() = Units.Milliwatts.of(this.toDouble())
inline val Number.Milliwatt: Power get() = Milliwatts

inline val Number.HorsePower: Power
    get() = Units.Horsepower.of(this.toDouble())

// TEMPERATURE
inline val Number.Kelvin: Temperature
    get() = Units.Kelvin.of(this.toDouble())

inline val Number.Celsius: Temperature
    get() = Units.Celsius.of(this.toDouble())

inline val Number.Fahrenheit: Temperature
    get() = Units.Fahrenheit.of(this.toDouble())

// FEEDFORWARD UNITS
inline val Number.VoltsPerMeterPerSecond: Measure<out PerUnit<VoltageUnit, LinearVelocityUnit>>
    get() = Units.VoltsPerMeterPerSecond.of(this.toDouble())

inline val Number.VoltsPerMeterPerSecondSquared: Measure<out PerUnit<VoltageUnit, LinearAccelerationUnit>>
    get() = Units.VoltsPerMeterPerSecondSquared.of(this.toDouble())

inline val Number.VoltsPerRadianPerSecond: Measure<out PerUnit<VoltageUnit, AngularVelocityUnit>>
    get() = Units.VoltsPerRadianPerSecond.of(this.toDouble())

inline val Number.VoltsPerRadianPerSecondSquared: Measure<out PerUnit<VoltageUnit, AngularAccelerationUnit>>
    get() = Units.VoltsPerRadianPerSecondSquared.of(this.toDouble())