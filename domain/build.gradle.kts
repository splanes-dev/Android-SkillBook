buildscript {
    apply(plugin = baseLibs.plugins.kotlin.parcelize.id)
}

android {
    namespace = "com.splanes.gifting.domain"
    compileSdk = 33
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
}
