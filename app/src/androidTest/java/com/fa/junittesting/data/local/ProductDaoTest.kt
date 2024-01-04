package com.fa.junittesting.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.fa.junittesting.getOrAwaitValue
import com.fa.junittesting.launchFragmentInHiltContainer
import com.fa.junittesting.ui.ProductFragment
import com.google.ar.core.Config
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ProductDaoTest {

    @Inject
    @Named("test_db")
    lateinit var appDatabas: AppDatabase

    private lateinit var productDao: ProductDao

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        hiltRule.inject()
        productDao = appDatabas.ProductDao()
    }

    @After
    fun tearDown() {
        appDatabas.close()
    }

    @Test
    fun testLaunchFragmentInHiltContainer(){
        launchFragmentInHiltContainer<ProductFragment> {

        }
    }

    @Test
    fun insertProductTest() = runBlockingTest {
        var newProduct = Product(1, "apple", 20, 2f, "")
        productDao.insertProduct(newProduct)

        val allProduct = productDao.getAllProducts().getOrAwaitValue()

        assertThat(allProduct).contains(newProduct)
    }

    @Test
    fun deleteProductTest() = runBlockingTest {
        var newProduct = Product(1, "banana", 10, 1.5f, "")
        productDao.insertProduct(newProduct)
        productDao.deleteProduct(newProduct)

        val allProduct = productDao.getAllProducts().getOrAwaitValue()
        assertThat(allProduct).doesNotContain(newProduct)

    }

    @Test
    fun totalPriceTest() = runBlockingTest {
        var newProduct1 = Product(1, "apple", 10, 1.5f, "")
        var newProduct2 = Product(2, "banana", 10, 2f, "")
        var newProduct3 = Product(3, "peach", 10, 3f, "")
        productDao.insertProduct(newProduct1)
        productDao.insertProduct(newProduct2)
        productDao.insertProduct(newProduct3)

        val actualTotalPrice = productDao.totalPrice().getOrAwaitValue()
        val totalPrice = 10 * 1.5f + 10 * 2f + 10 * 3f
        assertThat(actualTotalPrice).isEqualTo(totalPrice)
    }

}
