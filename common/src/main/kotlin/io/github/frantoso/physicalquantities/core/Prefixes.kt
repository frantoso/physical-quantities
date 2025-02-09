@file:Suppress("ktlint:standard:backing-property-naming", "ObjectPropertyName", "NonAsciiCharacters")

package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.RawType
import io.github.frantoso.physicalquantities.utils.toRawType

val Number.atto: RawType get() = this.toRawType().times(Prefix.ATTO.factorToBase)
val Number.femto: RawType get() = this.toRawType().times(Prefix.FEMTO.factorToBase)
val Number.pico: RawType get() = this.toRawType().times(Prefix.PICO.factorToBase)
val Number.nano: RawType get() = this.toRawType().times(Prefix.NANO.factorToBase)
val Number.micro: RawType get() = this.toRawType().times(Prefix.MICRO.factorToBase)
val Number.milli: RawType get() = this.toRawType().times(Prefix.MILLI.factorToBase)
val Number.centi: RawType get() = this.toRawType().times(Prefix.CENTI.factorToBase)
val Number.deci: RawType get() = this.toRawType().times(Prefix.DECI.factorToBase)
val Number.deca: RawType get() = this.toRawType().times(Prefix.DECA.factorToBase)
val Number.hecto: RawType get() = this.toRawType().times(Prefix.HECTO.factorToBase)
val Number.kilo: RawType get() = this.toRawType().times(Prefix.KILO.factorToBase)
val Number.mega: RawType get() = this.toRawType().times(Prefix.MEGA.factorToBase)
val Number.giga: RawType get() = this.toRawType().times(Prefix.GIGA.factorToBase)
val Number.tera: RawType get() = this.toRawType().times(Prefix.TERA.factorToBase)
val Number.peta: RawType get() = this.toRawType().times(Prefix.PETA.factorToBase)
val Number.exa: RawType get() = this.toRawType().times(Prefix.EXA.factorToBase)

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
