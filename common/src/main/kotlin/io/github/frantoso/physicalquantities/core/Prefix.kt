package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.RawType
import io.github.frantoso.physicalquantities.utils.toRawType

// @formatter:off
@Suppress("ktlint")
enum class Prefix(
    val symbol: String,
    val factorToBase: RawType,
    val factorFromBase: RawType,
) {
    ATTO("a", 1e-18.toRawType(), 1e18.toRawType()),
    FEMTO("f", 1e-15.toRawType(), 1e15.toRawType()),
    PICO("p", 1e-12.toRawType(), 1e12.toRawType()),
    NANO("n", 1e-9.toRawType(), 1e9.toRawType()),
    MICRO("µ", 1e-6.toRawType(), 1e6.toRawType()),
    MILLI("m", 1e-3.toRawType(), 1e3.toRawType()),
    CENTI("c", 1e-2.toRawType(), 1e2.toRawType()),
    DECI("d", 0.1.toRawType(), 10.0.toRawType()),
    DECA("da", 10.0.toRawType(), 0.1.toRawType()),
    HECTO("h", 1e2.toRawType(), 1e-2.toRawType()),
    KILO("k", 1e3.toRawType(), 1e-3.toRawType()),
    MEGA("M", 1e6.toRawType(), 1e-6.toRawType()),
    GIGA("G", 1e9.toRawType(), 1e-9.toRawType()),
    TERA("T", 1e12.toRawType(), 1e-12.toRawType()),
    PETA("P", 1e15.toRawType(), 1e-15.toRawType()),
    EXA("E", 1e18.toRawType(), 1e-18.toRawType()),
    None("", 1.0.toRawType(), 1.0.toRawType()),
}
// @formatter:on

/**
 * Converts a string to a prefix.
 * @throws NoSuchPrefixException in case of an unknown prefix string.
 */
fun String.toPrefix(): Prefix =
    enumValues<Prefix>().firstOrNull { it.name == this.uppercase() }
        ?: enumValues<Prefix>().firstOrNull { it.symbol == this }
        ?: throw NoSuchPrefixException("Unknown prefix $this")
