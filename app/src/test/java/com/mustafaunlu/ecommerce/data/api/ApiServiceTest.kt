package com.mustafaunlu.ecommerce.data.api

import com.google.common.truth.Truth.assertThat
import com.mustafaunlu.ecommerce.data.ALL_PRODUCTS_RESPONSE_FILE_NAME
import com.mustafaunlu.ecommerce.data.CATEGORY_PRODUCTS_RESPONSE_FILE_NAME
import com.mustafaunlu.ecommerce.data.SEARCH_RESPONSE_FILE_NAME
import com.mustafaunlu.ecommerce.data.SERVER_PORT
import com.mustafaunlu.ecommerce.data.SINGLE_PRODUCT_RESPONSE_FILE_NAME
import com.mustafaunlu.ecommerce.data.categoryName
import com.mustafaunlu.ecommerce.data.productId
import com.mustafaunlu.ecommerce.data.query
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServiceTest {
    private val mockWebServer: MockWebServer = MockWebServer()

    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        mockWebServer.start(port = SERVER_PORT)
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
            .create(ApiService::class.java)
    }

    /**
     * AllProducts Test
     */
    @Test
    fun response_whenAllProductsSeached_isNotNull() {
        runBlocking {
            enqueueMockResponse(ALL_PRODUCTS_RESPONSE_FILE_NAME)
            val response = apiService.getProductsListFromApi()
            mockWebServer.takeRequest()
            assertThat(response).isNotNull()
        }
    }

    @Test
    fun requestPath_whenAllProductsRequested_isSameWithRequest() {
        runBlocking {
            enqueueMockResponse(ALL_PRODUCTS_RESPONSE_FILE_NAME)
            apiService.getProductsListFromApi()
            val request = mockWebServer.takeRequest()
            assertThat(request.path).isEqualTo("/products")
        }
    }

    @Test
    fun firstElement_whenAllProductsRequested_hasSameTitle() {
        runBlocking {
            enqueueMockResponse(ALL_PRODUCTS_RESPONSE_FILE_NAME)
            val response = apiService.getProductsListFromApi()
            val firstItem = response.products.first()
            assertThat(firstItem.title).isEqualTo("iPhone 9")
        }
    }

    /**
     * ProductDetail Test
     */
    @Test
    fun response_whenProductDetailSeached_isNotNull() {
        runBlocking {
            enqueueMockResponse(SINGLE_PRODUCT_RESPONSE_FILE_NAME)
            val response = apiService.getSingleProductByIdFromApi(productId)
            mockWebServer.takeRequest()
            assertThat(response).isNotNull()
        }
    }

    @Test
    fun requestPath_whenProductDetailRequested_isSameWithRequest() {
        runBlocking {
            enqueueMockResponse(SINGLE_PRODUCT_RESPONSE_FILE_NAME)
            apiService.getSingleProductByIdFromApi(productId)
            val request = mockWebServer.takeRequest()
            assertThat(request.path).isEqualTo("/products/$productId")
        }
    }

    @Test
    fun firstElement_whenProductDetailRequested_hasSameTitle() {
        runBlocking {
            enqueueMockResponse(SINGLE_PRODUCT_RESPONSE_FILE_NAME)
            val response = apiService.getSingleProductByIdFromApi(productId)
            assertThat(response.title).isEqualTo("iPhone 9")
        }
    }

    /**
     * SearchProducts Test
     */

    @Test
    fun response_whenSearchProductsSeached_isNotNull() {
        runBlocking {
            enqueueMockResponse(SEARCH_RESPONSE_FILE_NAME)
            val response = apiService.getProductsListBySearchFromApi(query)
            mockWebServer.takeRequest()
            assertThat(response).isNotNull()
        }
    }

    @Test
    fun requestPath_whenSearchProductsRequested_isSameWithRequest() {
        runBlocking {
            enqueueMockResponse(SEARCH_RESPONSE_FILE_NAME)
            apiService.getProductsListBySearchFromApi(query)
            val request = mockWebServer.takeRequest()
            assertThat(request.path).isEqualTo("/products/search?q=$query")
        }
    }

    @Test
    fun firstElement_whenSearchProductsRequested_hasSameTitle() {
        runBlocking {
            enqueueMockResponse(SEARCH_RESPONSE_FILE_NAME)
            val response = apiService.getProductsListBySearchFromApi(query)
            val firstItem = response.products.first()
            assertThat(firstItem.title).isEqualTo("Women Shoes")
        }
    }

    /**
     * CategoryProducts Test
     */

    @Test
    fun response_whenCategoryProductsSeached_isNotNull() {
        runBlocking {
            enqueueMockResponse(CATEGORY_PRODUCTS_RESPONSE_FILE_NAME)
            val response = apiService.getProductsListByCategoryNameFromApi(categoryName)
            mockWebServer.takeRequest()
            assertThat(response).isNotNull()
        }
    }

    @Test
    fun requestPath_whenCategoryProductsRequested_isSameWithRequest() {
        runBlocking {
            enqueueMockResponse(CATEGORY_PRODUCTS_RESPONSE_FILE_NAME)
            apiService.getProductsListByCategoryNameFromApi(categoryName)
            val request = mockWebServer.takeRequest()
            assertThat(request.path).isEqualTo("/products/category/$categoryName")
        }
    }

    @Test
    fun firstElement_whenCategoryProductsRequested_hasSameTitle() {
        runBlocking {
            enqueueMockResponse(CATEGORY_PRODUCTS_RESPONSE_FILE_NAME)
            val response = apiService.getProductsListByCategoryNameFromApi(categoryName)
            val firstItem = response.products.first()
            assertThat(firstItem.title).isEqualTo("iPhone 9")
        }
    }


    private fun enqueueMockResponse(serverResponseFileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(serverResponseFileName)
                val source = inputStream.source().buffer()
                val mockResponse = MockResponse()
                mockResponse.setBody(source.readString(Charsets.UTF_8))
                mockWebServer.enqueue(mockResponse)
        }
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }
}