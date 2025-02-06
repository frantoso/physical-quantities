package io.github.frantoso.physicalquantities.core

import java.math.BigDecimal

// @formatter:off
@Suppress("ktlint")
enum class Prefix(
    val symbol: String,
    val factorToBase: BigDecimal,
    val factorFromBase: BigDecimal,
) {
    ATTO("a", 1e-18.toBigDecimal(), 1e18.toBigDecimal()),
    FEMTO("f", 1e-15.toBigDecimal(), 1e15.toBigDecimal()),
    PICO("p", 1e-12.toBigDecimal(), 1e12.toBigDecimal()),
    NANO("n", 1e-9.toBigDecimal(), 1e9.toBigDecimal()),
    MICRO("µ", 1e-6.toBigDecimal(), 1e6.toBigDecimal()),
    MILLI("m", 1e-3.toBigDecimal(), 1e3.toBigDecimal()),
    CENTI("c", 1e-2.toBigDecimal(), 1e2.toBigDecimal()),
    DECI("d", 0.1.toBigDecimal(), 10.0.toBigDecimal()),
    DECA("da", 10.0.toBigDecimal(), 0.1.toBigDecimal()),
    HECTO("h", 1e2.toBigDecimal(), 1e-2.toBigDecimal()),
    KILO("k", 1e3.toBigDecimal(), 1e-3.toBigDecimal()),
    MEGA("M", 1e6.toBigDecimal(), 1e-6.toBigDecimal()),
    GIGA("G", 1e9.toBigDecimal(), 1e-9.toBigDecimal()),
    TERA("T", 1e12.toBigDecimal(), 1e-12.toBigDecimal()),
    PETA("P", 1e15.toBigDecimal(), 1e-15.toBigDecimal()),
    EXA("E", 1e18.toBigDecimal(), 1e-18.toBigDecimal()),
    None("", 1.0.toBigDecimal(), 1.0.toBigDecimal()),
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
