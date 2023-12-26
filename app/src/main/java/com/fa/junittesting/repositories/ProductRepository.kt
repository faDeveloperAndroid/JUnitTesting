package com.fa.junittesting.repositories

import androidx.lifecycle.LiveData
import com.fa.junittesting.data.local.Product
import com.fa.junittesting.data.remote.responses.ImageResponse
import com.fa.junittesting.other.Resource
import retrofit2.Response

interface ProductRepository {
    suspend fun insertProductItem(product: Product)
    suspend fun deleteProductItem(product: Product)
    suspend fun getAllProduct(): LiveData<List<Product>>
    suspend fun totalPrice(): LiveData<Float>
    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}