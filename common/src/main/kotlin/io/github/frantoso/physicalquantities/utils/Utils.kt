package io.github.frantoso.physicalquantities.utils

import java.text.NumberFormat
import java.util.Locale

/**
 * Returns the default number formatter used at several places in the library.
 */
private fun createDefaultNumberFormatter(): NumberFormat {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    formatter.maximumFractionDigits = 6
    formatter.minimumFractionDigits = 1
    return formatter
}

/**
 * Gets the default number formatter used at several places in the library.
 * Can be changed using [setDefaultNumberFormatter].
 * Use [resetDefaultNumberFormatter] to reset it to the library default.
 */
var defaultNumberFormatter = createDefaultNumberFormatter()
    private set

/**
 * Sets the default number formatter to [formatter].
 */
fun setDefaultNumberFormatter(formatter: NumberFormat) {
    defaultNumberFormatter = formatter
}

/**
 * Resets the default number formatter to the library default.
 */
fun resetDefaultNumberFormatter() {
    defaultNumberFormatter = createDefaultNumberFormatter()
}

/**
 * Gets the default precision fraction used at several places in the library (initial value: 0.001).
 */
var defaultPrecisionFraction = 0.001.toRawType()
    private set

/**
 * Sets the default precision fraction to [precisionFraction].
 */
fun setDefaultPrecisionFraction(precisionFraction: RawType) {
    defaultPrecisionFraction = precisionFraction
}

/**
 * Resets the default precision fraction to the library default.
 */
fun resetDefaultPrecisionFraction() {
    defaultPrecisionFraction = 0.001.toRawType()
}
