package de.franklisting.physicalquantities.core

import kotlin.math.sign

/**
 * Base class for units.
 * @param value The raw value to store in this unit instance. This raw values unit is the reference unit.
 */
abstract class UnitBase protected constructor(
    internal val value: Double,
) {
    /**
     * Indicates whether two units are equal.
     * @param other The other instance to compare with this instance.
     * @return Returns true if the instances are from the same type and the values are equal.
     */
    override fun equals(other: Any?): Boolean = (other != null) && (this::class == other::class) && value.equals((other as UnitBase).value)

    /**
     * Gets a hash code value for the object.
     * @return Returns a hash code value for the object.
     */
    override fun hashCode(): Int = value.hashCode()

    /**
     * Gets the sign of this instance:
     *      -1.0 if value is negative,
     *      zero if value is zero,
     *      1.0 if value is positive
     *  Special case:
     *      NaN. sign is NaN
     */
    val sign: Double = value.sign
}
