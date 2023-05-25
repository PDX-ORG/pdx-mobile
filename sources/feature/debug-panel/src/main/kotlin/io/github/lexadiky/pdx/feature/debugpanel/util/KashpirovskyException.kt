package io.github.lexadiky.pdx.feature.debugpanel.util

import kotlinx.datetime.Clock

class KashpirovskyException : Exception("Kashpirovsky visited ${Clock.System.now()}")
