package com.fa.junittesting.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.fa.junittesting.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import com.fa.junittesting.R


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ProductFragmentTest{

    @get:Rule
    var hiltRule=HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    //fun 'click Add product item button navigate to add product fragment'(){
    fun clickAddProductItemButton_NavigateToAddProductFragment(){
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<ProductFragment>{
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.fabAddProductItem)).perform(click())

        verify(navController).navigate(
            ProductFragmentDirections.actionProductFragmentToAddProductItemFragment()
        )

    }

}