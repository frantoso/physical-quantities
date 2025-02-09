package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.RawType
import io.github.frantoso.physicalquantities.utils.hash
import io.github.frantoso.physicalquantities.utils.toRawType

/**
 * A helper class to store the data needed for a scaling. Normally used by a prefix (e.g. 'k').
 *
 * @param T The type of the concrete instance.
 * @property quantity The quantity to scale.
 * @property scaleFactor The scale factor to apply to the quantities value.
 * @property symbolPrefix The prefix of the scale.
 */
class ScaledQuantity<T : QuantityBase>(
    val quantity: T,
    scaleFactor: Number,
    val symbolPrefix: String,
) {
    /**
     * Gets the scale factor to apply to the quantities value.
     */
    val scaleFactor: RawType = scaleFactor.toRawType()

    /**
     * Returns `true` if this instance is equal to [other]; `false` otherwise.
     */
    override fun equals(other: Any?): Boolean {
        val otherInstance = other as? ScaledQuantity<*> ?: return false

        return quantity == otherInstance.quantity &&
            symbolPrefix == otherInstance.symbolPrefix &&
            scaleFactor.compareTo(otherInstance.scaleFactor) == 0
    }

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int = quantity.hashCode() xor symbolPrefix.hashCode() xor scaleFactor.hash
}

/**
 * Creates a [ScaledQuantity] from the provided [quantity] and [prefix].
 */
fun <T : QuantityBase> scaledQuantity(
    quantity: T,
    prefix: Prefix,
): ScaledQuantity<T> = ScaledQuantity(quantity, prefix.factorFromBase, prefix.symbol)
