@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.drawer.accountcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.drawer.R
import io.github.lexadiky.pdx.library.arc.Page
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid

@Composable
internal fun AccountCard() {
    AccountCardImpl()
}

@Composable
internal fun AccountCardImpl(vm: AccountCardSocket = di.viewModel()) = Page(vm) { state, act ->
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(MaterialTheme.grid.x1)
            .clickable(state.isLoggedIn) { act(AccountCardAction.AccountClicked) }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(MaterialTheme.grid.x2)
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.small)
                    .size(MaterialTheme.grid.x4)
                    .clip(MaterialTheme.shapes.small)
            )
            Text(
                text = state.username.render(),
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        if (!state.isLoggedIn) {
            Button(
                onClick = { act(AccountCardAction.LoginClicked) },
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.grid.x2)
                    .padding(bottom = MaterialTheme.grid.x2)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.drawer_account_card_login_button))
            }
        }
    }
}
