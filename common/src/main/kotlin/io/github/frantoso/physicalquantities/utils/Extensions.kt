package io.github.frantoso.physicalquantities.utils

import io.github.frantoso.physicalquantities.core.QuantityBase

/**
 * Returns `true` if the absolute difference between this instance and [other] is smaller than [defaultPrecisionFraction];
 * `false` otherwise.
 */
infix fun QuantityBase?.eqByPrecision(other: QuantityBase?): Boolean {
    if (this == null && other == null) {
        return true
    }

    if (this == null || other == null) {
        return false
    }

    return (this::class == other::class) && equalsByPrecision(other)
}

/**
 * Returns `true` if this value is equals or greater than [min] and equals or less than [max]; false otherwise.
 * If a parameter is `null`, this limit is not considered.
 */
fun QuantityBase.isInRange(
    min: QuantityBase? = null,
    max: QuantityBase? = null,
): Boolean =
    when {
        min == null && max == null -> true
        min != null && this::class != min::class -> false
        max != null && this::class != max::class -> false
        min != null && this.value < min.value -> false
        max != null && this.value > max.value -> false

        else -> true
    }
