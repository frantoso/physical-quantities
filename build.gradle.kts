import com.vanniktech.maven.publish.SonatypeHost

plugins {
    java
    kotlin("jvm") version "2.1.0"
    id("com.vanniktech.maven.publish") version "0.30.0"
    id("org.jmailen.kotlinter") version "5.0.1"
    id("org.jetbrains.kotlinx.kover") version "0.9.1"
    id("org.jetbrains.dokka") version "2.0.0"
}

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

version = System.getenv("LIBRARY_VERSION") ?: project.findProperty("localLibraryVersion") ?: "-.-.-"

println("Using version: $version")

mavenPublishing {
    coordinates(
        groupId = "io.github.frantoso",
        artifactId = "physical-quantities",
    )

    // Configure POM metadata for the published artifact
    pom {
        name.set("Kotlin library for dealing with physical quantities")
        description.set(
            "This library can be used by JVM targets which want to calculate values representing physical quantities in a type safe manner",
        )
        inceptionYear.set("2025")
        url.set("https://github.com/frantoso/physical-quantities/")

        licenses {
            license {
                name.set("Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("frantoso")
                name.set("The frantoso developers")
                url.set("https://github.com/frantoso/physical-quantities/")
            }
        }

        scm {
            url.set("https://github.com/frantoso/physical-quantities/")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}
