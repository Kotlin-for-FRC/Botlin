@file:Suppress("unused")

package gay.zharel.botlin.commands

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class CoroutineCommandBuilder(
    private val block: suspend CoroutineCommandIteratorScope.() -> Unit
): ReadOnlyProperty<Any?, CoroutineCommand> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): CoroutineCommand {
        return CoroutineCommand(block)
    }

}