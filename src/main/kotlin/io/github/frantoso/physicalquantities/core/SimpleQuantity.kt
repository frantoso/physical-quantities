package io.github.frantoso.physicalquantities.core

import kotlin.math.roundToInt

/**
 * Base class for simple quantities.
 * It provides a set of operators.
 * @param QuantityType The type of the concrete instance.
 * @param DiffType The type to add or subtract. Maybe the same as the [QuantityType].
 * @param value The raw value to store in this instance. Raw values unit is the reference unit of the quantity.
 * @constructor Initializes a new instance of the [SimpleQuantity] class.
 */
abstract class SimpleQuantity<QuantityType : QuantityBase, DiffType : QuantityBase> protected constructor(
    value: Number,
) : QuantityBase(value.toDouble()),
    Comparable<QuantityType> {
    /**
     * Compares this instance with the specified instance for order.
     * @param other The instance to compare with this instance.
     * @return Returns:
     *   - zero if this value is equal to the specified other value,
     *   - a negative number if it's less than other,
     *   - or a positive number if it's greater than 'other'.
     */
    override fun compareTo(other: QuantityType): Int = value.compareTo(other.value)

    /**
     * Returns the negative of this value.
     */
    operator fun unaryMinus(): QuantityType = createFromValue(-value)

    /**
     * Subtracts [other] from this value.
     * @return Returns the result of the operation.
     */
    operator fun minus(other: DiffType): QuantityType = createFromValue(value - other.value)

    /**
     * Adds [other] to this value.
     * @return Returns the result of the operation.
     */
    operator fun plus(other: DiffType): QuantityType = createFromValue(value + other.value)

    /**
     * Multiplies this value by [other].
     * @return Returns the result of the operation.
     */
    operator fun times(other: Number): QuantityType = createFromValue(value * other.toDouble())

    /**
     * Divides this value by [other].
     * @return Returns the result of the operation.
     */
    operator fun div(other: Number): QuantityType = createFromValue(value / other.toDouble())

    /**
     * Rounds this value towards the closest integer with ties rounded towards even integer.
     * @return Returns an instance containing the rounded value.
     */
    fun round(): QuantityType = createFromValue(value.roundToInt())

    /**
     * Helper method to be able to generally create a new instance of the right quantity type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created instance from type [QuantityType].
     */
    protected abstract fun createFromValue(value: Number): QuantityType
}
