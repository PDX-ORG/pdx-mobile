package io.github.lexadiky.pdx.library.errorhandler

import io.github.lexadiky.pdx.lib.errorhandler.R
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from

interface UIError {
    val message: StringResource

    companion object {

        fun generic(): UIError = object : UIError {
            override val message: StringResource = StringResource.from(R.string.error_handler_error_message_unknown)
        }

        fun network(): UIError = object : UIError {
            override val message: StringResource = StringResource.from(R.string.error_handler_error_message_network)
        }
    }
}
