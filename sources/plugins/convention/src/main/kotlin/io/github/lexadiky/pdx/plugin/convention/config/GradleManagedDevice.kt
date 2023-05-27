package io.github.lexadiky.pdx.plugin.convention.config

enum class GradleManagedDevice(
    val id: String,
    val readable: String,
    val apiLevel: Int,
    val systemImageSource: String
) {

    Pixel6Api31(
        id = "pixel6Api31",
        readable = "Pixel 6",
        apiLevel = 31,
        systemImageSource = "aosp"
    );
}
