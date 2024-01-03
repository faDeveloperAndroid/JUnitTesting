package com.fa.junittesting.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fa.junittesting.data.local.Product
import com.fa.junittesting.data.remote.responses.ImageResponse
import com.fa.junittesting.other.Constants
import com.fa.junittesting.other.Event
import com.fa.junittesting.other.Resource
import com.fa.junittesting.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
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
        if(name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()){
            _insertProductItemStatus.postValue(Event(Resource.error("The field must not be empty", null)))
            return
        }

        if(name.length>Constants.MAX_NAME_LENGTH){
            _insertProductItemStatus.postValue(Event(Resource.error(
                "The name of the item must not exceed ${Constants.MAX_NAME_LENGTH} characters", null)))
            return
        }

        if(priceString.length>Constants.MAX_PRICE_LENGTH){
            _insertProductItemStatus.postValue(Event(Resource.error(
                "The price of the item must not exceed ${Constants.MAX_PRICE_LENGTH} characters", null)))
            return
        }

        val amount = try{
            amountString.toInt()

        }catch (e:Exception){
            _insertProductItemStatus.postValue((Event(Resource.error("please inter a valid amount", null))))
            return
        }

        val productItem = Product(1, name, amount, priceString.toFloat(), _curImageUrl.value ?: "")
        insertProductItemIntoDb(productItem)
        setCurImageUrl("")
        _insertProductItemStatus.postValue(Event(Resource.success(productItem)))
    }

    fun searchForImage(imageQuery:String){
        if(imageQuery.isEmpty()){
            return
        }
        _images.value= Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }
    }
}