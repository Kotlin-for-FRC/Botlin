package com.botlin.lib.units

import edu.wpi.first.units.AngularAccelerationUnit
import edu.wpi.first.units.MomentOfInertiaUnit
import edu.wpi.first.units.Units.*
import edu.wpi.first.units.VoltageUnit
import edu.wpi.first.units.measure.*

/*
 * MAJOR SOURCE: Team 78 Air Strike
 * https://github.com/frc78/78-Kotlin-Template/blob/main/src/main/java/frc/robot/lib/Units.kt
 */

// LENGTH
inline val Number.inches: Distance
    get() = Inches.of(this.toDouble())
inline val Number.meters: Distance
    get() = Meters.of(this.toDouble())
inline val Number.centimeters: Distance
    get() = Centimeters.of(this.toDouble())

// TIME
inline val Number.seconds: Time
    get() = Seconds.of(this.toDouble())

// SPEED
inline val Number.metersPerSecond: LinearVelocity
    get() = MetersPerSecond.of(this.toDouble())
inline val Number.inchesPerSecond: LinearVelocity
    get() = InchesPerSecond.of(this.toDouble())
inline val Number.feetPerSecond: LinearVelocity
    get() = FeetPerSecond.of(this.toDouble())

// ACCELERATION
inline val Number.metersPerSecondPerSecond: LinearAcceleration
    get() = MetersPerSecondPerSecond.of(this.toDouble())

// ANGLES
inline val Number.radians: Angle
    get() = Radians.of(this.toDouble())
inline val Number.degrees: Angle
    get() = Degrees.of(this.toDouble())
inline val Number.rotations: Angle
    get() = Rotations.of(this.toDouble())

// ANGULAR VELOCITY
inline val Number.radiansPerSecond: AngularVelocity
    get() = RadiansPerSecond.of(this.toDouble())
inline val Number.rpm
    get() = RPM.of(this.toDouble())
inline val Number.rotationsPerSecond: AngularVelocity
    get() = RotationsPerSecond.of(this.toDouble())

// ANGULAR ACCELERATION
inline val Number.radiansPerSecondPerSecond: AngularAcceleration
    get() = RadiansPerSecondPerSecond.of(this.toDouble())
inline val Number.rotationsPerSecondPerSecond: AngularAcceleration
    get() = RotationsPerSecondPerSecond.of(this.toDouble())

// ANGULAR JOLT
inline val Number.rotationsPerSecondCubed: Velocity<AngularAccelerationUnit>
    get() = RotationsPerSecondPerSecond.of(this.toDouble()).per(Second)

// MASS
inline val Number.pounds: Mass
    get() = Pounds.of(this.toDouble())

// INERTIA
inline val Number.kilogramSquareMeters: MomentOfInertia
    get() = KilogramSquareMeters.of(this.toDouble())
inline val Number.poundSquareInches: MomentOfInertia
    get() = PoundsSquareInches.of(this.toDouble())
val PoundsSquareInches: MomentOfInertiaUnit =
    Pounds.mult(InchesPerSecond).mult(Inches).mult(RadiansPerSecond)

// ELECTRICITY
inline val Number.volts: Voltage
    get() = Volts.of(this.toDouble())
inline val Number.amps: Current
    get() = Amps.of(this.toDouble())
inline val Number.voltsPerSecond: Velocity<VoltageUnit>
    get() = Volts.of(this.toDouble()).per(Second)