package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.defaultNumberFormatter

/**
 * Base class for simple differences.
 * @param DiffType The type of the concrete instance.
 * @param value The raw value to store in this instance. Raw values unit is the reference unit of the difference.
 * @param unitSymbol The symbol of the unit used in the derived class.
 * @constructor Initializes a new instance of the [SimpleDifference] class.
 */
abstract class SimpleDifference<DiffType : QuantityBase> protected constructor(
    value: Number,
    val unitSymbol: String,
) : QuantityBase(value),
    Comparable<DiffType> {
    /**
     * Returns a string representation of the object (it's value and base unit).
     */
    override fun toString(): String = "${defaultNumberFormatter.format(value)} $unitSymbol"

    /**
     * Compares this instance with the specified instance for order.
     * @param other The instance to compare with this instance.
     * @return Returns:
     *   - zero if this value is equal to the specified other value,
     *   - a negative number if it's less than other,
     *   - or a positive number if it's greater than 'other'.
     */
    override fun compareTo(other: DiffType): Int = value.compareTo(other.value)
}
