import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.vanniktech.publish)
}

android {
    namespace = "io.lokile.compose.animation.modifiers"
    compileSdk = 36

    buildFeatures { compose = true }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
}

dependencies {
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
}

val groupId = "io.github.lokile"
val artifactId = "compose-animated-border"
val version = "1.0.0"
val libraryDescription = "Lightweight animated border modifier for Jetpack Compose"
val yearOfRelease = "2025"
val libraryName = artifactId
val githubUrl = "https://github.com/lokile/$artifactId"
val scmUrl = "https://github.com/lokile/$artifactId"
val scmConnectionUrl = "scm:git:git://github.com/lokile/${artifactId}.git"
val scmDeveloperConnection = "scm:git:ssh://git@github.com/lokile/${artifactId}.git"

mavenPublishing {
    coordinates(
        groupId = groupId,
        artifactId = artifactId,
        version = version
    )

    // Configure POM metadata for the published artifact
    pom {
        // General information
        name.set(libraryName)
        description.set(libraryDescription)
        inceptionYear.set(yearOfRelease)
        url.set(githubUrl)

        // License information
        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("https://opensource.org/licenses/MIT")
            }
        }

        // Specify developers information
        developers {
            developer {
                id.set("lokile")
                name.set("Loki Le")
                email.set("lokile208@gmail.com")
                url.set("https://github.com/lokile")
            }
        }

        // Specify SCM information
        scm {
            url.set(scmUrl)
            connection.set(scmConnectionUrl)
            developerConnection.set(scmDeveloperConnection)
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral()

    // Enable GPG signing for all publications
    if (
        providers.gradleProperty("signingInMemoryKey").isPresent ||
        providers.gradleProperty("signing.secretKeyRingFile").isPresent
    ) {
        signAllPublications()
    }
}
