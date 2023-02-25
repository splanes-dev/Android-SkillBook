enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

apply(from = "gradle/dependency-catalogs.settings.gradle.kts")

rootProject.name = "SkillBook"
include(":app")
include(":domain")
include(":data")
include(":ui")
