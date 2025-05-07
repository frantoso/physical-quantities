package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.quantity.Power
import io.github.frantoso.physicalquantities.quantity.W
import io.github.frantoso.physicalquantities.utils.divideBy
import io.github.frantoso.physicalquantities.utils.toRawType
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

// **** Power related calculations

/**
 * Computes a [Power] from this [Voltage] and the given [Current].
 */
operator fun Voltage.times(current: Current): Power = value.times(current.value).W

/**
 * Computes a [Power] from this [Current] and the given [Voltage].
 */
operator fun Current.times(voltage: Voltage): Power = value.times(voltage.value).W

/**
 * Computes a [Current] from this [Power] and the given [Voltage].
 */
operator fun Power.div(voltage: Voltage): Current = value.divideBy(voltage.value).A

/**
 * Computes a [Voltage] from this [Power] and the given [Current].
 */
operator fun Power.div(current: Current): Voltage = value.divideBy(current.value).V

// **** Resistance related calculations

/**
 * Computes a [Voltage] from this [Current] and the given [Resistance].
 */
operator fun Current.times(resistance: Resistance): Voltage = value.times(resistance.value).V

/**
 * Computes a [Voltage] from this [Resistance] and the given [Current].
 */
operator fun Resistance.times(current: Current): Voltage = value.times(current.value).V

/**
 * Computes a [Resistance] from this [Voltage] and the given [Current].
 */
operator fun Voltage.div(current: Current): Resistance = value.divideBy(current.value).O

/**
 * Computes a [Current] from this [Voltage] and the given [Resistance].
 */
operator fun Voltage.div(resistance: Resistance): Current = value.divideBy(resistance.value).A

// **** Charge related calculations

/**
 * Computes a [Charge] from this [Current] and the given [Duration].
 */
operator fun Current.times(duration: Duration): Charge = ampere.times(duration.toDouble(DurationUnit.SECONDS).toRawType()).C

/**
 * Computes a [Charge] from this [Duration] and the given [Current].
 */
operator fun Duration.times(current: Current): Charge = toDouble(DurationUnit.SECONDS).toRawType().times(current.ampere).C

/**
 * Computes a [Duration] from this [Charge] and the given [Current].
 */
operator fun Charge.div(current: Current): Duration = coulomb.divideBy(current.ampere).toDouble().seconds

/**
 * Computes a [Current] from this [Charge] and the given [Duration].
 */
operator fun Charge.div(duration: Duration): Current = coulomb.divideBy(duration.toDouble(DurationUnit.SECONDS).toRawType()).A
