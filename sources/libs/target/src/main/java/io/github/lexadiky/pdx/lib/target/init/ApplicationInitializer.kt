package io.github.lexadiky.pdx.lib.target.init

import io.github.lexadiky.akore.alice.DIContainer
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ApplicationInitializer(di: DIContainer) {

    private val context = ApplicationInitializerContext(di)

    private val blockingAsyncTasks: MutableList<AsyncInitializationTask> = ArrayList()
    private val nonBlockingAsyncTasks: MutableList<AsyncInitializationTask> = ArrayList()

    private val scope = CoroutineScope(Job() + Dispatchers.IO + CoroutineName("app_init"))

    fun asyncTask(task: AsyncInitializationTask) = apply {
        if (task.blocking) {
            blockingAsyncTasks += task
        } else {
            nonBlockingAsyncTasks += task
        }
    }

    fun run() {
        runBlocking {
            val launchedTasks = blockingAsyncTasks.map { task ->
                scope.async {
                    BLogger.tag("ApplicationInitializer")
                        .info("blocking suspended init task '${task.id}' running...")
                    task.run(context)
                    BLogger.tag("ApplicationInitializer")
                        .info("blocking suspended init task '${task.id}' done")
                }
            }
            launchedTasks.awaitAll()
            BLogger.tag("ApplicationInitializer")
                .info("all blocking suspended async tasks are done")
        }
        scope.launch {
            val launchedTasks = nonBlockingAsyncTasks.map { task ->
                scope.async {
                    task.run(context)
                    BLogger.tag("ApplicationInitializer")
                        .info("non-blocking suspended init task '${task.id}' done")
                }
            }
            launchedTasks.awaitAll()
        }
    }
}