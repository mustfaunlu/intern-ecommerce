package com.mustafaunlu.ecommerce.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCart(userCartEntity: UserCartEntity)

    @Query("SELECT * FROM user_carts WHERE userId = :userId")
    suspend fun getCartByUserId(userId: Int): List<UserCartEntity>

    @Delete
    suspend fun deleteUserCartItem(userCartEntity: UserCartEntity)
}
