package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.toBigDecimal
import java.math.BigDecimal

/**
 * A helper class used to store a value together with a unit. This is normally the result of a scale and
 * export operation applied to a quantity.
 *
 * @property value The value to store.
 * @property symbolPrefix The symbol of the scaling prefix.
 * @property symbolUnit The symbol of the unit.
 */
class ValueWithUnit(
    value: Number,
    val symbolPrefix: String,
    val symbolUnit: String,
) {
    /**
     * Gets the stored value.
     */
    val value: BigDecimal = value.toBigDecimal()

    /**
     * Returns `true` if this instance is equal to [other]; `false` otherwise.
     */
    override fun equals(other: Any?): Boolean {
        val otherInstance = other as? ValueWithUnit ?: return false

        return value.compareTo(otherInstance.value) == 0 &&
            symbolPrefix == otherInstance.symbolPrefix &&
            symbolUnit == otherInstance.symbolUnit
    }

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int = value.hashCode() xor symbolPrefix.hashCode() xor symbolUnit.hashCode()
}

/**
 * Creates a pair of a value and associated unit from a scaled quantity and the main symbol.
 *
 * @param scaledQuantity The scaled quantity instance holding the data.
 * @param symbolUnit The symbol of the unit to add to the prefix.
 * @param conversion A conversion function to translate the value from the default unit to a different one.
 */
fun <T : QuantityBase> valueWithUnit(
    scaledQuantity: ScaledQuantity<T>,
    symbolUnit: String,
    conversion: (BigDecimal) -> BigDecimal = { value -> value },
): ValueWithUnit =
    ValueWithUnit(
        value = conversion(scaledQuantity.quantity.value) * scaledQuantity.scaleFactor,
        symbolPrefix = scaledQuantity.symbolPrefix,
        symbolUnit = symbolUnit,
    )

/**
 * Creates a [ValueWithUnit] from the provided quantity and [symbol] using the provided [conversion].
 */
fun <T : QuantityBase> T.valueWithUnit(
    symbol: String,
    conversion: (BigDecimal) -> BigDecimal = { value -> value },
): ValueWithUnit =
    ValueWithUnit(
        value = conversion(value),
        symbolPrefix = "",
        symbolUnit = symbol,
    )
