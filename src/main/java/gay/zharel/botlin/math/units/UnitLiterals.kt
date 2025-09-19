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

// DIMENSIONLESS
inline val Number.asUnit: Dimensionless
    get() = BaseUnits.Value.of(this.toDouble())
inline val Number.percent: Dimensionless
    get() = Percent.of(this.toDouble())

// LENGTH
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

// TIME
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

// ANGLE
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

// LINEAR VELOCITY
inline val Number.metersPerSecond: LinearVelocity
    get() = MetersPerSecond.of(this.toDouble())
inline val Number.mps: LinearVelocity get() = metersPerSecond

inline val Number.feetPerSecond: LinearVelocity
    get() = FeetPerSecond.of(this.toDouble())

inline val Number.inchesPerSecond: LinearVelocity
    get() = InchesPerSecond.of(this.toDouble())

// ANGULAR VELOCITY
inline val Number.revolutionsPerSecond: AngularVelocity
    get() = RevolutionsPerSecond.of(this.toDouble())

inline val Number.rotationsPerSecond: AngularVelocity
    get() = RotationsPerSecond.of(this.toDouble())
inline val Number.rps: AngularVelocity get() = rotationsPerSecond

inline val Number.rpm: AngularVelocity
    get() = RPM.of(this.toDouble())

inline val Number.radiansPerSecond: AngularVelocity
    get() = RadiansPerSecond.of(this.toDouble())

inline val Number.degreesPerSecond: AngularVelocity
    get() = DegreesPerSecond.of(this.toDouble())

// FREQUENCY
inline val Number.hertz: Frequency
    get() = Hertz.of(this.toDouble())

inline val Number.millihertz: Frequency
    get() = Millihertz.of(this.toDouble())


// LINEAR ACCELERATION
inline val Number.metersPerSecondPerSecond: LinearAcceleration
    get() = MetersPerSecondPerSecond.of(this.toDouble())
inline val Number.mpsSquared: LinearAcceleration get() = metersPerSecondPerSecond

inline val Number.feetPerSecondPerSecond: LinearAcceleration
    get() = FeetPerSecondPerSecond.of(this.toDouble())

inline val Number.inchesPerSecondPerSecond: LinearAcceleration
    get() = InchesPerSecondPerSecond.of(this.toDouble())

inline val Number.Gs: LinearAcceleration
    get() = Units.Gs.of(this.toDouble())

// ANGULAR ACCELERATION
inline val Number.rotationsPerSecondPerSecond: AngularAcceleration
    get() = RotationsPerSecondPerSecond.of(this.toDouble())
inline val Number.rpsSquared: AngularAcceleration get() = rotationsPerSecondPerSecond

inline val Number.radiansPerSecondPerSecond: AngularAcceleration
    get() = RadiansPerSecondPerSecond.of(this.toDouble())

inline val Number.degreesPerSecondPerSecond: AngularAcceleration
    get() = DegreesPerSecondPerSecond.of(this.toDouble())

// MASS
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

// FORCE
inline val Number.newtons: Force
    get() = Newtons.of(this.toDouble())
inline val Number.newton: Force get() = newtons

inline val Number.poundsForce: Force
    get() = PoundsForce.of(this.toDouble())
inline val Number.poundForce: Force get() = poundsForce

inline val Number.ouncesForce: Force
    get() = OuncesForce.of(this.toDouble())
inline val Number.ounceForce: Force get() = ouncesForce

// TORQUE
inline val Number.newtonMeters: Torque
    get() = NewtonMeters.of(this.toDouble())
inline val Number.newtonMeter: Torque get() = newtonMeters

inline val Number.poundFeet: Torque
    get() = PoundFeet.of(this.toDouble())
inline val Number.poundFoot: Torque get() = poundFeet

inline val Number.poundInches: Torque
    get() = PoundInches.of(this.toDouble())
inline val Number.poundInch: Torque get() = poundInches

inline val Number.ounceInches: Torque
    get() = OunceInches.of(this.toDouble())
