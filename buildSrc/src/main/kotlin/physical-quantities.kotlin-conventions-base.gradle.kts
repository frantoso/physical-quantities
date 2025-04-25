import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    java
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")
    id("org.jmailen.kotlinter")
    id("com.vanniktech.maven.publish")
    id("com.github.ben-manes.versions")
}

group = "io.github.frantoso"

version = System.getenv("LIBRARY_VERSION") ?: project.findProperty("localLibraryVersion") ?: "-.-.-"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("io.mockk:mockk:1.14.0")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)

    sourceSets["main"].kotlin {
        srcDir("${rootProject.rootDir}/common/src/main/kotlin")
    }
}

tasks.test {
    useJUnitPlatform()

    finalizedBy("koverHtmlReport")
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

// https://github.com/ben-manes/gradle-versions-plugin
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}
