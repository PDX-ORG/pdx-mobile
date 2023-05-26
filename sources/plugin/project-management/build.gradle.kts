@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("java-gradle-plugin")
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("pdx-catkeeper") {
            id = "io.github.lexadiky.pdx.plugin.pm.catkeeper"
            implementationClass = "io.github.lexadiky.pdx.plugin.pm.CatKeeperPlugin"
            version = "indev"
        }
    }
}
