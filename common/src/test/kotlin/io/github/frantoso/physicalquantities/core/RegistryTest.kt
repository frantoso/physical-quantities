package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.electrical.A
import io.github.frantoso.physicalquantities.electrical.C
import io.github.frantoso.physicalquantities.electrical.O
import io.github.frantoso.physicalquantities.electrical.V
import io.github.frantoso.physicalquantities.nonquantity.sc
import io.github.frantoso.physicalquantities.quantity.Hz
import io.github.frantoso.physicalquantities.quantity.J
import io.github.frantoso.physicalquantities.quantity.W
import io.github.frantoso.physicalquantities.quantity.ft
import io.github.frantoso.physicalquantities.quantity.inch
import io.github.frantoso.physicalquantities.quantity.l
import io.github.frantoso.physicalquantities.quantity.ls
import io.github.frantoso.physicalquantities.quantity.m
import io.github.frantoso.physicalquantities.quantity.m3s
import io.github.frantoso.physicalquantities.quantity.mile
import io.github.frantoso.physicalquantities.quantity.mol
import io.github.frantoso.physicalquantities.quantity.molPerM3
import io.github.frantoso.physicalquantities.quantity.pH
import io.github.frantoso.physicalquantities.thermodynamic.Celsius
import io.github.frantoso.physicalquantities.thermodynamic.K
import io.github.frantoso.physicalquantities.thermodynamic.Pa
import io.github.frantoso.physicalquantities.thermodynamic.bar
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class RegistryTest {
    @Test
    fun `checks whether the registry contains quantities`() {
        assertThat(Registry.creators.size).isGreaterThan(19)
    }

    @TestFactory
    fun `creates the right quantity`() =
        listOf(
            ValueWithUnit(2.3, "m", "C") to 2.3._m.C,
            ValueWithUnit(2.3, "m", "A") to 2.3._m.A,
            ValueWithUnit(2.3, "m", "Ω") to 2.3._m.O,
            ValueWithUnit(2.3, "m", "V") to 2.3._m.V,
            ValueWithUnit(42, "", "mol") to 42.mol,
            ValueWithUnit(2.3, "k", "mol") to 2.3._k.mol,
            ValueWithUnit(1800, "m", "mol") to 1.8.mol,
            ValueWithUnit(2.3, "k", "J") to 2.3._k.J,
            ValueWithUnit(2.3, "k", "Ws") to 2.3._k.J,
            ValueWithUnit(2.3, "k", "Wh") to (2.3 * 3_600)._k.J,
            ValueWithUnit(2.3, "M", "Hz") to 2.3._M.Hz,
            ValueWithUnit(1800, "", "rpm") to 30.Hz,
            ValueWithUnit(2.3, "m", "m") to 2.3._m.m,
            ValueWithUnit(2.3, "", "in") to 2.3.inch,
            ValueWithUnit(2.3, "", "ft") to 2.3.ft,
            ValueWithUnit(2.3, "k", "m") to 2.3._k.m,
            ValueWithUnit(2.3, "", "mile") to 2.3.mile,
            ValueWithUnit(42, "", "mol/m³") to 42.molPerM3,
            ValueWithUnit(2.3, "k", "mol/m³") to 2.3._k.molPerM3,
            ValueWithUnit(1800, "m", "mol/m³") to 1.8.molPerM3,
            ValueWithUnit(42, "", "mol/l") to 42000.molPerM3,
            ValueWithUnit(2.3, "k", "mol/l") to 2300._k.molPerM3,
            ValueWithUnit(1800, "m", "mol/l") to 1800.molPerM3,
            ValueWithUnit(42, "", "M") to 42._k.molPerM3,
            ValueWithUnit(2.3, "k", "M") to 2.3._M.molPerM3,
            ValueWithUnit(1800, "m", "M") to 1800.molPerM3,
            ValueWithUnit(2.3, "m", "W") to 2.3._m.W,
            ValueWithUnit(42, "", "l") to 42.l,
            ValueWithUnit(42, "", "m³") to 42000.l,
            ValueWithUnit(2.3, "k", "l") to 2.3._k.l,
            ValueWithUnit(1800, "m", "l") to 1.8.l,
            ValueWithUnit(2.3, "k", "Pa") to 2.3._k.Pa,
            ValueWithUnit(2.3, "m", "bar") to 2.3._m.bar,
            ValueWithUnit(200.3, "", "K") to 200.3.K,
            ValueWithUnit(2.3, "k", "Celsius") to 2.3._k.Celsius,
            ValueWithUnit(2.3, "m", "°C") to 2.3._m.Celsius,
            ValueWithUnit(42, "", "sc") to 42.sc,
            ValueWithUnit(42, "", "%") to 0.42.sc,
            ValueWithUnit(42, "", "percent") to 0.42.sc,
            ValueWithUnit(2.3, "k", "%") to 23.sc,
            ValueWithUnit(1800, "", "‰") to 1.8.sc,
            ValueWithUnit(1800, "", "permille") to 1.8.sc,
            ValueWithUnit(42, "", "m³/s") to 42.m3s,
            ValueWithUnit(2.3, "k", "m³/s") to 2.3._k.m3s,
            ValueWithUnit(1800, "m", "m³/s") to 1.8.m3s,
            ValueWithUnit(42, "", "l/s") to 42._m.m3s,
            ValueWithUnit(2.3, "k", "l/s") to 2.3.m3s,
            ValueWithUnit(1800, "m", "l/s") to 1.8.ls,
            ValueWithUnit(2.3, "m", "X") to null,
            ValueWithUnit(42, "", "pH") to 42.pH,
            ValueWithUnit(2.3, "k", "pH") to 2.3._k.pH,
            ValueWithUnit(1800, "m", "pH") to 1.8.pH,
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("${"%02d".format(index)} expected result: $expected") {
                val result = Registry.fromValueWithUnit(input)

                assertThat(result).isEqualTo(expected)
            }
        }
}
