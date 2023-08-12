package com.mustafaunlu.ecommerce.ui.detail

import com.mustafaunlu.ecommerce.ui.detailProductEntity
import com.mustafaunlu.ecommerce.ui.detailProductUiData
import com.mustafaunlu.ecommerce.ui.mapper.DetailProductEntityToUiMapper
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductDetailUiMapperTest {

    private val detailUiMapper = DetailProductEntityToUiMapper()

    private lateinit var productDetailUiData: DetailProductUiData

    @Before
    fun setUp() {
        productDetailUiData = detailUiMapper.map(detailProductEntity)
    }

    @Test
    fun title_whenProductMappedWithTitle_isSame() {
        Assert.assertEquals(detailProductUiData.title, productDetailUiData.title)
    }

    @Test
    fun image_whenProductMappedWithImage_isSame() {
        Assert.assertEquals(detailProductUiData.imageUrl, productDetailUiData.imageUrl)
    }

    @Test
    fun price_whenProductMappedWithPrice_isSame() {
        Assert.assertEquals(detailProductUiData.price, productDetailUiData.price)
    }

    @Test
    fun description_whenProductMappedWithDescription_isSame() {
        Assert.assertEquals(detailProductUiData.description, productDetailUiData.description)
    }

    @Test
    fun id_whenProductMappedWithId_isSame() {
        Assert.assertEquals(detailProductUiData.id, productDetailUiData.id)
    }

    @Test
    fun rating_whenProductMappedWithRating_isSame() {
        Assert.assertEquals(detailProductUiData.rating, productDetailUiData.rating)
    }
}