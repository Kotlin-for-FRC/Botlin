package gay.zharel.botlin.math.units

import edu.wpi.first.units.BaseUnits
import edu.wpi.first.units.Units
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

// UTILITY FUNCTIONS
fun Angle.toDistance(radius: Distance): Distance = radius * this.radians
fun Distance.toAngle(radius: Distance): Angle = Radians.of((this / radius).magnitude())

fun LinearVelocity.toAngularVelocity(radius: Distance): AngularVelocity =
    RadiansPerSecond.of(this.metersPerSecond / radius.meters)
fun AngularVelocity.toLinearVelocity(radius: Distance): LinearVelocity =
    MetersPerSecond.of(this.radiansPerSecond * radius.meters)