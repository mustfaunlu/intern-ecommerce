package com.mustafaunlu.ecommerce.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.data.categoryName
import com.mustafaunlu.ecommerce.data.productId
import com.mustafaunlu.ecommerce.data.query
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FakeRemoteRepositoryTest {

    private val fakeRemoteRepository = FakeRemoteRepository()

    /**
     * GetAllProducts test
     */

    @Test
    fun allProductsResponse_whenRemoteDataSourceReturnSuccess() {
        runBlocking {
            fakeRemoteRepository.getProductsListFromApi().test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun allProductsResponse_whenRemoteDataSourceReturnError() {
        runBlocking {
            fakeRemoteRepository.setShowErrorForAllProducts(true)
            fakeRemoteRepository.getProductsListFromApi().test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * GetSingleProductById test
     */

    @Test
    fun singleProductResponse_whenRemoteDataSourceReturnSuccess() {
        runBlocking {
            fakeRemoteRepository.getSingleProductByIdFromApi(productId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun singleProductResponse_whenRemoteDataSourceReturnError() {
        runBlocking {
            fakeRemoteRepository.setShowErrorForSingleProduct(true)
            fakeRemoteRepository.getSingleProductByIdFromApi(productId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * GetProductsListBySearch test
     */

    @Test
    fun searchProductsResponse_whenRemoteDataSourceReturnSuccess() {
        runBlocking {
            fakeRemoteRepository.getProductsListBySearchFromApi(query).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun searchProductsResponse_whenRemoteDataSourceReturnError() {
        runBlocking {
            fakeRemoteRepository.setShowErrorForAllProducts(true)
            fakeRemoteRepository.getProductsListBySearchFromApi(query).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    /**
     * GetProductListByCategoryName test
     */

    @Test
    fun categoryProductsResponse_whenRemoteDataSourceReturnSuccess() {
        runBlocking {
            fakeRemoteRepository.getProductsListByCategoryNameFromApi(categoryName).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun categoryProductsResponse_whenRemoteDataSourceReturnError() {
        runBlocking {
            fakeRemoteRepository.setShowErrorForAllProducts(true)
            fakeRemoteRepository.getProductsListByCategoryNameFromApi(categoryName).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

}