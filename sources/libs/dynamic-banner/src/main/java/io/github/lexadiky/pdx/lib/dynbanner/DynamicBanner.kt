package io.github.lexadiky.pdx.lib.dynbanner

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import io.github.lexadiky.akore.alice.lookup
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.pdx.lib.dynbanner.entity.Banner
import io.github.lexadiky.pdx.lib.fs.statist.StaticResourceProvider
import io.github.lexadiky.pdx.lib.fs.statist.provide

@Composable
fun DynamicBanner(id: String) {
    var data by remember {
        mutableStateOf<Banner?>(null)
    }
    val d = di
    LaunchedEffect(Unit) {
        data = d.lookup<StaticResourceProvider>()
            .provide<Banner>("remote-config://$id")
            .read()
            .orNull()
    }

    Text(text = data?.message.orEmpty(), fontSize = 40.sp)
}