package com.mustafaunlu.ecommerce.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustafaunlu.ecommerce.domain.entity.FavoriteItemEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity

@Database(entities = [UserCartEntity::class, FavoriteItemEntity::class, UserCartBadgeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): AppDao
}
