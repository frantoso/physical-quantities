package io.github.frantoso.physicalquantities.core

import io.github.frantoso.physicalquantities.electrical.C
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class RegistryTest {
    @Test
    fun `checks whether the registry contains quantities`(){
        val p=42.C
        assertThat(Registry.creators.size).isGreaterThan(1)
    }
}
