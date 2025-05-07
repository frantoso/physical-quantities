package io.github.frantoso.physicalquantities.core

object Registry {
    val creators = mutableMapOf<String, (Number) -> QuantityBase>()

    internal inline fun <reified T : QuantityBase> add(
        creatorInfos: List<SimpleQuantity.CreatorInfo<T>>,
    ): List<SimpleQuantity.CreatorInfo<T>> {
        creatorInfos.forEach { creators[it.symbol] = it.creator }
        return creatorInfos
    }
}
