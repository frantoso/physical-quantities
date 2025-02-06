@file:Suppress("ktlint:standard:backing-property-naming", "ObjectPropertyName", "NonAsciiCharacters")

package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.toBigDecimal
import java.math.BigDecimal

val Number.atto: BigDecimal get() = toBigDecimal().multiply(Prefix.ATTO.factorToBase)
val Number.femto: BigDecimal get() = toBigDecimal().multiply(Prefix.FEMTO.factorToBase)
val Number.pico: BigDecimal get() = toBigDecimal().multiply(Prefix.PICO.factorToBase)
val Number.nano: BigDecimal get() = toBigDecimal().multiply(Prefix.NANO.factorToBase)
val Number.micro: BigDecimal get() = toBigDecimal().multiply(Prefix.MICRO.factorToBase)
val Number.milli: BigDecimal get() = toBigDecimal().multiply(Prefix.MILLI.factorToBase)
val Number.centi: BigDecimal get() = toBigDecimal().multiply(Prefix.CENTI.factorToBase)
val Number.deci: BigDecimal get() = toBigDecimal().multiply(Prefix.DECI.factorToBase)
val Number.deca: BigDecimal get() = toBigDecimal().multiply(Prefix.DECA.factorToBase)
val Number.hecto: BigDecimal get() = toBigDecimal().multiply(Prefix.HECTO.factorToBase)
val Number.kilo: BigDecimal get() = toBigDecimal().multiply(Prefix.KILO.factorToBase)
val Number.mega: BigDecimal get() = toBigDecimal().multiply(Prefix.MEGA.factorToBase)
val Number.giga: BigDecimal get() = toBigDecimal().multiply(Prefix.GIGA.factorToBase)
val Number.tera: BigDecimal get() = toBigDecimal().multiply(Prefix.TERA.factorToBase)
val Number.peta: BigDecimal get() = toBigDecimal().multiply(Prefix.PETA.factorToBase)
val Number.exa: BigDecimal get() = toBigDecimal().multiply(Prefix.EXA.factorToBase)

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
