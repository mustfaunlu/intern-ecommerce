package com.mustafaunlu.ecommerce.ui.home

import com.mustafaunlu.ecommerce.ui.mapper.ProductEntityToUiMapper
import com.mustafaunlu.ecommerce.ui.productEntityList
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductHomeUiMapperTest {

    private val productHomeUiMapper = ProductEntityToUiMapper()

    private lateinit var productHomeUiList: List<ProductUiData>

    @Before
    fun setUp() {
        productHomeUiList = productHomeUiMapper.map(productEntityList)
    }

    @Test
    fun productListSize_whenProductMapped_isSameSize() {
        Assert.assertEquals(productHomeUiList.size, productEntityList.size)
    }

    @Test
    fun title_whenProductMapped_isSame() {
        Assert.assertEquals(productHomeUiList.first().title, productEntityList.first().title)
    }

    @Test
    fun description_whenProductMapped_isSame() {
        Assert.assertEquals(productHomeUiList.first().description, productEntityList.first().description)
    }

    @Test
    fun price_whenProductMapped_isSame() {
        Assert.assertEquals(productHomeUiList.first().price, productEntityList.first().price)
    }

    @Test
    fun id_whenProductMapped_isSame() {
        Assert.assertEquals(productHomeUiList.first().id, productEntityList.first().id)
    }
}