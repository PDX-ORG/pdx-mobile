package io.github.lexadiky.pdx.lib.system

import android.content.Context
import android.content.pm.ApplicationInfo

val Context.isDebug: Boolean get() =
    0 == (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)