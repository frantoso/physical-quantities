package io.github.frantoso.physicalquantities.testUtils

import io.github.frantoso.physicalquantities.utils.RawType
import io.github.frantoso.physicalquantities.utils.toRawType
import org.assertj.core.data.Offset

val STANDARD_OFFSET: Offset<RawType> = Offset.offset(0.000_000_000_1.toRawType())
