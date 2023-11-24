package io.github.ovso.globenews.core.network

import io.github.ovso.globenews.BuildConfig
import retrofit2.http.GET

interface NewsNetworkApi {
    @GET(value = "/v2/top-headlines?country=kr&apiKey=${BuildConfig.API_KEY}")
    suspend fun getNews(): NewsArticle

}
