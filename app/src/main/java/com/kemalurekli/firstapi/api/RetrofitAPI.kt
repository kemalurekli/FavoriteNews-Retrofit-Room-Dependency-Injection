package com.kemalurekli.firstapi.api

import com.kemalurekli.firstapi.model.NewsResponse
import com.kemalurekli.firstapi.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitAPI {
    @GET("/news/getNews?country=tr&tag=general")
    @Headers("content-type:application/json", "authorization: $API_KEY")
    suspend fun newsDownload () : Response<NewsResponse>
}