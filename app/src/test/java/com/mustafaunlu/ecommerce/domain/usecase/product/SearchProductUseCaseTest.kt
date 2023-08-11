package com.mustafaunlu.ecommerce.domain.usecase.product

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.query
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchProductUseCaseTest {
    private val fakeSearchProductUseCase = FakeSearchProductUseCase()

    @Test
    fun networkState_whenStateLoading_returnLoading() {
        runBlocking {
            fakeSearchProductUseCase(query).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun networkState_whenStateLoadingAndSuccess_returnLoadingAndSuccessSequentially(){
        runBlocking { fakeSearchProductUseCase(query).test {
            Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
            Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Success::class.java)
            awaitComplete()
        } }
    }

    @Test
    fun networkState_whenStateLoadingAndError_returnError(){
        runBlocking {
            fakeSearchProductUseCase.updateShowError(true)
            fakeSearchProductUseCase(query).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }
}