package com.fa.junittesting.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fa.junittesting.data.local.Product
import com.fa.junittesting.data.remote.responses.ImageResponse
import com.fa.junittesting.other.Resource

class FakeProductRepository : ProductRepository {

    private val products = mutableListOf<Product>()
    private val getAllProduct = MutableLiveData<List<Product>>(products)
    private val totalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        getAllProduct.postValue(products)
        totalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return products.sumByDouble { it.price.toDouble() }.toFloat()
    }
    override suspend fun insertProductItem(product: Product) {
        products.add(product)
        refreshLiveData()
    }

    override suspend fun deleteProductItem(product: Product) {
        products.remove(product)
        refreshLiveData()
    }

    override suspend fun getAllProduct(): LiveData<List<Product>> {
        return getAllProduct
    }

    override suspend fun totalPrice(): LiveData<Float> {
        return totalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }

}