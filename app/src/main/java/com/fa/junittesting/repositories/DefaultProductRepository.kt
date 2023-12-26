package com.fa.junittesting.repositories

import androidx.lifecycle.LiveData
import com.fa.junittesting.data.local.Product
import com.fa.junittesting.data.local.ProductDao
import com.fa.junittesting.data.remote.PixabayAPI
import com.fa.junittesting.data.remote.responses.ImageResponse
import com.fa.junittesting.other.Resource
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val pixabayAPI: PixabayAPI
) : ProductRepository {
    override suspend fun insertProductItem(product: Product) {
        productDao.insertProduct(product)
    }

    override suspend fun deleteProductItem(product: Product) {
        productDao.deleteProduct(product)
    }

    override suspend fun getAllProduct(): LiveData<List<Product>> {
        return productDao.getAllProducts()
    }

    override suspend fun totalPrice(): LiveData<Float> {
        return productDao.totalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown Error", null)
            } else {
                Resource.error("An unknown Error", null)
            }
        } catch (e: Exception) {
            Resource.error("couldn't reach the server.", null)
        }
    }
}