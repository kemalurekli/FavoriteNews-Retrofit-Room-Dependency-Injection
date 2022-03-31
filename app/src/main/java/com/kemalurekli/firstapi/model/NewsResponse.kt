package com.kemalurekli.firstapi.model

data class NewsResponse(
    val success : Boolean,
    val result : List<NewsResult>
)
