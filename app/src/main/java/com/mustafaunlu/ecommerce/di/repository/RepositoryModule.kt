package com.mustafaunlu.ecommerce.di.repository

import com.mustafaunlu.ecommerce.data.repository.LocalRepositoryImpl
import com.mustafaunlu.ecommerce.data.repository.RemoteRepositoryImpl
import com.mustafaunlu.ecommerce.domain.repository.LocalRepository
import com.mustafaunlu.ecommerce.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteRepository(
        repository: RemoteRepositoryImpl,
    ): RemoteRepository

    @Binds
    @ViewModelScoped
    abstract fun bindLocalRepository(
        repository: LocalRepositoryImpl,
    ): LocalRepository
}
