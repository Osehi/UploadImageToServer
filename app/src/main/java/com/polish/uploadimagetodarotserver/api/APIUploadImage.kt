package com.polish.uploadimagetodarotserver.api

import com.polish.uploadimagetodarotserver.networkmodel.UploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface APIUploadImage {
    @Multipart
    @POST("upload")
    suspend fun uploadImage(
        @Part file:MultipartBody.Part
    ):UploadResponse
}