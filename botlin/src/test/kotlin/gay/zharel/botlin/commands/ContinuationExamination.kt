package gay.zharel.botlin.commands

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.junit.jupiter.api.Test
import java.util.Optional
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume

class ControllableDispatcher() : CoroutineDispatcher() {
    internal var block: Optional<() -> Unit> = Optional.empty()

    val isIdle: Boolean get() = block.isEmpty

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        this.block = Optional.of(block::run)
    }

    fun run() {
        block.ifPresent { it() }
        block = Optional.empty()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class ContinuationExamination {
    @Test
    fun `do continuations do what i think they do`() {
        val dispatcher = ControllableDispatcher()

        val thing = suspend {
            for (i in 1..5) {
                println("Tick $i")
                yield()
            }
        }

        val completion = object : Continuation<Unit> {
            override val context: CoroutineContext = dispatcher

            override fun resumeWith(result: Result<Unit>) {
                result.onSuccess {
                    println("Coroutine completed successfully")
                }.onFailure { exception ->
                    println("Coroutine failed: ${exception.message}")
                }
            }
        }

        val cont = thing.createCoroutine(completion)
        cont.resume(Unit)

        var i = 0
        while (!dispatcher.isIdle) {
            println("Iteration ${++i}")
            dispatcher.run()
        }
        dispatcher.run()
    }
}
