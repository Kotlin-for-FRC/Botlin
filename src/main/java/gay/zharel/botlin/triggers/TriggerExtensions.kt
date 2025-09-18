package gay.zharel.botlin.triggers

import edu.wpi.first.wpilibj2.command.button.Trigger
import java.util.function.BooleanSupplier

/** Boolean NOT */
operator fun Trigger.not(): Trigger = this.negate()

/** Infix boolean AND */
infix fun Trigger.and(trigger: BooleanSupplier): Trigger = this.and(trigger)

/** Infix boolean NAND */
infix fun Trigger.nand(trigger: BooleanSupplier): Trigger = Trigger {
    !(asBoolean && trigger.asBoolean)
}

/** Infix boolean OR */
infix fun Trigger.or(trigger: BooleanSupplier): Trigger = this.or(trigger)

/** Infix boolean NOR */
infix fun Trigger.nor(trigger: BooleanSupplier): Trigger = Trigger {
    !(asBoolean || trigger.asBoolean)
}

/** Infix boolean XOR */
infix fun Trigger.xor(trigger: BooleanSupplier): Trigger = Trigger {
    asBoolean xor trigger.asBoolean
}

/** Infix boolean XNOR */
infix fun Trigger.xnor(trigger: BooleanSupplier): Trigger = Trigger {
    (asBoolean && trigger.asBoolean) || !(asBoolean || trigger.asBoolean)
}
