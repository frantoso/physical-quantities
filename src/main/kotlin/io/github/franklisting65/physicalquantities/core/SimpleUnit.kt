package io.github.franklisting65.physicalquantities.core

import kotlin.math.roundToInt

/**
 * Base class for simple units.
 * It provides a set of operators.
 * @param UnitType The type of the concrete unit.
 * @param DiffType The type to add or subtract. Maybe the same as the UnitType.
 * @param value The raw value to store in this unit instance. This raw values unit is the reference unit.
 */
abstract class SimpleUnit<UnitType : UnitBase, DiffType : UnitBase> protected constructor(
    value: Number,
) : UnitBase(value.toDouble()),
    Comparable<UnitType> {
    /**
     * Compares this instance with the specified instance for order.
     * @param other The instance to compare with this instance.
     * @return Returns zero if this value is equal to the specified other value,
     *  a negative number if it's less than other,
     *  or a positive number if it's greater than 'other'.
     */
    override fun compareTo(other: UnitType): Int = value.compareTo(other.value)

    /**
     * Gets the negative of this value.
     * @return Returns the negative of this value.
     */
    operator fun unaryMinus(): UnitType = createFromValue(-value)

    /**
     * Subtracts the other value from this value.
     * @param other The other value.
     * @return Returns the result of the operation.
     */
    operator fun minus(other: DiffType): UnitType = createFromValue(value - other.value)

    /**
     * Adds the other value to this value.
     * @param other The other value.
     * @return Returns the result of the operation.
     */
    operator fun plus(other: DiffType): UnitType = createFromValue(value + other.value)

    /**
     * Multiplies this value by the other value.
     * @param other The other value.
     * @return Returns the result of the operation.
     */
    operator fun times(other: Number): UnitType = createFromValue(value * other.toDouble())

    /**
     * Divides this value by the other value.
     * @param other The other value.
     * @return Returns the result of the operation.
     */
    operator fun div(other: Number): UnitType = createFromValue(value / other.toDouble())

    /**
     * Rounds value towards the closest integer with ties rounded towards even integer.
     * @return Returns an instance containing the rounded value.
     */
    fun round(): UnitType = createFromValue(value.roundToInt())

    /**
     * Helper method to be able to generally create a new instance of the right unit type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created instance from type T.
     */
    protected abstract fun createFromValue(value: Number): UnitType
}
