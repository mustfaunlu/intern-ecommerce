package com.mustafaunlu.ecommerce.ui.home

import androidx.lifecycle.asFlow
import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.domain.usecase.cart.badge.UserCartBadgeUseCase
import com.mustafaunlu.ecommerce.domain.usecase.category.CategoryUseCase
import com.mustafaunlu.ecommerce.ui.mapper.ProductEntityToUiMapper
import com.mustafaunlu.ecommerce.ui.productEntityList
import com.mustafaunlu.ecommerce.ui.productHomeUiDataList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HomeViewModelTest {

    @Mock
    private lateinit var getFakeGetAllProductsUseCase: FakeGetAllProductsUseCase

    @Mock
    private lateinit var fakeSearchProductUseCase: FakeSearchProductUseCase

    @Mock
    private lateinit var categoryUseCase: CategoryUseCase

    @Mock
    private lateinit var userCartBadgeUseCase: UserCartBadgeUseCase

    private val productMapper = ProductEntityToUiMapper()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        homeViewModel = HomeViewModel(
            getFakeGetAllProductsUseCase,
            categoryUseCase,
            fakeSearchProductUseCase,
            productMapper,
            userCartBadgeUseCase
        )
    }

    @Test
    fun state_whenUseCaseReturnsLoading_isLoading() {
        runTest {
            val resultList = listOf(NetworkResponseState.Loading)
            whenever(getFakeGetAllProductsUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult =
                mutableListOf<ScreenState<List<ProductUiData>>>(ScreenState.Loading)

            val job = launch {
                homeViewModel.products.asFlow().toList(listOfEmittedResult)
            }
            verify(getFakeGetAllProductsUseCase).invoke()
            assert(listOfEmittedResult.first() is ScreenState.Loading)
            job.cancel()
        }
    }

    @Test
    fun state_whenUseCaseReturnsLoadinAndSuccess_isLoadingAndSuccessSequentially() {
        runTest {
            val resultList = listOf(
                NetworkResponseState.Loading,
                NetworkResponseState.Success(productEntityList)
            )
            whenever(getFakeGetAllProductsUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult =
                mutableListOf(ScreenState.Loading, ScreenState.Success(productHomeUiDataList))
            val job = launch {
                homeViewModel.products.asFlow().toList(listOfEmittedResult)
            }
            verify(getFakeGetAllProductsUseCase).invoke()
            assert(listOfEmittedResult[1] is ScreenState.Success)
            job.cancel()
        }
    }

    @Test
    fun state_whenUseCaseReturnsLoadingAndError_isLoadingAndErrorSequentially() {
        runTest {
            val resultList =
                listOf(NetworkResponseState.Loading, NetworkResponseState.Error(IOException()))
            whenever(getFakeGetAllProductsUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult = mutableListOf(
                ScreenState.Loading,
                ScreenState.Error("An error occurred").message
            )
            val job = launch {
                homeViewModel.products.asFlow().toList(listOfEmittedResult)
            }
            verify(getFakeGetAllProductsUseCase).invoke()
            assert(listOfEmittedResult[1] is String)
            job.cancel()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}