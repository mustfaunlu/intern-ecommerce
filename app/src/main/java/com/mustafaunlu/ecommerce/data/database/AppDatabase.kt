package com.mustafaunlu.ecommerce.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustafaunlu.ecommerce.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity

@Database(entities = [UserCartEntity::class, FavoriteProductEntity::class, UserCartBadgeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}
