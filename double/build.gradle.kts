import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("physical-quantities.kotlin-conventions")
}

println("Using version: $version (Double)")

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
