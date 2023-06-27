package com.example.loginsignupdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class productAdapter(var productList : List<ProductList>, private val onItemClick: (ProductList) -> Unit):
    RecyclerView.Adapter<productAdapter.productViewHolder>() {

    inner class productViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView? = itemView.findViewById(R.id.ProductImageView)
        val productName: TextView = itemView.findViewById(R.id.textViewProductName)
        val productPrice:TextView =itemView.findViewById(R.id.textViewProductPrice)

        fun Bind(product:ProductList){
            with(itemView) {
                if (imageView != null) {
                    Glide.with(context).load(product.ProductImageUrl).into(imageView)
                }
                productName.text = product.ProductName
                //productName.text = "This is for testing"
                productPrice.text = product.ProductPrice.toString()
                //productPrice.text ="100 rs"
                setOnClickListener { onItemClick(product) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card_view, parent, false)
        return productViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: productViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.Bind(currentItem)
    }

    override fun getItemCount() = productList.size

}
