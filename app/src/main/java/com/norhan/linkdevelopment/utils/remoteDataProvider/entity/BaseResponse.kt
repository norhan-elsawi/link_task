package com.norhan.linkdevelopment.utils.remoteDataProvider

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("status")
    @Expose
    var code: Long = 0
    @SerializedName("message")
    @Expose
    var message: String = ""
    @SerializedName("state")
    @Expose
    var state: Boolean = false
    @SerializedName("data")
    @Expose
    var data: T? = null
}