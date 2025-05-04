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
