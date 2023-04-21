package io.github.lexadiky.pdx.lib.firebase

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import io.github.lexadiky.akore.alice.DIModule
import io.github.lexadiky.akore.alice.eagerModule
import io.github.lexadiky.akore.alice.util.single

fun FirebaseModule(context: Context): DIModule {
    val application = FirebaseApp.initializeApp(context)!!
    
    return eagerModule("firebase") {
        single { application }
        single { FirebaseRemoteConfigProvider(inject()) }
        single { FirebaseRemoteConfig.getInstance(application) } // TODO use fs implementation
    }
}
