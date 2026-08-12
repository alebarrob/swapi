import java.util.Properties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val localPropertiesFile = rootProject.file("local.properties")
val localProperties = Properties()

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
}

if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { inputStream ->
        localProperties.load(inputStream)
    }
}

android {
    namespace = "barrera.alejandro.swapi"
    compileSdk = 37

    defaultConfig {
        applicationId = "barrera.alejandro.swapi"
        minSdk = 26
        targetSdk = 37
        versionCode = 7
        versionName = "1.6"

        testInstrumentationRunner = "barrera.alejandro.swapi.util.SwapiTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            type = "String",
            name = "INTERSTITIAL_AD_ID",
            value = "\"${localProperties.getProperty("INTERSTITIAL_AD_ID", "")}\"",
        )

        manifestPlaceholders["APPLICATION_ADMOB_ID"] =
            localProperties.getProperty("APPLICATION_ADMOB_ID", "")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            ndk {
                debugSymbolLevel = "FULL"
            }
        }

        debug {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

ksp {
    arg("room.generateKotlin", "true")
}

room {
    schemaDirectory("$projectDir/schemas")
}

composeCompiler {
    reportsDestination =
        layout.buildDirectory.dir("compose_compiler")

    stabilityConfigurationFiles.add(
        rootProject.layout.projectDirectory.file("stability_config.conf")
    )
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)

    // Core Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.jetbrains.kotlinx.serialization.json)

    // Navigation 3
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    // Compose Libraries
    implementation(libs.androidx.activity.compose)
    implementation(composeBom)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Material Design Library
    implementation(libs.androidx.material3)

    // Room Libraries
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Hilt Libraries
    implementation(libs.google.dagger.hilt.android)
    implementation(libs.hilt.lifecycle.viewmodel.compose)
    ksp(libs.google.dagger.hilt.compiler)

    // Firebase Libraries
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    // Ads Libraries
    implementation(libs.play.services.ads)
    implementation(libs.user.messaging.platform)

    // DataStore Libraries
    implementation(libs.datastore.preferences)

    // Testing Libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.google.dagger.hilt.android.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    kspAndroidTest(libs.google.dagger.hilt.compiler)

    // Debug Libraries
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}