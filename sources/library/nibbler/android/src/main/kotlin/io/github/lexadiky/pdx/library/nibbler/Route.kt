package io.github.lexadiky.pdx.library.nibbler

@JvmInline
value class Route private constructor(val uri: String) {

    companion object {

        val INDEX = from("nibbler://__buildin__/index")

        fun from(uri: String): Route = Route(uri)
    }
}
