package io.github.lexadiky.akore.lechuck.robo.fsdialog

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.FloatingWindow
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.NavigatorState
import kotlin.math.max
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun rememberFullScreenDialogNavigator(): FullScreenDialogNavigator = remember {
    FullScreenDialogNavigator()
}

@Navigator.Name("FullScreenDialogNavigator")
class FullScreenDialogNavigator : Navigator<FullScreenDialogNavigator.Destination>() {
    private val anchors: HashMap<String, FullScreenDialogAnchor> = HashMap()
    private var attached by mutableStateOf(false)

    private val backStack: StateFlow<List<NavBackStackEntry>>
        get() = if (attached) state.backStack else MutableStateFlow(emptyList())
    private val transitionsInProgress: StateFlow<Set<NavBackStackEntry>>
        get() = if (attached) state.transitionsInProgress else MutableStateFlow(emptySet())

    val content: @Composable () -> Unit = {
        val backStackEntries by backStack.collectAsState()
        val transitionsInProgressEntries by transitionsInProgress.collectAsState()

        val latestEntry = backStackEntries.lastOrNull { entry ->
            entry.getLifecycle().currentState.isAtLeast(Lifecycle.State.STARTED)
        }

        DisposableEffect(backStackEntries) {
            transitionsInProgressEntries.forEach {
                if (it != latestEntry) state.markTransitionComplete(it)
            }
            onDispose { }
        }

        if (latestEntry != null) {
            val anchor = anchors[DEFAULT_FS_DIALOG_TAG]
            val style = anchor?.style
            if (anchor != null && style is FullScreenDialogStyle.CircleExpansion) {
                val positionInRoot = anchor.coordinates.positionInRoot()
                val destination = remember(latestEntry) {
                    latestEntry.destination as Destination
                }

                val configuration = LocalConfiguration.current
                val radius = max(configuration.screenWidthDp, configuration.screenHeightDp)
                val size = remember { Animatable(0f) }
                LaunchedEffect(latestEntry) {
                    size.animateTo(radius.toFloat() * 2, style.animationSpec)
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithContent {
                            clipRect {
                                drawCircle(
                                    brush = style.backgroundBrush,
                                    radius = size.value.dp.toPx(),
                                    center = positionInRoot
                                )
                            }
                            drawContent()
                        }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        destination.content(latestEntry)
                    }
                }
            }
        }
    }

    fun saveAnchor(tag: String, anchor: FullScreenDialogAnchor) {
        anchors[tag] = anchor
    }

    override fun onAttach(state: NavigatorState) {
        super.onAttach(state)
        attached = true
    }

    override fun createDestination(): Destination =
        Destination(navigator = this, content = {})

    override fun navigate(
        entries: List<NavBackStackEntry>,
        navOptions: NavOptions?,
        navigatorExtras: Extras?,
    ) {
        entries.forEach { entry ->
            state.pushWithTransition(entry)
        }
    }

    override fun popBackStack(popUpTo: NavBackStackEntry, savedState: Boolean) {
        state.popWithTransition(popUpTo, savedState)
    }

    @NavDestination.ClassType(Composable::class)
    class Destination(
        navigator: FullScreenDialogNavigator,
        internal val content: @Composable (NavBackStackEntry) -> Unit,
    ) : NavDestination(navigator), FloatingWindow

    companion object {
        internal const val DEFAULT_FS_DIALOG_TAG = "akore-fsd://__tech__/default"
    }
}
