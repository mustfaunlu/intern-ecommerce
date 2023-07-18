package com.mustafaunlu.ecommerce.presenter.mapper

import com.mustafaunlu.ecommerce.common.SingleProductUiData
import com.mustafaunlu.ecommerce.domain.entity.SingleProductEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class ProductDetailUiMapper @Inject constructor() :
    ProductBaseMapper<SingleProductEntity, SingleProductUiData> {
    override fun map(input: SingleProductEntity): SingleProductUiData {
        return SingleProductUiData(
            id = input.id,
            title = input.title,
            description = input.description,
            price = input.price,
            imageUrl = input.imageUrl,
            rating = input.rating,
        )
    }
}
