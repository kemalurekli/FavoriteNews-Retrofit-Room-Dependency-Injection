package com.kemalurekli.firstapi.model

import com.google.gson.annotations.SerializedName

data class NewsResult(
    val key : String,
    val url : String,
    val description : String,
    @SerializedName("image")
    val imageUrl : String,
    val name : String,
    val source : String,
    val date : String
)
