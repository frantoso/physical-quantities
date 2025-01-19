package io.github.franklisting65.physicalquantities.core

/**
 * A helper class used to store a value together with a unit. This is normally the result of a scale and
 * export operation applied to a quantity.
 *
 * @property value The value to store.
 * @property symbolPrefix The symbol of the scaling prefix.
 * @property symbolUnit The symbol of the unit.
 */
data class ValueWithUnit(
    val value: Double,
    val symbolPrefix: String,
    val symbolUnit: String,
)

/**
 * Creates a pair of a value and associated unit from a scaled quantity and the main symbol.
 *
 * @param scaledQuantity The scaled quantity instance holding the data.
 * @param symbolUnit The symbol of the unit to add to the prefix.
 */
fun <T : QuantityBase> valueWithUnit(
    scaledQuantity: ScaledQuantity<T>,
    symbolUnit: String,
): ValueWithUnit =
    ValueWithUnit(
        value = scaledQuantity.quantity.value * scaledQuantity.scaleFactor,
        symbolPrefix = scaledQuantity.symbolPrefix,
        symbolUnit = symbolUnit,
    )

/**
 * Creates a [ValueWithUnit] from the provided quantity and [symbol].
 */
fun <T : QuantityBase> T.valueWithUnit(symbol: String): ValueWithUnit =
    ValueWithUnit(
        value = value,
        symbolPrefix = "",
        symbolUnit = symbol,
    )
