package io.github.franklisting65.physicalquantities.electrical

// **** Power related calculations

/**
 * Computes the power from the given current and voltage values.
 * @param current The current value.
 * @return Returns the computed power.
 */
operator fun Voltage.times(current: Current): Power = Power.fromWatt(value * current.value)

/**
 * Computes the power from the given current and voltage values.
 * @param voltage The voltage value.
 * @return Returns the computed power.
 */
operator fun Current.times(voltage: Voltage): Power = Power.fromWatt(value * voltage.value)

/**
 * Computes the current from the given power and voltage values.
 * @param voltage The voltage value.
 * @return Returns the computed current.
 */
operator fun Power.div(voltage: Voltage): Current = Current.fromAmpere(value / voltage.value)

/**
 * Computes the voltage from the given power and current values.
 * @param current The current value.
 * @return Returns the computed voltage.
 */
operator fun Power.div(current: Current): Voltage = Voltage.fromVolt(value / current.value)
