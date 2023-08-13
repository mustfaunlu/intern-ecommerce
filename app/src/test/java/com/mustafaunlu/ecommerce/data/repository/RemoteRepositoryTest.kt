package com.mustafaunlu.ecommerce.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.allProductsResponse
import com.mustafaunlu.ecommerce.data.api.ApiService
import com.mustafaunlu.ecommerce.data.categoryName
import com.mustafaunlu.ecommerce.data.mapper.ProductEntityMapper
import com.mustafaunlu.ecommerce.data.mapper.SingleProductEntityMapper
import com.mustafaunlu.ecommerce.data.productId
import com.mustafaunlu.ecommerce.data.query
import com.mustafaunlu.ecommerce.data.singleProductResponse
import com.mustafaunlu.ecommerce.data.source.remote.RemoteDataSource
import com.mustafaunlu.ecommerce.data.source.remote.RemoteDataSourceImpl
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RemoteRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var remoteRepository: RemoteRepository
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteDataSource = RemoteDataSourceImpl(apiService)
        remoteRepository = RemoteRepositoryImpl(
            remoteDataSource,
            allProductsMapper = ProductEntityMapper(),
            singleProductMapper = SingleProductEntityMapper(),
        )
    }

    /**
     * GetAll Products Test
     */

    @Test
    fun allProductsResponse_whenRemoteDataSourceReturnsSuccess() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListFromApi()).thenReturn(allProductsResponse)
            remoteRepository.getProductsListFromApi().test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun allProductsResponse_whenRemoteDataSourceReturnError() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListFromApi()).thenReturn(null)
            remoteRepository.getProductsListFromApi().test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * Get Single Product Test
     */

    @Test
    fun singleProductResponse_whenRemoteDataSourceReturnsSuccess() {
        runBlocking {
            Mockito.`when`(apiService.getSingleProductByIdFromApi(productId)).thenReturn(
                singleProductResponse)
            remoteRepository.getSingleProductByIdFromApi(productId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun singleProductResponse_whenRemoteDataSourceReturnError() {
        runBlocking {
            Mockito.`when`(apiService.getSingleProductByIdFromApi(productId)).thenReturn(null)
            remoteRepository.getSingleProductByIdFromApi(productId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * GetSearchProducts Test
     */

    @Test
    fun searchProductsResponse_whenRemoteDataSourceReturnsSuccess() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListBySearchFromApi(query)).thenReturn(
                allProductsResponse)
            remoteRepository.getProductsListBySearchFromApi(query).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun searchProductsResponse_whenRemoteDataSourceReturnError() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListBySearchFromApi(query)).thenReturn(null)
            remoteRepository.getProductsListBySearchFromApi(query).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * GetCategoryProducts Test
     */

    @Test
    fun categoryProductsResponse_whenRemoteDataSourceReturnsSuccess() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListByCategoryNameFromApi(categoryName)).thenReturn(
                allProductsResponse)
            remoteRepository.getProductsListByCategoryNameFromApi(categoryName).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun categoryProductsResponse_whenRemoteDataSourceReturnError() {
        runBlocking {
            Mockito.`when`(apiService.getProductsListByCategoryNameFromApi(categoryName)).thenReturn(null)
            remoteRepository.getProductsListByCategoryNameFromApi(categoryName).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }
}