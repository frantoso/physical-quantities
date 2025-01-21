package io.github.frantoso.physicalquantities.core

/**
 * A helper class to store the data needed for a scaling. Normally used by a prefix (e.g. 'k').
 *
 * @param T The type of the concrete instance.
 * @property quantity The quantity to scale.
 * @property scaleFactor The scale factor to apply to the quantities value.
 * @property symbolPrefix The prefix of the scale.
 */
data class ScaledQuantity<T : QuantityBase>(
    val quantity: T,
    val scaleFactor: Double,
    val symbolPrefix: String,
)

/**
 * Creates a [ScaledQuantity] from the provided [quantity] and [prefix].
 */
fun <T : QuantityBase> scaledQuantity(
    quantity: T,
    prefix: Prefix,
): ScaledQuantity<T> = ScaledQuantity(quantity, prefix.factorFromBase, prefix.symbol)
