@file:Suppress("unused")

package gay.zharel.botlin.commands

import edu.wpi.first.wpilibj2.command.Subsystem
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Delegated [CoroutineCommand] builder.
 *
 * Creates a delegated property that generates a new command each time.
 *
 * @param requirements (optional) The command subsystem requirements
 * @param runsWhileDisabled (optional) A flag to run the command while the robot is disabled
 * @param block The code block used for execution
 */
class CoroutineCommandBuilder(
    private val requirements: Set<Subsystem> = setOf(),
    private val runsWhileDisabled: Boolean = false,
    private val block: suspend CoroutineCommandIteratorScope.() -> Unit
): ReadOnlyProperty<Any?, CoroutineCommand> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): CoroutineCommand {
        return CoroutineCommand(requirements, runsWhileDisabled, block)
    }

}