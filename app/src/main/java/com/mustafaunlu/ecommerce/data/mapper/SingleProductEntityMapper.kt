package com.mustafaunlu.ecommerce.data.mapper

import com.mustafaunlu.ecommerce.data.dto.Product
import com.mustafaunlu.ecommerce.domain.entity.SingleProductEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductBaseMapper
import javax.inject.Inject

class SingleProductEntityMapper @Inject constructor() :
    ProductBaseMapper<Product, SingleProductEntity> {
    override fun map(input: Product): SingleProductEntity {
        return SingleProductEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            price = input.price.toString(),
            imageUrl = input.images,
            rating = input.rating.toString(),
        )
    }
}
