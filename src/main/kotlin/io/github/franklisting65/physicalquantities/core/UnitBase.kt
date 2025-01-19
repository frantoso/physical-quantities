package io.github.franklisting65.physicalquantities.core

import kotlin.math.sign

/**
 * Base class for units.
 *
 * @property value The raw value to store in this instance. Raw values unit is the reference unit.
 * @constructor Initializes a new instance of the [UnitBase] class.
 */
abstract class UnitBase protected constructor(
    internal val value: Double,
) {
    /**
     * Returns `true` if this unit is equal to [other]; `false` otherwise.
     */
    override fun equals(other: Any?): Boolean = (other != null) && (this::class == other::class) && value.equals((other as UnitBase).value)

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int = value.hashCode()

    /**
     * Gets the sign of this instance:
     *   - `-1.0` if the value is negative,
     *   - zero if the value is zero,
     *   - `1.0` if the value is positive
     *
     * Special case:
     *   - `NaN.sign` is `NaN`
     */
    val sign: Double = value.sign
}
