@file:Suppress("ktlint:standard:backing-property-naming", "ObjectPropertyName", "NonAsciiCharacters")

package io.github.franklisting65.physicalquantities.core

val Number.atto: Double get() = this.toDouble() / 1000000000000000000.0
val Number.femto: Double get() = this.toDouble() / 1000000000000000.0
val Number.pico: Double get() = this.toDouble() / 1000000000000.0
val Number.nano: Double get() = this.toDouble() / 1000000000.0
val Number.micro: Double get() = this.toDouble() / 1000000.0
val Number.milli: Double get() = this.toDouble() / 1000.0
val Number.centi: Double get() = this.toDouble() / 100.0
val Number.deci: Double get() = this.toDouble() / 10.0
val Number.kilo: Double get() = this.toDouble() * 1000.0
val Number.mega: Double get() = this.toDouble() * 1000000.0
val Number.giga: Double get() = this.toDouble() * 1000000000.0
val Number.tera: Double get() = this.toDouble() * 1000000000000.0
val Number.peta: Double get() = this.toDouble() * 1000000000000000.0
val Number.exa: Double get() = this.toDouble() * 1000000000000000000.0

val Number._a: Double get() = this.atto
val Number._f: Double get() = this.femto
val Number._p: Double get() = this.pico
val Number._n: Double get() = this.nano
val Number._µ: Double get() = this.micro
val Number._m: Double get() = this.milli
val Number._c: Double get() = this.centi
val Number._d: Double get() = this.deci
val Number._k: Double get() = this.kilo
val Number._M: Double get() = this.mega
val Number._G: Double get() = this.giga
val Number._T: Double get() = this.tera
val Number._P: Double get() = this.peta
val Number._E: Double get() = this.exa
