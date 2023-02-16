package io.github.lexadiky.pdx.lib.target

import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single

val FirebaseModule by module("firebase") {
    single { FirebaseApp.initializeApp(inject())!! }
    single { FirebaseRemoteConfig.getInstance(inject()) }
}