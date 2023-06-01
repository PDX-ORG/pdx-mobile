@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import io.github.lexadiky.akore.alice.robo.DIApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val application = application as PdxApplication

        setContent {
            DIApplication(application.diContainer) {
                Content()
            }
        }
    }

    @Composable
    private fun Content() {
        TODO("not implemented")
    }
}
