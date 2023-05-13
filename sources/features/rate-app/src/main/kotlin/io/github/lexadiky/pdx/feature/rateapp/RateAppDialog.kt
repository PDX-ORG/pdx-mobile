@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.rateapp

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.lib.arc.Page

@Composable
fun RateAppDialog() {
    DIFeature(RateAppModule) {
        RateAppDialogImpl()
    }
}

@Composable
private fun RateAppDialogImpl(vm : RateAppSocket = di.viewModel()) = Page(vm) { state, act ->
    if (vm.state.isDialogOpen) {
        AlertDialog(
            title = { Text(text = stringResource(id = R.string.rate_app_title)) },
            text = { Text(text = stringResource(id = R.string.rate_app_content)) },
            confirmButton = {
                TextButton(onClick = { act(RateAppAction.LikePressed) }) {
                    Text(text = stringResource(id = R.string.rate_app_like))
                }
            },
            dismissButton = {
                TextButton(onClick = { act(RateAppAction.DislikePressed) }) {
                    Text(text = stringResource(id = R.string.rate_app_dislike))
                }
            },
            onDismissRequest = { act(RateAppAction.DialogDismiss) }
        )
    }
}
