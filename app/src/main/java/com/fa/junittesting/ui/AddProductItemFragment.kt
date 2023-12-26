package com.fa.junittesting.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fa.junittesting.R

class AddProductItemFragment: Fragment(R.layout.fragment_add_product_item) {

    lateinit var viewModel: ProductViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
    }
}