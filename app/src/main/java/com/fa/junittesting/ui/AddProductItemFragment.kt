package com.fa.junittesting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fa.junittesting.R
import com.fa.junittesting.databinding.FragmentAddProductItemBinding

class AddProductItemFragment : Fragment() {

    lateinit var binding: FragmentAddProductItemBinding
    lateinit var viewModel: ProductViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)

        binding.btnNextPage.setOnClickListener {
            findNavController().navigate(AddProductItemFragmentDirections.actionAddProductItemFragmentToImagePickFragment())
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_product_item, container, false)
        val view = binding.root
        postponeEnterTransition()
        binding.addProductItem = this
        return view
    }
}