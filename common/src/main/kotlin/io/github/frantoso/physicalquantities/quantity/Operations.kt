package io.github.frantoso.physicalquantities.quantity

import io.github.frantoso.physicalquantities.utils.divideBy
import io.github.frantoso.physicalquantities.utils.toRawType
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.times

// **** Energy related calculations

/**
 * Computes an [io.github.frantoso.physicalquantities.quantity.Energy] from this [Power] and the given [Duration].
 */
operator fun Power.times(duration: Duration): Energy = watt.times(duration.toDouble(DurationUnit.SECONDS).toRawType()).J

/**
 * Computes an [Energy] from this [Duration] and the given [Power].
 */
operator fun Duration.times(power: Power): Energy = toDouble(DurationUnit.SECONDS).toRawType().times(power.watt).J

/**
 * Computes a [Power] from this [Energy] and the given [Duration].
 */
operator fun Energy.div(duration: Duration): Power = joule.divideBy(duration.toDouble(DurationUnit.SECONDS).toRawType()).W

/**
 * Computes a [Duration] from this [Energy] and the given [Power].
 */
operator fun Energy.div(power: Power): Duration = joule.divideBy(power.watt).toDouble().seconds

// **** Concentration related calculations

/**
 * Computes a [MolarConcentration] from this [AmountOfSubstance] and the given [Volume].
 */
operator fun AmountOfSubstance.div(volume: Volume): MolarConcentration = mole.divideBy(volume.cubicMeter.toRawType()).molPerM3

/**
 * Computes an [AmountOfSubstance] from this [MolarConcentration] and the given [Volume].
 */
operator fun MolarConcentration.times(volume: Volume): AmountOfSubstance = molePerCubicMeters.times(volume.cubicMeter.toRawType()).mol

/**
 * Computes a [Volume] from this [AmountOfSubstance] and the given [MolarConcentration].
 */
operator fun AmountOfSubstance.div(concentration: MolarConcentration): Volume =
    mole.divideBy(concentration.molePerCubicMeters.toRawType()).m3
