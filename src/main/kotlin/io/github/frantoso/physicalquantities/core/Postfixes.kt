@file:Suppress("ktlint:standard:backing-property-naming", "ObjectPropertyName", "NonAsciiCharacters")

package io.github.frantoso.physicalquantities.core

val <T : QuantityBase> T.atto get() = scaledQuantity(this, Prefix.ATTO)
val <T : QuantityBase> T.femto get() = scaledQuantity(this, Prefix.FEMTO)
val <T : QuantityBase> T.pico get() = scaledQuantity(this, Prefix.PICO)
val <T : QuantityBase> T.nano get() = scaledQuantity(this, Prefix.NANO)
val <T : QuantityBase> T.micro get() = scaledQuantity(this, Prefix.MICRO)
val <T : QuantityBase> T.milli get() = scaledQuantity(this, Prefix.MILLI)
val <T : QuantityBase> T.centi get() = scaledQuantity(this, Prefix.CENTI)
val <T : QuantityBase> T.deci get() = scaledQuantity(this, Prefix.DECI)
val <T : QuantityBase> T.deca get() = scaledQuantity(this, Prefix.DECA)
val <T : QuantityBase> T.hecto get() = scaledQuantity(this, Prefix.HECTO)
val <T : QuantityBase> T.kilo get() = scaledQuantity(this, Prefix.KILO)
val <T : QuantityBase> T.mega get() = scaledQuantity(this, Prefix.MEGA)
val <T : QuantityBase> T.giga get() = scaledQuantity(this, Prefix.GIGA)
val <T : QuantityBase> T.tera get() = scaledQuantity(this, Prefix.TERA)
val <T : QuantityBase> T.peta get() = scaledQuantity(this, Prefix.PETA)
val <T : QuantityBase> T.exa get() = scaledQuantity(this, Prefix.EXA)

val <T : QuantityBase> T._a get() = this.atto
val <T : QuantityBase> T._f get() = this.femto
val <T : QuantityBase> T._p get() = this.pico
val <T : QuantityBase> T._n get() = this.nano
val <T : QuantityBase> T._µ get() = this.micro
val <T : QuantityBase> T._m get() = this.milli
val <T : QuantityBase> T._c get() = this.centi
val <T : QuantityBase> T._d get() = this.deci
val <T : QuantityBase> T._da get() = this.deca
val <T : QuantityBase> T._h get() = this.hecto
val <T : QuantityBase> T._k get() = this.kilo
val <T : QuantityBase> T._M get() = this.mega
val <T : QuantityBase> T._G get() = this.giga
val <T : QuantityBase> T._T get() = this.tera
val <T : QuantityBase> T._P get() = this.peta
val <T : QuantityBase> T._E get() = this.exa
