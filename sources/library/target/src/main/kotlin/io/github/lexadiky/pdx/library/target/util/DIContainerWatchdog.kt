package io.github.lexadiky.pdx.library.target.util

import android.content.Context
import io.github.lexadiky.akore.alice.DIContainer
import io.github.lexadiky.akore.alice.DIModule
import io.github.lexadiky.akore.alice.Qualifier
import io.github.lexadiky.akore.alice.introspection.DIContainerEventListener
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.verbose
import io.github.lexadiky.pdx.library.system.isDebug
import java.util.concurrent.atomic.AtomicInteger
import kotlin.reflect.KClass

class DIContainerWatchdog private constructor(): DIContainerEventListener {

    private val logger = BLogger.tag("DIContainerWatchdog")
    private var totalLookupCounter: AtomicInteger = AtomicInteger(0)

    override fun onModuleRegistered(module: DIModule) {
        logger.verbose("module registered: ${module.name}")
    }

    override fun onLookup(type: KClass<*>, qualifier: Qualifier, vararg parameters: Any) {
        logger.verbose("lookup: ${totalLookupCounter.addAndGet(1)}:${type.qualifiedName}:${qualifier}:${parameters.contentToString()}")
    }

    override fun onContainerBuild(container: DIContainer) {
        logger.verbose("container built")
    }

    override fun onModuleDeregistered(module: DIModule) {
        logger.verbose("module deregistered: ${module.name}")
    }

    companion object {

        fun create(context: Context): DIContainerEventListener = if (context.isDebug) {
            DIContainerWatchdog()
        } else {
            object : DIContainerEventListener {}
        }
    }
}
