/**
 * This file sets the raw type to BigDecimal and provides functions and properties which
 * are different for the supported raw types (e.g. Double.sign and BigDecimal.signum()).
 */
package io.github.frantoso.physicalquantities.utils

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * Sets the raw type to [BigDecimal]
 */
typealias RawType = BigDecimal

/**
 * Converts this [Number] value to [BigDecimal].
 * @return Returns the resulting [BigDecimal] value represents the
 * same numerical value as this [Number].
 */
fun Number.toRawType(): BigDecimal = BigDecimal(toString())

/**
 * Divides this [BigDecimal] by the given [divisor].
 * Kotlin's [BigDecimal] provides a [BigDecimal.div] method, but with strange behaviour. That's why
 * we use the original [BigDecimal.divide] method from Java.
 *
 * The name of the function must be different from the [Double] and [BigDecimal] implementation
 * to have the same imports at the place of use.
 * The precision is limited to a decimal 128-bit number equivalent to prevent of exceptions at
 * divisions with infinite decimal places.
 */
fun BigDecimal.divideBy(divisor: BigDecimal): BigDecimal = divide(divisor, MathContext.DECIMAL128)

/**
 * Round this [BigDecimal] mathematically to the next [Int].
 */
fun BigDecimal.round(): Int = setScale(0, RoundingMode.HALF_EVEN).toInt()

/**
 * Gets the sign of this [BigDecimal].
 *
 * The name of the function must be different from the [Double] and [BigDecimal] implementation
 * to have the same imports at the place of use.
 *
 * @return -1, 0, or 1 as the value of this [BigDecimal] is negative, zero, or positive.
 */
val BigDecimal.sgn: Int get() = signum()

val BigDecimal.hash: Int get() = stripTrailingZeros().hashCode()
