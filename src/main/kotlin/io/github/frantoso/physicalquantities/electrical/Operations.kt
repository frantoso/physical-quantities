package io.github.frantoso.physicalquantities.electrical

import io.github.frantoso.physicalquantities.utils.toBigDecimal
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

// **** Power related calculations

/**
 * Computes a [Power] from this [Voltage] and the given [Current].
 */
operator fun Voltage.times(current: Current): Power = value.multiply(current.value).W

/**
 * Computes a [Power] from this [Current] and the given [Voltage].
 */
operator fun Current.times(voltage: Voltage): Power = value.multiply(voltage.value).W

/**
 * Computes a [Current] from this [Power] and the given [Voltage].
 */
operator fun Power.div(voltage: Voltage): Current = value.divide(voltage.value).A

/**
 * Computes a [Voltage] from this [Power] and the given [Current].
 */
operator fun Power.div(current: Current): Voltage = value.divide(current.value).V

// **** Resistance related calculations

/**
 * Computes a [Voltage] from this [Current] and the given [Resistance].
 */
operator fun Current.times(resistance: Resistance): Voltage = value.multiply(resistance.value).V

/**
 * Computes a [Voltage] from this [Resistance] and the given [Current].
 */
operator fun Resistance.times(current: Current): Voltage = value.multiply(current.value).V

/**
 * Computes a [Resistance] from this [Voltage] and the given [Current].
 */
operator fun Voltage.div(current: Current): Resistance = value.divide(current.value).O

/**
 * Computes a [Current] from this [Voltage] and the given [Resistance].
 */
operator fun Voltage.div(resistance: Resistance): Current = value.divide(resistance.value).A

// **** Energy related calculations

/**
 * Computes an [Energy] from this [Power] and the given [Duration].
 */
operator fun Power.times(duration: Duration): Energy = watt.multiply(duration.toDouble(DurationUnit.SECONDS).toBigDecimal()).J

/**
 * Computes an [Energy] from this [Duration] and the given [Power].
 */
operator fun Duration.times(power: Power): Energy = toDouble(DurationUnit.SECONDS).toBigDecimal().multiply(power.watt).J

/**
 * Computes a [Power] from this [Energy] and the given [Duration].
 */
operator fun Energy.div(duration: Duration): Power = joule.divide(duration.toDouble(DurationUnit.SECONDS).toBigDecimal()).W

/**
 * Computes a [Duration] from this [Energy] and the given [Power].
 */
operator fun Energy.div(power: Power): Duration = joule.divide(power.watt).toDouble().seconds

// **** Charge related calculations

/**
 * Computes a [Charge] from this [Current] and the given [Duration].
 */
operator fun Current.times(duration: Duration): Charge = ampere.multiply(duration.toDouble(DurationUnit.SECONDS).toBigDecimal()).C

/**
 * Computes a [Charge] from this [Duration] and the given [Current].
 */
operator fun Duration.times(current: Current): Charge = toDouble(DurationUnit.SECONDS).toBigDecimal().multiply(current.ampere).C

/**
 * Computes a [Duration] from this [Charge] and the given [Current].
 */
operator fun Charge.div(current: Current): Duration = coulomb.divide(current.ampere).toDouble().seconds

/**
 * Computes a [Current] from this [Charge] and the given [Duration].
 */
operator fun Charge.div(duration: Duration): Current = coulomb.divide(duration.toDouble(DurationUnit.SECONDS).toBigDecimal()).A
