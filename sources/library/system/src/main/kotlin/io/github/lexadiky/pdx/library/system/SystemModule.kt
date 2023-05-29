package io.github.lexadiky.pdx.library.system

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single

val SystemModule by module("lib-system") {
    single<ActivityHolder> { ActivityHolder() }
    single<ReviewRequester> { ReviewRequesterImpl(inject()) }
}
