package com.mustafaunlu.ecommerce.domain.usecase.category

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import com.mustafaunlu.ecommerce.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCaseImpl @Inject constructor(private val repository: RemoteRepository) :
    CategoryUseCase {
    override fun invoke(): Flow<NetworkResponseState<List<String>>> {
        return repository.getAllCategoriesListFromApi()
    }

    override fun invoke(categoryName: String): Flow<NetworkResponseState<List<ProductEntity>>> {
        return repository.getProductsListByCategoryNameFromApi(categoryName)
    }
}
