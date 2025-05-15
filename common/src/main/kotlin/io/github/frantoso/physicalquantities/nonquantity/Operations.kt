package io.github.frantoso.physicalquantities.nonquantity

import io.github.frantoso.physicalquantities.core.QuantityBase
import io.github.frantoso.physicalquantities.core.SimpleQuantity
import io.github.frantoso.physicalquantities.utils.toRawType

/**
 * Multiplies this value by [scale].
 */
inline operator fun <reified QT : QuantityBase, reified DT : QuantityBase> SimpleQuantity<QT, DT>.times(scale: Scale): QT =
    times(scale.scale.toRawType())

inline operator fun <reified QT : QuantityBase, reified DT : QuantityBase> Scale.times(quantity: SimpleQuantity<QT, DT>): QT =
    quantity.times(scale.toRawType())

/**
 * Divides this value by [scale].
 */
inline operator fun <reified QT : QuantityBase, reified DT : QuantityBase> SimpleQuantity<QT, DT>.div(scale: Scale): QT =
    div(scale.scale.toRawType())
