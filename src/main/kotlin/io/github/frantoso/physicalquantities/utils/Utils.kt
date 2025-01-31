package io.github.frantoso.physicalquantities.utils

import java.text.NumberFormat
import java.util.Locale

private fun createDefaultNumberFormatter(): NumberFormat {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    formatter.maximumFractionDigits = 6
    formatter.minimumFractionDigits = 1
    return formatter
}

var defaultNumberFormatter = createDefaultNumberFormatter()
    private set

fun setDefaultNumberFormatter(formatter: NumberFormat) {
    defaultNumberFormatter = formatter
}

fun resetDefaultNumberFormatter() {
    defaultNumberFormatter = createDefaultNumberFormatter()
}
