package com.botlin.lib.units

import edu.wpi.first.units.Units.Amps
import edu.wpi.first.units.Units.Centimeters
import edu.wpi.first.units.Units.Degrees
import edu.wpi.first.units.Units.Inches
import edu.wpi.first.units.Units.KilogramSquareMeters
import edu.wpi.first.units.Units.Kilograms
import edu.wpi.first.units.Units.Meters
import edu.wpi.first.units.Units.MetersPerSecond
import edu.wpi.first.units.Units.RPM
import edu.wpi.first.units.Units.Radians
import edu.wpi.first.units.Units.RadiansPerSecond
import edu.wpi.first.units.Units.RadiansPerSecondPerSecond
import edu.wpi.first.units.Units.Rotations
import edu.wpi.first.units.Units.RotationsPerSecond
import edu.wpi.first.units.Units.Volts
import edu.wpi.first.units.measure.Angle
import edu.wpi.first.units.measure.AngularAcceleration
import edu.wpi.first.units.measure.AngularVelocity
import edu.wpi.first.units.measure.Current
import edu.wpi.first.units.measure.Distance
import edu.wpi.first.units.measure.LinearVelocity
import edu.wpi.first.units.measure.Mass
import edu.wpi.first.units.measure.MomentOfInertia
import edu.wpi.first.units.measure.Voltage

/*
 * MAJOR SOURCE: Team 78 Air Strike
 * https://github.com/frc78/78-Kotlin-Template/blob/main/src/main/java/frc/robot/lib/Units.kt
 */

// These extension properties allow converting from a unit to a raw value
// You can use them like so:
// val meters = Meters.of(10)
// val tenMetersInInches = meters.inches
// val rotations = Rotations.of(5)
// val fiveRotationsInDegrees = rotations.degrees
inline val Distance.inches
    get() = this.`in`(Inches)
inline val Distance.meters
    get() = this.`in`(Meters)
inline val Distance.centimeters
    get() = this.`in`(Centimeters)
inline val LinearVelocity.metersPerSecond
    get() = this.`in`(MetersPerSecond)
inline val AngularVelocity.radiansPerSecond
    get() = this.`in`(RadiansPerSecond)
inline val AngularVelocity.rotationsPerSecond
    get() = this.`in`(RotationsPerSecond)
inline val AngularVelocity.rpm
    get() = this.`in`(RPM)
inline val AngularAcceleration.radiansPerSecondPerSecond
    get() = this.`in`(RadiansPerSecondPerSecond)
inline val Voltage.volts
    get() = this.`in`(Volts)
inline val Angle.radians
    get() = this.`in`(Radians)
inline val Angle.degrees
    get() = this.`in`(Degrees)
inline val Angle.rotations
    get() = this.`in`(Rotations)
inline val Current.amps
    get() = this.`in`(Amps)
inline val Mass.kilograms
    get() = this.`in`(Kilograms)
inline val MomentOfInertia.kilogramSquareMeters
    get() = this.`in`(KilogramSquareMeters)

fun Angle.toDistance(radius: Distance): Distance = radius * this.radians

fun Distance.toAngle(radius: Distance): Angle = Radians.of((this / radius).magnitude())

fun LinearVelocity.toAngularVelocity(radius: Distance): AngularVelocity =
    RadiansPerSecond.of(this.metersPerSecond / radius.meters)

fun AngularVelocity.toLinearVelocity(radius: Distance): LinearVelocity =
    MetersPerSecond.of(radiansPerSecond * radius.meters)