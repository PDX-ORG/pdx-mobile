package io.github.lexadiky.pdx.library.nibbler

sealed interface NavigateCommand {

    class GoTo(val route: Route): NavigateCommand {

        val uri: String = route.uri
        val timestamp = System.currentTimeMillis().toInt()

        override fun hashCode(): Int {
            return timestamp
        }

        override fun toString(): String {
            return "GoTo(uri=$uri)"
        }
    }

    class Back : NavigateCommand {

        val timestamp = System.currentTimeMillis().toInt()

        override fun hashCode(): Int {
            return timestamp
        }

        override fun toString(): String {
            return "Back"
        }
    }
}
