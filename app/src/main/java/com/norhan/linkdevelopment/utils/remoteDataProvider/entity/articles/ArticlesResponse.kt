package com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("source")
    @Expose
    val source: String,
    @SerializedName("sortBy")
    @Expose
    val sortBy: String,
    @SerializedName("articles")
    @Expose
    val articles: List<Article>
)