inline val Number.ounceInch: Torque get() = ounceInches

// LINEAR MOMENTUM
inline val Number.kilogramMetersPerSecond: LinearMomentum
    get() = KilogramMetersPerSecond.of(this.toDouble())
inline val Number.kgMpS: LinearMomentum get() = kilogramMetersPerSecond

// ANGULAR MOMENTUM
inline val Number.kilogramMetersSquaredPerSecond: AngularMomentum
    get() = KilogramMetersSquaredPerSecond.of(this.toDouble())
inline val Number.kgM2pS: AngularMomentum get() = kilogramMetersSquaredPerSecond

// MOMENT OF INERTIA
inline val Number.kilogramSquareMeters: MomentOfInertia
    get() = KilogramSquareMeters.of(this.toDouble())
inline val Number.kgM2: MomentOfInertia get() = kilogramSquareMeters

// VOLTAGE
inline val Number.volts: Voltage
    get() = Volts.of(this.toDouble())
inline val Number.volt: Voltage get() = volts

inline val Number.millivolts: Voltage
    get() = Millivolts.of(this.toDouble())
inline val Number.millivolt: Voltage get() = millivolts

// CURRENT
inline val Number.amps: Current
    get() = Amps.of(this.toDouble())
inline val Number.amp: Current get() = amps

inline val Number.milliamps: Current
    get() = Milliamps.of(this.toDouble())
inline val Number.milliamp: Current get() = milliamps

// RESISTANCE
inline val Number.ohms: Resistance
    get() = Ohms.of(this.toDouble())
inline val Number.ohm: Resistance get() = ohms

inline val Number.kiloOhms: Resistance
    get() = KiloOhms.of(this.toDouble())
inline val Number.kiloOhm: Resistance get() = kiloOhms

inline val Number.milliOhms: Resistance
    get() = MilliOhms.of(this.toDouble())
inline val Number.milliOhm: Resistance get() = milliOhms

// ENERGY
inline val Number.joules: Energy
    get() = Joules.of(this.toDouble())
inline val Number.joule: Energy get() = joules

inline val Number.kilojoules: Energy
    get() = Kilojoules.of(this.toDouble())
inline val Number.kilojoule: Energy get() = kilojoules

inline val Number.millijoules: Energy
    get() = Millijoules.of(this.toDouble())
inline val Number.millijoule: Energy get() = millijoules

// POWER
inline val Number.watts: Power
    get() = Watts.of(this.toDouble())
inline val Number.watt: Power get() = watts

inline val Number.milliwatts: Power
    get() = Milliwatts.of(this.toDouble())
inline val Number.milliwatt: Power get() = milliwatts

inline val Number.horsePower: Power
    get() = Horsepower.of(this.toDouble())

// TEMPERATURE
inline val Number.kelvin: Temperature
    get() = Kelvin.of(this.toDouble())

inline val Number.celsius: Temperature
    get() = Celsius.of(this.toDouble())

inline val Number.fahrenheit: Temperature
    get() = Fahrenheit.of(this.toDouble())

// FEEDFORWARD UNITS
inline val Number.voltsPerMeterPerSecond: Measure<out PerUnit<VoltageUnit, LinearVelocityUnit>>
    get() = VoltsPerMeterPerSecond.of(this.toDouble())

inline val Number.voltsPerMeterPerSecondSquared: Measure<out PerUnit<VoltageUnit, LinearAccelerationUnit>>
    get() = VoltsPerMeterPerSecondSquared.of(this.toDouble())

inline val Number.voltsPerRadianPerSecond: Measure<out PerUnit<VoltageUnit, AngularVelocityUnit>>
    get() = VoltsPerRadianPerSecond.of(this.toDouble())

inline val Number.voltsPerRadianPerSecondSquared: Measure<out PerUnit<VoltageUnit, AngularAccelerationUnit>>
    get() = VoltsPerRadianPerSecondSquared.of(this.toDouble())