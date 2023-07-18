package com.mustafaunlu.ecommerce.utils

import com.mustafaunlu.ecommerce.common.NetworkResponseState

fun <I : Any, O : Any> NetworkResponseState<I>.mapResponse(mapper: I.() -> O): NetworkResponseState<O> {
    return when (this) {
        is NetworkResponseState.Error -> NetworkResponseState.Error(this.exception)
        NetworkResponseState.Loading -> NetworkResponseState.Loading
        is NetworkResponseState.Success -> NetworkResponseState.Success(mapper.invoke(this.result))
    }
}