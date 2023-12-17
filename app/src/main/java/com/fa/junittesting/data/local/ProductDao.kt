package com.fa.junittesting.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("Select * From product")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("Select * From product Where Id In (:productIds)")
    fun getAllByIds(productIds: IntArray): LiveData<List<Product>>

    @Query("Select Sum(Amount * Price) From product")
    fun totalPrice(): LiveData<Float>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(preduct: Product)

    @Delete
    suspend fun deleteProduct(product: Product)
}