package io.github.lexadiky.pdx.library.arc

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual
import java.util.concurrent.Executors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelSocketSpec : DescribeSpec({
    Dispatchers.setMain(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
    describe("Generic behaviour") {
        it("state could be updated") {
            val socket = TestSocket(0)
                .synchronize()

            socket.state.counter shouldBeEqual 0
            socket.act(TestAction.Inc)
            socket.state.counter shouldBeEqual 1
        }
    }
})

private data class TestState(val counter: Int)

private sealed interface TestAction {
    object Inc : TestAction
    object Dec : TestAction
}

private class TestSocket(initCounter: Int) : ViewModelSocket<TestState, TestAction>(TestState(initCounter)) {

    override suspend fun onAction(action: TestAction) {
        state = when (action) {
            TestAction.Dec -> state.copy(counter = state.counter - 1)
            TestAction.Inc -> state.copy(counter = state.counter + 1)
        }
    }
}
