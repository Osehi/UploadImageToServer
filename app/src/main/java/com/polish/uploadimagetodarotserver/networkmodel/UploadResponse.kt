package com.polish.uploadimagetodarotserver.networkmodel


import com.google.gson.annotations.SerializedName

data class UploadResponse(
    val message: String?,
    val payload: Payload?,
    val status: Int?
)