@file:Suppress("ktlint:standard:backing-property-naming", "ObjectPropertyName", "NonAsciiCharacters")

package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.utils.RawType
import io.github.frantoso.physicalquantities.utils.toRawType

val Number.atto: RawType get() = toRawType().times(Prefix.ATTO.factorToBase)
val Number.femto: RawType get() = toRawType().times(Prefix.FEMTO.factorToBase)
val Number.pico: RawType get() = toRawType().times(Prefix.PICO.factorToBase)
val Number.nano: RawType get() = toRawType().times(Prefix.NANO.factorToBase)
val Number.micro: RawType get() = toRawType().times(Prefix.MICRO.factorToBase)
val Number.milli: RawType get() = toRawType().times(Prefix.MILLI.factorToBase)
val Number.centi: RawType get() = toRawType().times(Prefix.CENTI.factorToBase)
val Number.deci: RawType get() = toRawType().times(Prefix.DECI.factorToBase)
val Number.deca: RawType get() = toRawType().times(Prefix.DECA.factorToBase)
val Number.hecto: RawType get() = toRawType().times(Prefix.HECTO.factorToBase)
val Number.kilo: RawType get() = toRawType().times(Prefix.KILO.factorToBase)
val Number.mega: RawType get() = toRawType().times(Prefix.MEGA.factorToBase)
val Number.giga: RawType get() = toRawType().times(Prefix.GIGA.factorToBase)
val Number.tera: RawType get() = toRawType().times(Prefix.TERA.factorToBase)
val Number.peta: RawType get() = toRawType().times(Prefix.PETA.factorToBase)
val Number.exa: RawType get() = toRawType().times(Prefix.EXA.factorToBase)

val Number._a get() = atto
val Number._f get() = femto
val Number._p get() = pico
val Number._n get() = nano
val Number._µ get() = micro
val Number._m get() = milli
val Number._c get() = centi
val Number._d get() = deci
val Number._da get() = deca
val Number._h get() = hecto
val Number._k get() = kilo
val Number._M get() = mega
val Number._G get() = giga
val Number._T get() = tera
val Number._P get() = peta
val Number._E get() = exa
