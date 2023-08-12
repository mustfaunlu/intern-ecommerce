package com.mustafaunlu.ecommerce.data.mapper

import com.mustafaunlu.ecommerce.data.productList
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductEntityMapperTest {

    private val productEntityMapper = ProductEntityMapper()

    private lateinit var allProductEntities: List<ProductEntity>

    @Before
    fun setUp() {
        allProductEntities = productEntityMapper.map(productList)
    }

    @Test
    fun productListSize_whenProductMapped_isSameSize() {
        assertEquals(allProductEntities.size, productList.size)
    }

    @Test
    fun title_whenProductMappedWithName_isSame() {
        assertEquals(allProductEntities.first().title, productList.first().title)
    }

    @Test
    fun id_whenProductMappedWithId_isSame() {
        assertEquals(allProductEntities.first().id, productList.first().id)
    }

}