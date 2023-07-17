package com.mustafaunlu.ecommerce.di.database

import android.content.Context
import androidx.room.Room
import com.mustafaunlu.ecommerce.data.database.CartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CartDatabase {
        return Room.databaseBuilder(
            context,
            CartDatabase::class.java,
            "cart_database",
        ).build()
    }

    @Provides
    fun provideCartDao(cartDatabase: CartDatabase) = cartDatabase.cartDao()
}
