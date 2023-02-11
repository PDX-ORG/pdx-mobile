package io.github.lexadiky.pdx.lib.target.util

import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

typealias SuspendedInitTask = suspend () -> Unit

class ApplicationInitializer {

    private val suspendedTasks: MutableList<Pair<String, SuspendedInitTask>> = ArrayList()
    private val scope = CoroutineScope(Job() + Dispatchers.IO + CoroutineName("app_init"))

    fun task(id: String, task: SuspendedInitTask) = apply {
        suspendedTasks += id to task
    }

    fun run() {
        scope.launch {
            val launchedTasks = suspendedTasks.map { (id, task) ->
                scope.async {
                    task.invoke()
                    BLogger.tag("ApplicationInitializer")
                        .info("suspended init task '$id' done")
                }
            }
            launchedTasks.awaitAll()
        }
    }
}
