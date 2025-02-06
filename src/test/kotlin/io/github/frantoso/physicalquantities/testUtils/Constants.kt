package io.github.frantoso.physicalquantities.testUtils

import io.github.frantoso.physicalquantities.utils.toBigDecimal
import org.assertj.core.data.Offset
import java.math.BigDecimal

val STANDARD_OFFSET: Offset<BigDecimal> = Offset.offset(0.000_000_000_000_001.toBigDecimal())
