package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.defaultNumberFormatter
import io.github.frantoso.physicalquantities.utils.divideBy
import io.github.frantoso.physicalquantities.utils.round
import io.github.frantoso.physicalquantities.utils.toRawType

/**
 * Base class for simple quantities.
 * It provides a set of operators.
 * @param QuantityType The type of the concrete instance.
 * @param DiffType The type to add or subtract. Maybe the same as the [QuantityType].
 * @param value The raw value to store in this instance. Raw values unit is the reference unit of the quantity.
 * @param unitSymbol The symbol of the unit used in the derived class.
 * @constructor Initializes a new instance of the [SimpleQuantity] class.
 */
abstract class SimpleQuantity<QuantityType : QuantityBase, DiffType : QuantityBase> protected constructor(
    value: Number,
    val unitSymbol: String,
) : QuantityBase(value),
    Comparable<QuantityType> {
    /**
     * Helper class to store a unit symbol together t´with a creator function.
     * @property symbol The unit symbol to associate with the creator.
     * @property creator A function used to create a quantity object from type T.
     */
    data class CreatorInfo<T>(
        val symbol: String,
        val creator: (Number) -> T,
    )

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
    override fun compareTo(other: QuantityType): Int = value.compareTo(other.value)

    /**
     * Returns the negative of this value.
     */
    operator fun unaryMinus(): QuantityType = createFromValue(-value)

    /**
     * Subtracts [other] from this value.
     * @return Returns the result of the operation.
     */
    operator fun minus(other: DiffType): QuantityType = createFromValue(value.minus(other.value))

    /**
     * Adds [other] to this value.
     * @return Returns the result of the operation.
     */
    operator fun plus(other: DiffType): QuantityType = createFromValue(value.plus(other.value))

    /**
     * Multiplies this value by [other].
     * @return Returns the result of the operation.
     */
    operator fun times(other: Number): QuantityType = createFromValue(value.times(other.toRawType()))

    /**
     * Divides this value by [other].
     * @return Returns the result of the operation.
     */
    operator fun div(other: Number): QuantityType = createFromValue(value.divideBy(other.toRawType()))

    /**
     * Rounds this value towards the closest integer with ties rounded towards even integer.
     * @return Returns an instance containing the rounded value.
     */
    fun round(): QuantityType = createFromValue(value.round())

    /**
     * Helper method to be able to generally create a new instance of the right quantity type.
     * @param value The value to store in the new instance.
     * @return Returns the newly created instance from type [QuantityType].
     */
    protected abstract fun createFromValue(value: Number): QuantityType

    companion object {
        /**
         * Conversion function to get a quantity from a [ValueWithUnit] instance.
         * @param T The type of the quantity to return from the function.
         * @param input The instance to convert.
         * @param creators A list of creator functions provided by the specialized class.
         * @return Returns the newly created quantity instance.
         * @throws Throws
         *  - [NoSuchPrefixException] if there was an invalid prefix found.
         *  - [NoSuchUnitException] if there is no creator for the symbol found.
         */
        @JvmStatic
        protected inline fun <reified T> fromValueWithUnit(
            input: ValueWithUnit,
            creators: List<CreatorInfo<T>>,
        ): T {
            val factor = input.symbolPrefix.toPrefix().factorToBase
            val creator = creators.find { it.symbol == input.symbolUnit }?.creator

            val instance = creator?.invoke(input.value * factor)
            return instance ?: throw NoSuchUnitException("Unit ${input.symbolUnit} is not supported by ${T::class}")
        }
    }
}
