package com.mustafaunlu.ecommerce.domain.usecase.product

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.productId
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetSingleProductUseCaseTest {
    private val getSingleProductUseCase = FakeGetSingleProductUseCase()

    @Test
    fun networkState_whenStateLoading_returnLoading() {
        runBlocking {
            getSingleProductUseCase(productId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially() {
        runBlocking {
            getSingleProductUseCase(productId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError() {
        runBlocking {
            getSingleProductUseCase.updateShowError(true)
            getSingleProductUseCase(productId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }
}