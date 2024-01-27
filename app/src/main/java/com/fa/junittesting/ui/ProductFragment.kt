package com.fa.junittesting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fa.junittesting.R
import com.fa.junittesting.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    lateinit var viewModel: ProductViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)

        binding.fabAddProductItem.setOnClickListener{
            findNavController().navigate(
                ProductFragmentDirections.actionProductFragmentToAddProductItemFragment()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product, container, false
        )
        val view: View = binding.root
        postponeEnterTransition()
        binding.productItem = this
        initialize()
        return view
    }

    private fun initialize(){

    }
}