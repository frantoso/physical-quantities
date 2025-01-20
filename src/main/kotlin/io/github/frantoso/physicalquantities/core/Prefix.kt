package io.github.frantoso.physicalquantities.core

// @formatter:off
@Suppress("ktlint")
enum class Prefix( val symbol: String, val factorToBase: Double, val factorFromBase: Double ) {
    ATTO("a", 1e-18, 1e18),
    FEMTO("f", 1e-15, 1e15),
    PICO("p", 1e-12, 1e12),
    NANO("n", 1e-9, 1e9),
    MICRO("µ", 1e-6, 1e6),
    MILLI("m", 1e-3, 1e3),
    CENTI("c", 1e-2, 1e2),
    DECI("d", 0.1, 10.0),
    DECA("da", 10.0, 0.1),
    HECTO("h", 1e2, 1e-2),
    KILO("k", 1e3, 1e-3),
    MEGA("M", 1e6, 1e-6),
    GIGA("G", 1e9, 1e-9),
    TERA("T", 1e12, 1e-12),
    PETA("P", 1e15, 1e-15),
    EXA("E", 1e18, 1e-18),
}
// @formatter:on
