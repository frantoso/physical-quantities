package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.RawType
import io.github.frantoso.physicalquantities.utils.absoluteValue
import io.github.frantoso.physicalquantities.utils.defaultPrecisionFraction
import io.github.frantoso.physicalquantities.utils.hash
import io.github.frantoso.physicalquantities.utils.sgn
import io.github.frantoso.physicalquantities.utils.toRawType

/**
 * Base class for quantities.
 *
 * @property value The raw value to store in this instance. Raw values unit is the reference unit of the quantity.
 * @constructor Initializes a new instance of the [QuantityBase] class.
 */
abstract class QuantityBase protected constructor(
    value: Number,
) {
    /**
     * Gets the raw value stored in this instance. Raw values unit is the reference unit of the quantity.
     */
    internal val value: RawType = value.toRawType()

    /**
     * Gets the raw value stored in this instance. Raw values unit is the reference unit of the quantity.
     * This property is not intended for normal usage.
     */
    val rawValueNotForNormalUsage: RawType
        get() = value

    /**
     * Returns `true` if this instance is equal to [other]; `false` otherwise.
     */
    override fun equals(other: Any?): Boolean =
        (other != null) && (this::class == other::class) && value.compareTo((other as QuantityBase).value) == 0

    /**
     * Returns `true` if the absolute difference between this instance and [other] is smaller than [precisionFraction];
     * `false` otherwise. The default of [precisionFraction] is [defaultPrecisionFraction].
     */
    fun equalsByPrecision(
        other: Any?,
        precisionFraction: RawType = defaultPrecisionFraction,
    ): Boolean =
        (other != null) && (this::class == other::class) && ((value - (other as QuantityBase).value).absoluteValue < precisionFraction)

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int = value.hash

    /**
     * Returns a string representation of the object (it's value).
     */
    override fun toString(): String = value.toString()

    /**
     * Gets the sign of this instance:
     *   - `-1.0` if the value is negative,
     *   - zero if the value is zero,
     *   - `1.0` if the value is positive
     *
     * Special case:
     *   - `NaN.sign` is `NaN`
     */
    val sign: Int = this.value.sgn

    /**
     * Stores metadata associated with this quantity.
     * This metadata can be used to store additional information about the quantity,
     * such as measurement conditions, source, or any other relevant data.
     */
    private val metaDataStore: MutableMap<Any, Any> = mutableMapOf()

    /**
     * Gets the metadata associated with this quantity.
     */
    val metaData: Map<Any, Any>
        get() = metaDataStore

    /**
     * Sets metadata for this quantity.
     * If the key already exists, it will be overwritten.
     * @param key The key to associate with the value.
     * @param value The value to associate with the key.
     */
    fun setMetaData(
        key: Any,
        value: Any,
    ) {
        metaDataStore[key] = value
    }

    /**
     * Adds metadata for this quantity if the key does not already exist.
     * If the key already exists, it will not overwrite the existing value.
     * @param key The key to associate with the value.
     * @param value The value to associate with the key.
     * @return Returns `true` if the metadata was added, `false` if the key already exists.
     */
    fun addMetaData(
        key: Any,
        value: Any,
    ): Boolean {
        if (key in metaDataStore) {
            return false // Key already exists, do not overwrite
        }

        setMetaData(key, value)
        return true
    }

    /**
     * Copies this object.
     * @return Returns a copy of this object.
     */
    fun copy(): QuantityBase =
        copyQuantity().let {
            it.metaDataStore.putAll(metaData)
            it
        }

    /**
     * Copies this object.
     * @return Returns a copy of this object.
     */
    protected abstract fun copyQuantity(): QuantityBase
}
