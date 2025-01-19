package io.github.franklisting65.physicalquantities.core

import io.github.franklisting65.physicalquantities.electrical.Power
import io.github.franklisting65.physicalquantities.electrical.V
import io.github.franklisting65.physicalquantities.electrical.Voltage
import io.github.franklisting65.physicalquantities.electrical.W
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class PrefixesTest {
    @TestFactory
    fun `test prefixes with long names (int)`() =
        listOf(
            { 2.atto } to 0.000000000000000002,
            { 2.femto } to 0.000000000000002,
            { 2.pico } to 0.000000000002,
            { 2.nano } to 0.000000002,
            { 2.micro } to 0.000002,
            { 2.milli } to 0.002,
            { 2.centi } to 0.02,
            { 2.deci } to 0.2,
            { 2.kilo } to 2000.0,
            { 2.mega } to 2000000.0,
            { 2.giga } to 2000000000.0,
            { 2.tera } to 2000000000000.0,
            { 2.peta } to 2000000000000000.0,
            { 2.exa } to 2000000000000000000.0,
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `test prefixes with long names (double)`() =
        listOf(
            { 4.2.atto } to 0.0000000000000000042,
            { 4.2.femto } to 0.0000000000000042,
            { 4.2.pico } to 0.0000000000042,
            { 4.2.nano } to 0.0000000042,
            { 4.2.micro } to 0.0000042,
            { 4.2.milli } to 0.0042,
            { 4.2.centi } to 0.042,
            { 4.2.deci } to 0.42,
            { 4.2.kilo } to 4200.0,
            { 4.2.mega } to 4200000.0,
            { 4.2.giga } to 4200000000.0,
            { 4.2.tera } to 4200000000000.0,
            { 4.2.peta } to 4200000000000000.0,
            { 4.2.exa } to 4200000000000000000.0,
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isCloseTo(expected, Offset.offset(0.000000000000001))
            }
        }

    @TestFactory
    fun `test prefixes with short names (int)`() =
        listOf(
            { 2._a } to 0.000000000000000002,
            { 2._f } to 0.000000000000002,
            { 2._p } to 0.000000000002,
            { 2._n } to 0.000000002,
            { 2._µ } to 0.000002,
            { 2._m } to 0.002,
            { 2._c } to 0.02,
            { 2._d } to 0.2,
            { 2._k } to 2000.0,
            { 2._M } to 2000000.0,
            { 2._G } to 2000000000.0,
            { 2._T } to 2000000000000.0,
            { 2._P } to 2000000000000000.0,
            { 2._E } to 2000000000000000000.0,
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `test prefixes with short names (double)`() =
        listOf(
            { 4.2._a } to 0.0000000000000000042,
            { 4.2._f } to 0.0000000000000042,
            { 4.2._p } to 0.0000000000042,
            { 4.2._n } to 0.0000000042,
            { 4.2._µ } to 0.0000042,
            { 4.2._m } to 0.0042,
            { 4.2._c } to 0.042,
            { 4.2._d } to 0.42,
            { 4.2._k } to 4200.0,
            { 4.2._M } to 4200000.0,
            { 4.2._G } to 4200000000.0,
            { 4.2._T } to 4200000000000.0,
            { 4.2._P } to 4200000000000000.0,
            { 4.2._E } to 4200000000000000000.0,
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isCloseTo(expected, Offset.offset(0.000000000000001))
            }
        }

    @TestFactory
    fun `test some prefixes with appended unit`() =
        listOf(
            { 4.2._m.V } to Voltage.fromVolt(0.0042),
            { 42._k.W } to Power.fromWatt(42000.0),
            { 4.2.micro.V } to Voltage.fromVolt(0.0000042),
            { 42.mega.W } to Power.fromWatt(42000000.0),
        ).mapIndexed { index, (inputFunc, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} - result is $expected") {
                val result = inputFunc()

                assertThat(result).isInstanceOf(expected::class.java)
                assertThat(result.value).isCloseTo(expected.value, Offset.offset(0.000000000000001))
            }
        }
}
