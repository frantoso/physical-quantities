package io.github.frantoso.physicalquantities.core

// @formatter:off
@Suppress("ktlint")
enum class Prefix( val symbol: String, val factorToBase: Double, val factorFromBase: Double ) {
    ATTO("a", 0.000_000_000_000_000_001, 1_000_000_000_000_000_000.0),
    FEMTO("f", 0.000_000_000_000_001, 1_000_000_000_000_000.0),
    PICO("p", 0.000_000_000_001, 1_000_000_000_000.0),
    NANO("n", 0.000_000_001, 1_000_000_000.0),
    MICRO("µ", 0.000_001, 1_000_000.0),
    MILLI("m", 0.001, 1_000.0),
    CENTI("c", 0.01, 100.0),
    DECI("d", 0.1, 10.0),
    DECA("da", 10.0, 0.1),
    HECTO("h", 100.0, 0.01),
    KILO("k", 1_000.0, 0.001),
    MEGA("M", 1_000_000.0, 0.000_001),
    GIGA("G", 1_000_000_000.0, 0.000_000_001),
    TERA("T", 1_000_000_000_000.0, 0.000_000_000_001),
    PETA("P", 1_000_000_000_000_000.0, 0.000_000_000_000_001),
    EXA("E", 1_000_000_000_000_000_000.0, 0.000_000_000_000_000_001),
}
// @formatter:on
