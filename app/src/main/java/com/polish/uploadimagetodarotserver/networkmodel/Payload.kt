package com.polish.uploadimagetodarotserver.networkmodel


import com.google.gson.annotations.SerializedName

data class Payload(
    val downloadUri: String?,
    val fileId: String?,
    val fileName: String?,
    val fileType: String?,
    val uploadStatus: Boolean?
)