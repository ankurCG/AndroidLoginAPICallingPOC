package com.example.loginsignupdemo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("id")
    val ProductId: String?,
    @SerializedName("title")
    val ProductName: String?,
    @SerializedName("price")
    val ProductPrice: Double,
    @SerializedName("description")
    val ProductDescription: String?,
    @SerializedName("category")
    val ProductCategory: String?,
    @SerializedName("image")
    val ProductImageUrl: String?,
    //val rating:Array<rating>

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        //parcel.readArray()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ProductId)
        parcel.writeString(ProductName)
        parcel.writeDouble(ProductPrice)
        parcel.writeString(ProductDescription)
        parcel.writeString(ProductCategory)
        parcel.writeString(ProductImageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductList> {
        override fun createFromParcel(parcel: Parcel): ProductList {
            return ProductList(parcel)
        }

        override fun newArray(size: Int): Array<ProductList?> {
            return arrayOfNulls(size)
        }
    }
}

data class rating(
    val rate:String,
    val count:String
)


