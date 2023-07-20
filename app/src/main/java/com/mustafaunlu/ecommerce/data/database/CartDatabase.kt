package com.mustafaunlu.ecommerce.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity

@Database(entities = [UserCartEntity::class], version = 1)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
