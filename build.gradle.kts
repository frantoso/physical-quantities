plugins {
    kotlin("jvm") version "2.0.21"
    application
    id("org.jmailen.kotlinter") version "4.3.0"

    id("org.jetbrains.kotlinx.kover") version "0.7.5"
    id("com.gradleup.shadow") version "8.3.5"
}

group = "de.franklisting.physicalquantities"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.25.1")
    testImplementation("io.mockk:mockk:1.13.11")
}

application {
    mainClass.set("de.franklisting.physicalquantities.Program")
}

tasks.test {
    useJUnitPlatform()

    finalizedBy("koverHtmlReport")
}

koverReport {
    filters {
        excludes {
            classes("de.franklisting.physicalunits.Program")
        }
    }
}

kotlin {
    jvmToolchain(21)
}
