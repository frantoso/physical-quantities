package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.electrical.Power
import io.github.frantoso.physicalquantities.electrical.V
import io.github.frantoso.physicalquantities.electrical.Voltage
import io.github.frantoso.physicalquantities.electrical.W
import io.github.frantoso.physicalquantities.testUtils.STANDARD_OFFSET
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class PrefixesTest {
    @TestFactory
    fun `test prefixes with long names (int)`() =
        listOf(
            { 2.atto } to 0.000_000_000_000_000_002,
            { 2.femto } to 0.000_000_000_000_002,
            { 2.pico } to 0.000_000_000_002,
            { 2.nano } to 0.000_000_002,
            { 2.micro } to 0.000_002,
            { 2.milli } to 0.002,
            { 2.centi } to 0.02,
            { 2.deci } to 0.2,
            { 2.deca } to 20.0,
            { 2.hecto } to 200.0,
            { 2.kilo } to 2_000.0,
            { 2.mega } to 2_000_000.0,
            { 2.giga } to 2_000_000_000.0,
            { 2.tera } to 2_000_000_000_000.0,
            { 2.peta } to 2_000_000_000_000_000.0,
            { 2.exa } to 2_000_000_000_000_000_000.0,
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isEqualTo(expected.toRawType())
            }
        }

    @TestFactory
    fun `test prefixes with long names (double)`() =
        listOf(
            { 4.2.atto } to 0.000_000_000_000_000_004_2,
            { 4.2.femto } to 0.000_000_000_000_004_2,
            { 4.2.pico } to 0.000_000_000_004_2,
            { 4.2.nano } to 0.000_000_004_2,
            { 4.2.micro } to 0.000_004_2,
            { 4.2.milli } to 0.004_2,
            { 4.2.centi } to 0.042,
            { 4.2.deci } to 0.42,
            { 4.2.deca } to 42.0,
            { 4.2.hecto } to 420.0,
            { 4.2.kilo } to 4_200.0,
            { 4.2.mega } to 4_200_000.0,
            { 4.2.giga } to 4_200_000_000.0,
            { 4.2.tera } to 4_200_000_000_000.0,
            { 4.2.peta } to 4_200_000_000_000_000.0,
            { 4.2.exa } to 4_200_000_000_000_000_000.0,
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isCloseTo(expected.toRawType(), STANDARD_OFFSET)
            }
        }

    @TestFactory
    fun `test prefixes with short names (int)`() =
        listOf(
            { 2._a } to 0.000_000_000_000_000_002,
            { 2._f } to 0.000_000_000_000_002,
            { 2._p } to 0.000_000_000_002,
            { 2._n } to 0.000_000_002,
            { 2._µ } to 0.000_002,
            { 2._m } to 0.002,
            { 2._c } to 0.02,
            { 2._d } to 0.2,
            { 2._da } to 20.0,
            { 2._h } to 200.0,
            { 2._k } to 2_000.0,
            { 2._M } to 2_000_000.0,
            { 2._G } to 2_000_000_000.0,
            { 2._T } to 2_000_000_000_000.0,
            { 2._P } to 2_000_000_000_000_000.0,
            { 2._E } to 2_000_000_000_000_000_000.0,
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isEqualTo(expected.toRawType())
            }
        }

    @TestFactory
    fun `test prefixes with short names (double)`() =
        listOf(
            { 4.2._a } to 0.000_000_000_000_000_004_2,
            { 4.2._f } to 0.000_000_000_000_004_2,
            { 4.2._p } to 0.000_000_000_004_2,
            { 4.2._n } to 0.000_000_004_2,
            { 4.2._µ } to 0.000_004_2,
            { 4.2._m } to 0.004_2,
            { 4.2._c } to 0.042,
            { 4.2._d } to 0.42,
            { 4.2._da } to 42.0,
            { 4.2._h } to 420.0,
            { 4.2._k } to 4_200.0,
            { 4.2._M } to 4_200_000.0,
            { 4.2._G } to 4_200_000_000.0,
            { 4.2._T } to 4_200_000_000_000.0,
            { 4.2._P } to 4_200_000_000_000_000.0,
            { 4.2._E } to 4_200_000_000_000_000_000.0,
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isCloseTo(expected.toRawType(), STANDARD_OFFSET)
            }
        }

    @TestFactory
    fun `test some prefixes with appended unit`() =
        listOf(
            { 4.2._m.V } to Voltage.fromVolt(0.004_2),
            { 42._k.W } to Power.fromWatt(42_000.0),
            { 4.2.micro.V } to Voltage.fromVolt(0.000_004_2),
            { 42.mega.W } to Power.fromWatt(42_000_000.0),
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isInstanceOf(expected::class.java)
                assertThat(result.value).isCloseTo(expected.value, STANDARD_OFFSET)
            }
        }
}
