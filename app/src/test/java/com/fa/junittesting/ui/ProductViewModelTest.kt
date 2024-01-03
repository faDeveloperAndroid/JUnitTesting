package com.fa.junittesting.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fa.junittesting.MainCoroutineRule
import com.fa.junittesting.getOrAwaitValueTest
import com.fa.junittesting.other.Constants
import com.fa.junittesting.other.Status
import com.fa.junittesting.repositories.FakeProductRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductViewModelTest{
    private lateinit var viewModel: ProductViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        viewModel = ProductViewModel(FakeProductRepository())
    }

    @Test
    fun insert_product_item_with_empty_field_error() {
        viewModel.insertProductItem("name", "", "10")
        val value = viewModel.insertProductItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insert_product_item_with_too_long_name_error() {
        val name = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH+1){
                append(1)
            }
        }

        viewModel.insertProductItem(name, "5", "10")
        val value = viewModel.insertProductItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insert_product_item_with_too_long_price_error() {
        val price = buildString {
            for(i in 1..Constants.MAX_PRICE_LENGTH+1){
                append(1)
            }
        }

        viewModel.insertProductItem("name", "5", price)
        val value = viewModel.insertProductItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insert_product_item_with_too_high_amount_error() {
        viewModel.insertProductItem("name", "99999999999999", "10")
        val value = viewModel.insertProductItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun insert_product_item_with_valid_input_success() {
        viewModel.insertProductItem("name", "5", "10")
        val value = viewModel.insertProductItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

}