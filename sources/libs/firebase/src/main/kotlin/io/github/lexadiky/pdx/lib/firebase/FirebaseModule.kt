package io.github.lexadiky.pdx.lib.firebase

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import io.github.lexadiky.akore.alice.DIModule
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.util.single

fun FirebaseModule(context: Context): DIModule {
    return eagerModule("firebase") {
        single { FirebaseApp.initializeApp(context)!! }
        single { FirebaseRemoteConfigProvider(inject()) }
        single { FirebaseRemoteConfig.getInstance(inject()) } // TODO use fs implementation
    }
}
