package com.mustafaunlu.ecommerce.data.mapper

import com.mustafaunlu.ecommerce.data.singleProductResponse
import com.mustafaunlu.ecommerce.domain.entity.product.DetailProductEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SingleProductEntityMapperTest {

    private val singleProductEntityMapper = SingleProductEntityMapper()

    private lateinit var detailProductEntity: DetailProductEntity

    @Before
    fun setUp() {
        detailProductEntity = singleProductEntityMapper.map(singleProductResponse)
    }

    @Test
    fun title_whenProductMappedWithName_isSame() {
        assertEquals(detailProductEntity.title, singleProductResponse.title)
    }

    @Test
    fun id_whenProductMappedWithId_isSame() {
        assertEquals(detailProductEntity.id, singleProductResponse.id)
    }

    @Test
    fun price_whenProductMappedWithPrice_isSame() {
        assertEquals(detailProductEntity.price, singleProductResponse.price.toString())
    }
}