package com.mustafaunlu.ecommerce.presentation.mapper

import com.mustafaunlu.ecommerce.presentation.home.AllProductsUiData
import com.mustafaunlu.ecommerce.domain.entity.AllProductsEntity
import com.mustafaunlu.ecommerce.domain.mapper.ProductListMapper
import javax.inject.Inject

class ProductHomeUiMapper @Inject constructor() :
    ProductListMapper<AllProductsEntity, AllProductsUiData> {
    override fun map(input: List<AllProductsEntity>): List<AllProductsUiData> {
        return input.map {
            AllProductsUiData(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
            )
        }
    }
}
