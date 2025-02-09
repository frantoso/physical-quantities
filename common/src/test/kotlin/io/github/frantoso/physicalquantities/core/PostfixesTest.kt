package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.electrical.A
import io.github.frantoso.physicalquantities.testUtils.STANDARD_OFFSET
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class PostfixesTest {
    @TestFactory
    fun `test postfixes with long names`() =
        listOf(
            2.A.atto to ScaledQuantity(2.A, 1e18, "a"),
            2.A.femto to ScaledQuantity(2.A, 1e15, "f"),
            2.A.pico to ScaledQuantity(2.A, 1e12, "p"),
            2.A.nano to ScaledQuantity(2.A, 1e9, "n"),
            2.A.micro to ScaledQuantity(2.A, 1e6, "µ"),
            2.A.milli to ScaledQuantity(2.A, 1e3, "m"),
            2.A.centi to ScaledQuantity(2.A, 100.0, "c"),
            2.A.deci to ScaledQuantity(2.A, 10.0, "d"),
            2.A.deca to ScaledQuantity(2.A, 0.1, "da"),
            2.A.hecto to ScaledQuantity(2.A, 0.01, "h"),
            2.A.kilo to ScaledQuantity(2.A, 1e-3, "k"),
            2.A.mega to ScaledQuantity(2.A, 1e-6, "M"),
            2.A.giga to ScaledQuantity(2.A, 1e-9, "G"),
            2.A.tera to ScaledQuantity(2.A, 1e-12, "T"),
            2.A.peta to ScaledQuantity(2.A, 1e-15, "P"),
            2.A.exa to ScaledQuantity(2.A, 1e-18, "E"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected (${expected.symbolPrefix})") {
                assertThat(input.quantity).isEqualTo(expected.quantity)
                assertThat(input.scaleFactor).isCloseTo(expected.scaleFactor, STANDARD_OFFSET)
                assertThat(input.quantity::class).isSameAs(expected.quantity::class)
            }
        }

    @TestFactory
    fun `test postfixes with short names`() =
        listOf(
            2.A._a to ScaledQuantity(2.A, 1e18, "a"),
            2.A._f to ScaledQuantity(2.A, 1e15, "f"),
            2.A._p to ScaledQuantity(2.A, 1e12, "p"),
            2.A._n to ScaledQuantity(2.A, 1e9, "n"),
            2.A._µ to ScaledQuantity(2.A, 1e6, "µ"),
            2.A._m to ScaledQuantity(2.A, 1e3, "m"),
            2.A._c to ScaledQuantity(2.A, 100.0, "c"),
            2.A._d to ScaledQuantity(2.A, 10.0, "d"),
            2.A._da to ScaledQuantity(2.A, 0.1, "da"),
            2.A._h to ScaledQuantity(2.A, 0.01, "h"),
            2.A._k to ScaledQuantity(2.A, 1e-3, "k"),
            2.A._M to ScaledQuantity(2.A, 1e-6, "M"),
            2.A._G to ScaledQuantity(2.A, 1e-9, "G"),
            2.A._T to ScaledQuantity(2.A, 1e-12, "T"),
            2.A._P to ScaledQuantity(2.A, 1e-15, "P"),
            2.A._E to ScaledQuantity(2.A, 1e-18, "E"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected (${expected.symbolPrefix})") {
                assertThat(input.quantity).isEqualTo(expected.quantity)
                assertThat(input.scaleFactor).isCloseTo(expected.scaleFactor, STANDARD_OFFSET)
                assertThat(input.quantity::class).isSameAs(expected.quantity::class)
            }
        }

    @TestFactory
    fun `test postfixes with unit`() =
        listOf(
            2.A._a.A to ValueWithUnit(2e18, "a", "A"),
            2.A._f.A to ValueWithUnit(2e15, "f", "A"),
            2.A._p.A to ValueWithUnit(2e12, "p", "A"),
            2.A._n.A to ValueWithUnit(2e9, "n", "A"),
            2.A._µ.A to ValueWithUnit(2e6, "µ", "A"),
            2.A._m.A to ValueWithUnit(2e3, "m", "A"),
            2.A._c.A to ValueWithUnit(200.0, "c", "A"),
            2.A._d.A to ValueWithUnit(20.0, "d", "A"),
            2.A._da.A to ValueWithUnit(0.2, "da", "A"),
            2.A.A to ValueWithUnit(2.0, "", "A"),
            2.A._h.A to ValueWithUnit(0.02, "h", "A"),
            2.A._k.A to ValueWithUnit(2e-3, "k", "A"),
            2.A._M.A to ValueWithUnit(2e-6, "M", "A"),
            2.A._G.A to ValueWithUnit(2e-9, "G", "A"),
            2.A._T.A to ValueWithUnit(2e-12, "T", "A"),
            2.A._P.A to ValueWithUnit(2e-15, "P", "A"),
            2.A._E.A to ValueWithUnit(2e-18, "E", "A"),
            0.02.A._m.A to ValueWithUnit(20.0, "m", "A"),
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                assertThat(input).isEqualTo(expected)
            }
        }
}
