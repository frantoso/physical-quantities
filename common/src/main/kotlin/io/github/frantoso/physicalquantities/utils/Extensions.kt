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
