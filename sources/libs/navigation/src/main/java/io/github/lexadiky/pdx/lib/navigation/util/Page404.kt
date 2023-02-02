package io.github.lexadiky.pdx.lib.navigation.util

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import io.github.lexadiky.pdx.lib.navigation.R
import io.github.lexadiky.pdx.ui.uikit.theme.grid

internal fun NavGraphBuilder.registerPage404() = composable(
    route = "navi://__tech__/404",
    deepLinks = listOf(navDeepLink { uriPattern = "android-app://androidx.navigation/.*" })
) { navEntry ->
    val route = navEntry.arguments?.getParcelable<Intent>("android-support-nav:controller:deepLinkIntent")
        ?.data
        ?.toString()
        .orEmpty()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.navigation_404_title),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = stringResource(R.string.navigation_404_subtitle),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x4))
            Text(
                text = route,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
