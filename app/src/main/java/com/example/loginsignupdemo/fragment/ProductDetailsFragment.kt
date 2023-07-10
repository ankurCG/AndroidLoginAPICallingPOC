package com.example.loginsignupdemo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.loginsignupdemo.ProductList
import com.example.loginsignupdemo.R

class ProductDetailsFragment : Fragment() {

    private var _binding: ProductDetailsFragment? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_ITEM = "item"

        fun newInstance(product: ProductList): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle()
            args.putParcelable(ARG_ITEM,product)
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        val product : ProductList? = arguments?.getParcelable(ARG_ITEM)

        if(product != null ){

            val imageView: ImageView? = view.findViewById(R.id.fragmentProductImg)
            val productName: TextView? = view.findViewById(R.id.fragmentProductTitle)
            val productCategory:TextView = view.findViewById(R.id.fragmentProductCategory)
            val productPrice:TextView =view.findViewById(R.id.fragmentProductPrice)
            val productDescription:TextView = view.findViewById(R.id.fragmentProductDescription)

            if (imageView != null) {
                Glide.with(requireContext()).load(product.ProductImageUrl).into(imageView)
            }
            productName?.text = product.ProductName
            productCategory.text = product.ProductCategory
            productPrice.text = product.ProductPrice.toString()
            productDescription.text = product.ProductDescription

        }
        return view
    }

}