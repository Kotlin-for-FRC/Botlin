package gay.zharel.botlin.triggers

import edu.wpi.first.wpilibj.event.EventLoop
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler
import java.util.function.BooleanSupplier

class ScopedTrigger(
    val loop: EventLoop = CommandScheduler.getInstance().defaultButtonLoop,
    val scope: BooleanSupplier = BooleanSupplier { true },
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

    fun onChange(command: Command): ScopedTrigger {
        addBinding {
            previous, current, previousScope, currentScope ->
            if(previous != current && currentScope) {
                CommandScheduler.getInstance().schedule(command)
            }
            if(previousScope && !currentScope) {
                CommandScheduler.getInstance().cancel(command)
            }
        }
        return this
    }

    fun onTrue(command: Command): ScopedTrigger {
        addBinding {
            previous, current, previousScope, currentScope ->
            if(!previous && current && currentScope) {
                CommandScheduler.getInstance().schedule(command)
            }
            if(previousScope && !currentScope) {
                CommandScheduler.getInstance().cancel(command)
            }
        }
        return this
    }

    fun onFalse(command: Command): ScopedTrigger {
        addBinding {
            previous, current, previousScope, currentScope ->
            if(previous && !current && currentScope) {
                CommandScheduler.getInstance().schedule(command)
            }
            if(previousScope && !currentScope) {
                CommandScheduler.getInstance().cancel(command)
            }
        }
        return this
    }

    override fun getAsBoolean(): Boolean = condition.asBoolean && scope.asBoolean

}