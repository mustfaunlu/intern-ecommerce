package com.mustafaunlu.ecommerce.domain.mapper

interface ProductBaseMapper<I, O> {
    fun map(input: I): O
}
