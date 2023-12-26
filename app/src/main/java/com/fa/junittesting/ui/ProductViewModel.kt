package com.fa.junittesting.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.junittesting.data.local.Product
import com.fa.junittesting.data.remote.responses.ImageResponse
import com.fa.junittesting.other.Event
import com.fa.junittesting.other.Resource
import com.fa.junittesting.repositories.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel @ViewModelInject constructor(
    private val repository: ProductRepository,
) : ViewModel() {
    val products = repository.getAllProduct()
    val totalPrice = repository.totalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertProductItemStatus = MutableLiveData<Event<Resource<Product>>>()
    val insertProductItemStatus: LiveData<Event<Resource<Product>>> = _insertProductItemStatus


    fun setCurImageUrl(url:String){
        _curImageUrl.postValue(url)
    }

    fun deleteProductItem(product: Product) = viewModelScope.launch {
        repository.deleteProductItem(product)
    }

    fun insertProductItemIntoDb(product: Product) = viewModelScope.launch {
        repository.insertProductItem(product)
    }

    fun insertProductItem(name:String, amountString: String, priceString: String){

    }

    fun searchForImage(imageQuery:String){

    }
}