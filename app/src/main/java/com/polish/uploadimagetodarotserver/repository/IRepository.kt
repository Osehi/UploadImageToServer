package com.polish.uploadimagetodarotserver.repository

import com.polish.uploadimagetodarotserver.networkmodel.UploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Part

interface IRepository {
    suspend fun uploadImage(
        @Part file:MultipartBody.Part
    ):UploadResponse
}