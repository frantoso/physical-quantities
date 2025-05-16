package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.electrical.Charge
import io.github.frantoso.physicalquantities.electrical.Current
import io.github.frantoso.physicalquantities.electrical.Resistance
import io.github.frantoso.physicalquantities.electrical.Voltage
import io.github.frantoso.physicalquantities.nonquantity.Scale
import io.github.frantoso.physicalquantities.quantity.AmountOfSubstance
import io.github.frantoso.physicalquantities.quantity.Energy
import io.github.frantoso.physicalquantities.quantity.Frequency
import io.github.frantoso.physicalquantities.quantity.Length
import io.github.frantoso.physicalquantities.quantity.MolarConcentration
import io.github.frantoso.physicalquantities.quantity.Power
import io.github.frantoso.physicalquantities.quantity.Volume
import io.github.frantoso.physicalquantities.thermodynamic.Pressure
import io.github.frantoso.physicalquantities.thermodynamic.Temperature

object Registry {
    val creators by lazy { quantities.flatMap { it }.associate { it.symbol to it.creator } }

    fun fromValueWithUnit(input: ValueWithUnit): QuantityBase? {
        val factor = input.symbolPrefix.toPrefix().factorToBase
        val creator = creators[input.symbolUnit]

        return creator?.invoke(input.value * factor)
    }

    private val quantities =
        listOf(
            Charge.creators,
            Current.creators,
            Resistance.creators,
            Voltage.creators,
            AmountOfSubstance.creators,
            Energy.creators,
            Frequency.creators,
            Length.creators,
            MolarConcentration.creators,
            Power.creators,
            Volume.creators,
            Pressure.creators,
            Temperature.creators,
            Scale.creators,
        )
}
