package io.github.lexadiky.pdx.feature.rateapp

internal sealed interface RateAppAction {

    object LikePressed : RateAppAction

    object DislikePressed : RateAppAction

    object DialogDismiss : RateAppAction
}
