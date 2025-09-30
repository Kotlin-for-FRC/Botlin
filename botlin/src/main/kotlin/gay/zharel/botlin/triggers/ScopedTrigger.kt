package gay.zharel.botlin.triggers

import edu.wpi.first.wpilibj.event.EventLoop
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler
import java.util.function.BooleanSupplier

/**
 * Creates a new scoped trigger based on the given condition and scope.
 *
 * @param loop The loop instance that polls this trigger.
 * @param scope The scope condition.
 * @param condition The condition represented by this trigger.
 */
class ScopedTrigger(
    val loop: EventLoop = CommandScheduler.getInstance().defaultButtonLoop,
    var scope: BooleanSupplier = BooleanSupplier { true },
    val condition: BooleanSupplier
): BooleanSupplier {

    private class BindingBody (
        val body: (previous: Boolean, current: Boolean, previousScope: Boolean, currentScope: Boolean) -> Unit,
        val currentGetter: () -> Boolean,
        val scopeGetter: () -> Boolean
    ): Runnable {
        var previous: Boolean = currentGetter()
        var previousScope: Boolean = scopeGetter()
        override fun run() {
            val current = currentGetter()
            val currentScope = scopeGetter()
            body(previous, current, previousScope, currentScope)
            previous = current
            previousScope = currentScope
        }
    }

    private fun addBinding(body: (previous: Boolean, current: Boolean, previousScope: Boolean, currentScope: Boolean) -> Unit) {
        loop.bind(BindingBody(
            body,
            { condition.asBoolean },
            { scope.asBoolean }
        ))
    }

    /**
     * Starts the given command when the condition changes.
     *
     * @param command the command to start
     * @return this trigger, so calls can be chained
     */
    fun onChange(command: Command): ScopedTrigger {
        addBinding {
            previous, current, previousScope, currentScope ->
            if(previous != current && currentScope) {
                CommandScheduler.getInstance().schedule(command)
            } else if(previousScope && !currentScope) {
                command.cancel()
            }
        }
        return this
    }

    /**
     * Starts the given command whenever the condition changes from `false` to `true`.
     *
     * @param command the command to start
     * @return this trigger, so calls can be chained
     */
    fun onTrue(command: Command): ScopedTrigger {
        addBinding {
            previous, current, previousScope, currentScope ->
            if(!previous && current && currentScope) {
                CommandScheduler.getInstance().schedule(command)
            } else if(previousScope && !currentScope) {
                command.cancel()
            }
        }
        return this
    }

    /**
     * Starts the given command whenever the condition changes from `true` to `false`.
     *
     * @param command the command to start
     * @return this trigger, so calls can be chained
     */
    fun onFalse(command: Command): ScopedTrigger {
        addBinding {
            previous, current, previousScope, currentScope ->
            if(previous && !current && currentScope) {
                CommandScheduler.getInstance().schedule(command)
            } else if(previousScope && !currentScope) {
                command.cancel()
            }
        }
        return this
    }

    /**
     * Starts the given command when the condition changes to `true` and cancels it when the condition
     * changes to `false`.
     *
     * Doesn't re-start the command if it ends while the condition is still `true`. If the command
     * should restart, see [edu.wpi.first.wpilibj2.command.RepeatCommand]
     *
     * @param command the command to start
     * @return this trigger, so calls can be chained
     */
    fun whileTrue(command: Command): ScopedTrigger {
        addBinding {
                previous, current, previousScope, currentScope ->
            if(!previous && current && currentScope) {
                CommandScheduler.getInstance().schedule(command)
            }
            if(previousScope && !currentScope || previous && !current) {
                command.cancel()
            }
        }
        return this
    }

    /**
     * Starts the given command when the condition changes to `false` and cancels it when the condition
     * changes to `true`.
     *
     * Doesn't re-start the command if it ends while the condition is still `false`. If the command
     * should restart, see [edu.wpi.first.wpilibj2.command.RepeatCommand]
     *
     * @param command the command to start
     * @return this trigger, so calls can be chained
     */
    fun whileFalse(command: Command): ScopedTrigger {
        addBinding {
                previous, current, previousScope, currentScope ->
            if(previous && !current && currentScope) {
                CommandScheduler.getInstance().schedule(command)
            }
            if(previousScope && !currentScope || !previous && current) {
                command.cancel()
            }
        }
        return this
    }

    /**
     * Toggles a command when the condition changes from `false` to `true`.
     *
     * @param command the command to toggle
     * @return this trigger, so calls can be chained
     */
    fun toggleOnTrue(command: Command): ScopedTrigger {
        addBinding {
            previous, current, previousScope, currentScope ->
            if (!previous && current && currentScope) {
                if (command.isScheduled) {
                    command.cancel()
                } else {
                    CommandScheduler.getInstance().schedule(command)
                }
            } else if(previousScope && !currentScope) {
                command.cancel()
            }
        }
        return this
    }

    /**
     * Toggles a command when the condition changes from `true` to `false`.
     *
     * @param command the command to toggle
     * @return this trigger, so calls can be chained
     */
    fun toggleOnFalse(command: Command): ScopedTrigger {
        addBinding {
                previous, current, previousScope, currentScope ->
            if (previous && !current && currentScope) {
                if (command.isScheduled) {
                    command.cancel()
                } else {
                    CommandScheduler.getInstance().schedule(command)
                }
            } else if(previousScope && !currentScope) {
                command.cancel()
            }
        }
        return this
    }

    override fun getAsBoolean(): Boolean = condition.asBoolean && scope.asBoolean

    /**
     * Bind this trigger to a specific command.
     *
     * @param command The command to bind to
     */
    fun bind(command: Command) {
        scope = BooleanSupplier {
            CommandScheduler.getInstance().isScheduled(command)
        }
    }

    /**
     * Infix boolean AND
     */
    infix fun and(trigger: ScopedTrigger) = ScopedTrigger(
        loop,
        {scope.asBoolean && trigger.scope.asBoolean},
        {condition.asBoolean && trigger.condition.asBoolean}
    )

    /**
     * Infix boolean AND
     */
    infix fun and(trigger: BooleanSupplier) = ScopedTrigger(
        loop,
        scope,
        {condition.asBoolean && trigger.asBoolean}
    )

    /**
     * Infix boolean NAND
     */
    infix fun nand(trigger: ScopedTrigger) = ScopedTrigger(
        loop,
        {scope.asBoolean && trigger.scope.asBoolean},
        {!(condition.asBoolean && trigger.condition.asBoolean)}
    )

    /**
     * Infix boolean NAND
     */
    infix fun nand(trigger: BooleanSupplier) = ScopedTrigger(
        loop,
        scope,
        {!(condition.asBoolean && trigger.asBoolean)}
    )

    /**
     * Infix boolean OR
     */
    infix fun or(trigger: ScopedTrigger) = ScopedTrigger(
        loop,
        {scope.asBoolean && trigger.scope.asBoolean},
        {condition.asBoolean || trigger.condition.asBoolean}
    )

    /**
     * Infix boolean OR
     */
    infix fun or(trigger: BooleanSupplier) = ScopedTrigger(
        loop,
        scope,
        {condition.asBoolean || trigger.asBoolean}
    )

    /**
     * Infix boolean NOR
     */
    infix fun nor(trigger: ScopedTrigger) = ScopedTrigger(
        loop,
        {scope.asBoolean && trigger.scope.asBoolean},
        {!(condition.asBoolean || trigger.condition.asBoolean)}
    )

    /**
     * Infix boolean NOR
     */
    infix fun nor(trigger: BooleanSupplier) = ScopedTrigger(
        loop,
        scope,
        {!(condition.asBoolean || trigger.asBoolean)}
    )

    /**
     * Infix boolean XOR
     */
    infix fun xor(trigger: ScopedTrigger) = ScopedTrigger(
        loop,
        {scope.asBoolean && trigger.scope.asBoolean},
        { condition.asBoolean || trigger.condition.asBoolean && !(condition.asBoolean && trigger.condition.asBoolean) }
    )

    /**
     * Infix boolean XOR
     */
    infix fun xor(trigger: BooleanSupplier) = ScopedTrigger(
        loop,
        scope,
        { condition.asBoolean || trigger.asBoolean && !(condition.asBoolean && trigger.asBoolean) }
    )

    /**
     * Infix boolean XNOR
     */
    infix fun xnor(trigger: ScopedTrigger) = ScopedTrigger(
        loop,
        {scope.asBoolean && trigger.scope.asBoolean},
        { condition.asBoolean && trigger.condition.asBoolean || !(condition.asBoolean || trigger.condition.asBoolean) }
    )

    /**
     * Infix boolean XNOR
     */
    infix fun xnor(trigger: BooleanSupplier) = ScopedTrigger(
        loop,
        scope,
        { condition.asBoolean && trigger.asBoolean || !(condition.asBoolean || trigger.asBoolean) }
    )

    /**
     * Boolean NOT
     */
    operator fun not(): ScopedTrigger = ScopedTrigger(loop, scope, { !condition.asBoolean })

    /**
     * Boolean NOT
     */
    fun negate(): ScopedTrigger = !this

}