package com.mustafaunlu.ecommerce.domain.usecase.category

import com.mustafaunlu.ecommerce.common.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {
    operator fun invoke(): Flow<NetworkResponseState<List<String>>>
}
