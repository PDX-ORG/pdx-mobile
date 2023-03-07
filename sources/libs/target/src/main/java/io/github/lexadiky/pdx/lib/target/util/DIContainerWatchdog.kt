package io.github.lexadiky.pdx.lib.target.util

import android.content.Context
import android.content.pm.ApplicationInfo
import io.github.lexadiky.akore.alice.DIModule
import io.github.lexadiky.akore.alice.Qualifier
import io.github.lexadiky.akore.alice.introspection.DIContainerInspector
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.verbose
import io.github.lexadiky.pdx.lib.system.isDebug
import java.util.concurrent.atomic.AtomicInteger
import kotlin.reflect.KClass

class DIContainerWatchdog private constructor(): DIContainerInspector {

    private var totalLookupCounter: AtomicInteger = AtomicInteger(0)

    override fun onModuleRegistered(module: DIModule) {
        BLogger.tag("DIContainerWatchdogRegister")
            .verbose("module registered: ${module.name}")
    }

    override fun onLookup(type: KClass<*>, qualifier: Qualifier, vararg parameters: Any) {
        BLogger.tag("DIContainerWatchdogLookup")
            .verbose("lookup: ${totalLookupCounter.addAndGet(1)}:${type.qualifiedName}:${qualifier}:${parameters.contentToString()}")
    }

    companion object {

        fun create(context: Context): DIContainerInspector = if (context.isDebug) {
            object : DIContainerInspector {}
        } else {
            DIContainerWatchdog()
        }
    }
}