package com.example.loginsignupdemo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsignupdemo.*
import com.example.loginsignupdemo.adapter.productAdapter
import com.example.loginsignupdemo.service.ApiClient
import com.example.loginsignupdemo.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var prodAdapter: productAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = ApiClient.retrofit.create(ApiService::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)
        val gridLayout= GridLayoutManager(activity,2, LinearLayoutManager.VERTICAL,false)
        //val staggeredLayout = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = gridLayout
        recyclerView.setHasFixedSize(true)

        prodAdapter = productAdapter(emptyList()) { product ->
            showProductDetails(product)
        }


        val call = apiService.getProducts()
        call.enqueue(object : Callback<List<ProductList>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<ProductList>>,
                response: Response<List<ProductList>>
            ) {
                Log.e("tag","API hit ")
                if (response.isSuccessful) {
                    val products = response.body()
                   // products!!.get(0).ProductName?.let { Log.e("tag", it) }
                    if (products != null) {
                        Toast.makeText(activity, "API call success ", Toast.LENGTH_SHORT)
                                    .show()
                                prodAdapter.productList = products
                                prodAdapter.notifyDataSetChanged()

                            } else {
                                Log.e("Error", "Api response getting error")
                                Toast.makeText(activity, "Error in getting API", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                    }

            override fun onFailure(call: Call<List<ProductList>>, t: Throwable) {
                Log.e("Error","API calling failure")
                Toast.makeText(activity,"Please Check your Connection",Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.adapter = prodAdapter

    }

/*
    private fun onProductClick(product: ProductList) {
        val productDetailsFragment = ProductDetailsFragment.newInstance(product)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.productDetailsFragmentContainer, productDetailsFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

 */

    private fun showProductDetails(product: ProductList?) {
        val productDetailsFragment = ProductDetailsFragment.newInstance(product!!)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.productDetailsFragmentContainer, productDetailsFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

}