package io.github.lexadiky.pdx.library.navigation

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.core.content.ContextCompat
import io.github.lexadiky.pdx.lib.navigation.R

class ShareIntentSender(private val context: Context) {

    fun shareApplication() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            context.getString(
                R.string.navigation_share_intent_message,
                "https://play.google.com/store/apps/details?id=${context.packageName}"
            )
        )
        sendIntent.flags = FLAG_ACTIVITY_NEW_TASK
        sendIntent.type = "text/plain"
        ContextCompat.startActivity(context, sendIntent, null)
    }
}
