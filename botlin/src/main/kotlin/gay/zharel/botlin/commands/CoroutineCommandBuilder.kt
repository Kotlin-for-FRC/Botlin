@file:Suppress("unused")

package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Subsystem
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class CoroutineCommandBuilder(
    private val requirements: Set<Subsystem> = setOf(),
    private val runsWhileDisabled: Boolean = false,
    private val block: suspend CoroutineCommandIteratorScope.() -> Unit
): ReadOnlyProperty<Any?, CoroutineCommand> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): CoroutineCommand {
        return CoroutineCommand(requirements, runsWhileDisabled, block)
    }

}