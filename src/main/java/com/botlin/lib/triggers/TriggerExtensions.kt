package com.botlin.lib.triggers

import java.util.function.BooleanSupplier
import edu.wpi.first.wpilibj2.command.button.Trigger

/**
 * Boolean NOT
 */
operator fun Trigger.not(): Trigger = this.negate()

/**
 * Infix boolean AND
 */
infix fun Trigger.and(trigger: BooleanSupplier): Trigger = this.and(trigger)

/**
 * Infix boolean NAND
 */
infix fun Trigger.nand(trigger: BooleanSupplier): Trigger = this.and(trigger).negate()

/**
 * Infix boolean OR
 */
infix fun Trigger.or(trigger: BooleanSupplier): Trigger = this.or(trigger)

/**
 * Infix boolean NOR
 */
infix fun Trigger.nor(trigger: BooleanSupplier): Trigger = this.or(trigger).negate()

/**
 * Infix boolean XOR
 */
infix fun Trigger.xor(trigger: BooleanSupplier): Trigger = this.or(trigger).and(this.and(trigger).negate())

/**
 * Infix boolean XNOR
 */
infix fun Trigger.xnor(trigger: BooleanSupplier): Trigger = this.and(trigger).or(this.or(trigger).negate())