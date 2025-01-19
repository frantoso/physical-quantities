@file:Suppress("ktlint:standard:backing-property-naming", "ObjectPropertyName", "NonAsciiCharacters")

package io.github.franklisting65.physicalquantities.core

val Number.atto get() = this.toDouble() * Prefix.ATTO.factorToBase
val Number.femto get() = this.toDouble() * Prefix.FEMTO.factorToBase
val Number.pico get() = this.toDouble() * Prefix.PICO.factorToBase
val Number.nano get() = this.toDouble() * Prefix.NANO.factorToBase
val Number.micro get() = this.toDouble() * Prefix.MICRO.factorToBase
val Number.milli get() = this.toDouble() * Prefix.MILLI.factorToBase
val Number.centi get() = this.toDouble() * Prefix.CENTI.factorToBase
val Number.deci get() = this.toDouble() * Prefix.DECI.factorToBase
val Number.deca get() = this.toDouble() * Prefix.DECA.factorToBase
val Number.hecto get() = this.toDouble() * Prefix.HECTO.factorToBase
val Number.kilo get() = this.toDouble() * Prefix.KILO.factorToBase
val Number.mega get() = this.toDouble() * Prefix.MEGA.factorToBase
val Number.giga get() = this.toDouble() * Prefix.GIGA.factorToBase
val Number.tera get() = this.toDouble() * Prefix.TERA.factorToBase
val Number.peta get() = this.toDouble() * Prefix.PETA.factorToBase
val Number.exa get() = this.toDouble() * Prefix.EXA.factorToBase

val Number._a get() = this.atto
val Number._f get() = this.femto
val Number._p get() = this.pico
val Number._n get() = this.nano
val Number._µ get() = this.micro
val Number._m get() = this.milli
val Number._c get() = this.centi
val Number._d get() = this.deci
val Number._da get() = this.deca
val Number._h get() = this.hecto
val Number._k get() = this.kilo
val Number._M get() = this.mega
val Number._G get() = this.giga
val Number._T get() = this.tera
val Number._P get() = this.peta
val Number._E get() = this.exa
