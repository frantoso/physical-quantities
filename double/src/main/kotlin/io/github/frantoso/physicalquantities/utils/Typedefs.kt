/**
 * This file sets the raw type to Double and provides functions and properties which
 * are different for the supported raw types (e.g. Double.sign and BigDecimal.signum()).
 */
package io.github.frantoso.physicalquantities.utils

import java.math.BigDecimal
import kotlin.math.roundToInt
import kotlin.math.sign

/**
 * Sets the raw type to [Double]
 */
typealias RawType = Double

/**
 * Converts this [Number] value to [Double].
 * @return Returns the resulting [Double] value represents the
 * same numerical value as this [Number].
 */
fun Number.toRawType(): Double = toDouble()

/**
 * Divides this [Double] by the given [divisor]. This function is only to have the same name
 * for all raw types.
 *
 * The name of the function must be different from the [Double] and [BigDecimal] implementation
 * to have the same imports at the place of use.
 */
fun Double.divideBy(divisor: Double): Double = div(divisor)

/**
 * Round this [Double] mathematically to the next [Int].
 */
fun Double.round(): Int = kotlin.math.round(this).toInt() // roundToInt()

/**
 * Gets the sign of this [Double].
 *
 * The name of the function must be different from the [Double] and [BigDecimal] implementation
 * to have the same imports at the place of use.
 *
 * @return -1, 0, or 1 as the value of this [Double] is negative, zero, or positive.
 */
val Double.sgn: Int get() = sign.roundToInt()

val Double.hash: Int get() = hashCode()
