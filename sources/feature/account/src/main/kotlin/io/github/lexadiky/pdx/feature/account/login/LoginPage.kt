package io.github.lexadiky.pdx.feature.account.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.account.R
import io.github.lexadiky.pdx.library.arc.Page
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.widget.BottomSheetBasement
import io.github.lexadiky.pdx.library.uikit.widget.scaffold.BottomSheetHeaderScaffold

@Composable
fun LoginPage() {
    DIFeature(LoginModule) {
        LoginPageImpl()
    }
}

@Composable
private fun LoginPageImpl(vm: LoginPageSocket = di.viewModel()) = Page(socket = vm) { state, act ->
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier
            .padding(MaterialTheme.grid.x2)
            .fillMaxWidth()
    ) {
        item {
            BottomSheetHeaderScaffold(
                title = { Text(text = stringResource(id = R.string.account_login_title)) },
                subtitle = { Text(text = stringResource(id = R.string.account_login_description)) },
                icon = {
                    Icon(
                        painter = painterResource(id = UikitDrawable.uikit_ic_account_circle),
                        contentDescription = null
                    )
                }
            )
        }
        item {
            LazyRow {
                when (state.availableAvatars) {
                    is Lce.Content -> {
                        items(state.availableAvatars.value) { preview ->
                            Image(
                                painter = preview.render(listOf(ImageTransformation.CropTransparent)),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(MaterialTheme.grid.x10)
                                    .aspectRatio(1f)
                            )
                        }
                    }
                    is Lce.Error,
                    Lce.Loading -> Unit
                }
            }
        }
        item {
            Text(text = stringResource(id = R.string.account_login_backup_notice))
        }
        item {
            OutlinedTextField(
                value = state.username,
                enabled = state.inputEnabled,
                isError = state.usernameIsInvalid,
                label = { Text(text = stringResource(id = R.string.account_login_username_box_title)) },
                placeholder = { Text(text = stringResource(id = R.string.account_login_username_box_placeholder)) },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = UikitDrawable.uikit_ic_shuffle),
                        contentDescription = null,
                        modifier = Modifier
                            .scale(0.5f)
                            .clickable { act(LoginPageAction.ShuffleUsername) }
                    )
                },
                maxLines = 1,
                onValueChange = { act(LoginPageAction.UpdateUsername(it)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            OutlinedTextField(
                value = state.localTrainerId,
                label = { Text(text = stringResource(id = R.string.account_login_trainer_id_title)) },
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.size(MaterialTheme.grid.x2))
                Button(
                    enabled = state.inputEnabled,
                    onClick = { act(LoginPageAction.Login) },
                ) {
                    Text(text = stringResource(id = R.string.account_login_create_account_button))
                }
            }
        }
        item {
            BottomSheetBasement()
        }
    }
}