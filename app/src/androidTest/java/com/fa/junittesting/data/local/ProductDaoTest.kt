package com.fa.junittesting.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.fa.junittesting.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProductDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var productDao: ProductDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        productDao = appDatabase.ProductDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertProductTest() = runTest {
        var newProduct = Product(1, "apple", 20, 2f, "")
        productDao.insertProduct(newProduct)

        val allProduct = productDao.getAllProducts().getOrAwaitValue()

        assertThat(allProduct).contains(newProduct)
    }

    @Test
    fun deleteProductTest() = runTest {
        var newProduct = Product(1, "banana", 10, 1.5f, "")
        productDao.insertProduct(newProduct)
        productDao.deleteProduct(newProduct)

        val allProduct = productDao.getAllProducts().getOrAwaitValue()
        assertThat(allProduct).doesNotContain(newProduct)

    }

    @Test
    fun totalPriceTest() = runTest {
        var newProduct1 = Product(1, "apple", 10, 1.5f, "")
        var newProduct2 = Product(2, "banana", 10, 2f, "")
        var newProduct3 = Product(3, "peach", 10, 3f, "")
        productDao.insertProduct(newProduct1)
        productDao.insertProduct(newProduct2)
        productDao.insertProduct(newProduct3)

        val actualTotalPrice = productDao.totalPrice().getOrAwaitValue()
        val totalPrice = 10 * 1.5 + 10 * 2 + 10 * 3
        assertThat(actualTotalPrice).isEqualTo(totalPrice)
    }

}
