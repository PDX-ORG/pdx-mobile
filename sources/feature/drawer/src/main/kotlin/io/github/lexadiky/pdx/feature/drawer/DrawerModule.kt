package io.github.lexadiky.pdx.feature.drawer

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.account.AccountDomainModule
import io.github.lexadiky.pdx.feature.drawer.accountcard.AccountCardSocket
import io.github.lexadiky.pdx.feature.drawer.domain.DrawerItemSource

internal val DrawerModule by module("drawer") {
    import(AccountDomainModule)
    internal {
        single { DrawerItemSource(inject(), inject()) }
        singleViewModel { AccountCardSocket(inject(), inject()) }
        singleViewModel { params -> DrawerViewModel(inject(), inject()) }
    }
}
