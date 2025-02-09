package io.github.frantoso.physicalquantities

import com.jillesvangurp.kotlin4example.Page
import com.jillesvangurp.kotlin4example.SourceRepository
import io.github.frantoso.physicalquantities.core._m
import io.github.frantoso.physicalquantities.core.milli
import io.github.frantoso.physicalquantities.electrical.A
import io.github.frantoso.physicalquantities.electrical.V
import io.github.frantoso.physicalquantities.electrical.W
import io.github.frantoso.physicalquantities.electrical.times
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
                    Test project to create a kotlin lib to handle physical quantities in code in a type safe manner.
                    """.trimIndent()

                section("Examples")
                example {
                    val power = 23.V.times(42.A)

                    assertThat(power).isEqualTo(966.W)
                }
                +
                    """
                    Initialize some `Voltage` in **V**olts and `Current` in **A**mpere to combine them into `Power` in **W**atts: 
                    """.trimIndent()
                +
                    """
                    Also long and short-form prefixes are supported; please note the `_` in `_m` to distinguish milli from meters.
                    Millimeters are `_m.m`
                    """.trimIndent()
                example {
                    val power = 23.milli.V.times(42._m.A)
                }
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
