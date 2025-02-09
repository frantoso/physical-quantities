plugins {
    java
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")
    id("org.jmailen.kotlinter")
    id("com.vanniktech.maven.publish")
}

group = "io.github.frantoso"

version = System.getenv("LIBRARY_VERSION") ?: project.findProperty("localLibraryVersion") ?: "-.-.-"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.25.1")
    testImplementation("io.mockk:mockk:1.13.11")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)

    sourceSets["main"].kotlin {
        srcDir("${rootProject.rootDir}/common/src/main/kotlin")
    }
    sourceSets["test"].kotlin {
        srcDir("${rootProject.rootDir}/common/src/test/kotlin")
    }
}

tasks.test {
    useJUnitPlatform()

    finalizedBy("koverHtmlReport")
}

dokka {
    basePublicationsDirectory.set(layout.buildDirectory.dir("docs"))

    dokkaPublications.html {
        outputDirectory.set(layout.buildDirectory.dir("docs/javadoc"))
    }
}
