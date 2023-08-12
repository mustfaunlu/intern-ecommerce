package com.mustafaunlu.ecommerce.data.source

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.allProductsResponse
import com.mustafaunlu.ecommerce.data.api.ApiService
import com.mustafaunlu.ecommerce.data.categoryName
import com.mustafaunlu.ecommerce.data.productId
import com.mustafaunlu.ecommerce.data.query
import com.mustafaunlu.ecommerce.data.singleProductResponse
import com.mustafaunlu.ecommerce.data.source.remote.RemoteDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RemoteDataSourceTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var remoteDataSource: RemoteDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteDataSource = RemoteDataSourceImpl(apiService)
    }

    /**
     * GetAllProducts
     */

    @Test
    fun allProductsResponse_whenApiReturnSuccess_isResponseStateSuccess() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListFromApi()).thenReturn(allProductsResponse)
            remoteDataSource.getProductsListFromApi().test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun allProuductResponse_whenApiReturnNull_isResponseStateError() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListFromApi()).thenReturn(null)
            remoteDataSource.getProductsListFromApi().test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * GetSingleProductById
     */

    @Test
    fun singleProductResponse_whenApiReturnSuccess_isResponseStateSuccess() {
        runBlocking {
            Mockito.`when`(apiService.getSingleProductByIdFromApi(productId)).thenReturn(
                singleProductResponse
            )
            remoteDataSource.getSingleProductByIdFromApi(productId).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun singleProductResponse_whenApiReturnNull_isResponseStateError() {
        runBlocking {
            Mockito.`when`(apiService.getSingleProductByIdFromApi(productId)).thenReturn(null)
            remoteDataSource.getSingleProductByIdFromApi(productId).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * GetProductsListBySearch
     */
    @Test
    fun searchProductResponse_whenApiReturnSuccess_isResponseStateSuccess() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListBySearchFromApi(query)).thenReturn(
                allProductsResponse
            )
            remoteDataSource.getProductsListBySearchFromApi(query).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun searchProductResponse_whenApiReturnNull_isResponseStateError() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListBySearchFromApi(query)).thenReturn(null)
            remoteDataSource.getProductsListBySearchFromApi(query).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * GetProductsListByCategoryName
     */
    @Test
    fun categoryProductResponse_whenApiReturnSuccess_isResponseStateSuccess() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListByCategoryNameFromApi(categoryName)).thenReturn(
                allProductsResponse
            )
            remoteDataSource.getProductsListByCategoryNameFromApi(categoryName).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun categoryProductResponse_whenApiReturnNull_isResponseStateError() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListByCategoryNameFromApi(categoryName)).thenReturn(null)
            remoteDataSource.getProductsListByCategoryNameFromApi(categoryName).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

}