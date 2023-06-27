package com.example.loginsignupdemo
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    fun getProducts(): Call<List<ProductList>>
}
