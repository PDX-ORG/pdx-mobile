package io.github.lexadiky.pdx.plugin.convention.config

data class GradleManagedDevice(
    val id: String,
    val name: String,
    val apiLevel: Int,
    val systemImageSource: String
) {

    companion object {

        val all = listOf(
            GradleManagedDevice(
                id = "pixel6Api31",
                name = "Pixel 6",
                apiLevel = 31,
                systemImageSource = "aosp"
            ),
        )
    }
}
