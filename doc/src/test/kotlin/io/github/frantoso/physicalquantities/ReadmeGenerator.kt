package io.github.frantoso.physicalquantities

import com.jillesvangurp.kotlin4example.Page
import com.jillesvangurp.kotlin4example.SourceRepository
import io.github.frantoso.physicalquantities.core._h
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.core.milli
import io.github.frantoso.physicalquantities.electrical.A
import io.github.frantoso.physicalquantities.electrical.Current
import io.github.frantoso.physicalquantities.electrical.V
import io.github.frantoso.physicalquantities.electrical.W
import io.github.frantoso.physicalquantities.electrical.times
import io.github.frantoso.physicalquantities.thermodynamic.Pa
import io.github.frantoso.physicalquantities.thermodynamic.bar
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

/**
 * The README.md is generated when the tests run.
 */
@Suppress("ktlint:standard:unary-op-spacing", "UNUSED_VARIABLE")
class ReadmeGenerator {
    @Test
    fun `generate readme for this project`() {
        val k4ERepo =
            SourceRepository(
                repoUrl = "https://github.com/frantoso/physical-quantities",
                branch = "main",
                sourcePaths =
                    setOf(
                        "common/src/main/kotlin",
                        "src/main/kotlin",
                        "src/test/kotlin",
                        "double/src/main/kotlin",
                    ),
            )

        val readmeMarkdown =
            k4ERepo.md {
                section("Introduction")
                +
                    """
                    This is a Kotlin library for processing physical quantities in code in a type-safe manner.

                    Simple numbers can be converted into quantities. This gives the numbers a meaning and they cannot be
                    freely combined with other numbers or quantities. Only supported calculations are possible.

                    The checks are performed during compilation.
                    """.trimIndent()

                section("How to use")
                subSection("Gradle")
                +
                    """
                    The library physical quantities is distributed via MavenCentral.
                    
                    There are two versions available. The difference lies in the data type used to store the values. One
                    version supports Double and the other BigDecimal.
                    
                    **build.gradle.kts (Double)**
                    """.trimIndent()
                mdCodeBlock(
                    code =
                        """
                        repositories {
                            mavenCentral()
                        }
                        
                        dependencies {
                            implementation("io.github.frantoso:physical-quantities:<version>")
                        }
                        """.trimIndent(),
                    type = "kotlin",
                )
                +
                    """
                    **build.gradle.kts (BigDecimal)**
                    """.trimIndent()
                mdCodeBlock(
                    code =
                        """
                        repositories {
                            mavenCentral()
                        }
                        
                        dependencies {
                            implementation("io.github.frantoso:physical-quantities-bd:<version>")
                        }
                        """.trimIndent(),
                    type = "kotlin",
                )

                subSection("Create a quantity")
                +
                    """
                    Quantities can be created by a member function of a quantity type or by a unit.
                    
                    e.g.
                    """.trimIndent()
                example {
                    val current = Current.fromAmpere(12)
                }
                +
                    """
                    is the same as
                    """.trimIndent()
                example {
                    val current = 12.A
                }
                +
                    """
                    To handle small and large values, modifiers are provided as prefixes. A long and a short
                    form is available.
                    
                    Long variant:
                    """.trimIndent()
                example {
                    val current = 12.milli.A
                }
                +
                    """
                    or short:
                    """.trimIndent()
                example {
                    val current = 12._m.A
                }
                +
                    """
                    Short form prefixes have a leading underscore to distinguish a prefix from a unit.
                    """.trimIndent()

                subSection("Examples")
                +
                    """
                    Multiplying a voltage with a current will result in power.
                    """.trimIndent()
                example {
                    val power = 23.V * 42.A

                    assertThat(power).isEqualTo(966.W)
                    assertThat(power).isNotEqualTo(966.V)
                    assertThat(power).isNotEqualTo(966.A)
                }
                +
                    """
                    Some quantities support multiple units, e.g. pressure:
                    """.trimIndent()
                example {
                    val pressure = 23._h.Pa
                    val anotherPressure = 42._m.bar

                    assertThat(pressure).isEqualTo(2_300.Pa)
                    assertThat(anotherPressure).isEqualTo(42._h.Pa)
                }

                section("Contributing")
                +
                    """
                    Currently the library only contains a few quantities (which is what I need most).
                    Help in expanding the library is appreciated.
                    
                    If you think something is missing (I'm sure there is a lot), prepare a pull request.
                    """.trimIndent()
            }

        val readmePage =
            Page(
                title = "Physical Quantities",
                fileName = "README.md",
                outputDir = "..",
            )
        readmePage.write(markdown = readmeMarkdown.value)
    }
}
