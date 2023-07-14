package com.example.loginsignupdemo.service
import com.example.loginsignupdemo.ProductList
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    fun getProducts(): Call<List<ProductList>>
}
