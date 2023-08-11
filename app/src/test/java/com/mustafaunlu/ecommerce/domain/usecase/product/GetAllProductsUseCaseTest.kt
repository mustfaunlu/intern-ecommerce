package com.mustafaunlu.ecommerce.domain.usecase.product

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.categoryName
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetAllProductsUseCaseTest {

    private val getAllProductUseCase = FakeGetAllProductUseCase()

    @Test
    fun networkState_whenStateLoading_returnLoading() {
        runBlocking {
            getAllProductUseCase().test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            getAllProductUseCase().test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            getAllProductUseCase.updateShowError(true)
            getAllProductUseCase().test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoading_returnLoading_byCategoryName() {
        runBlocking {
            getAllProductUseCase(categoryName).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially_byCategoryName() {
        runBlocking {
            getAllProductUseCase(categoryName).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError_byCategoryName() {
        runBlocking {
            getAllProductUseCase.updateShowError(true)
            getAllProductUseCase(categoryName).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }

}