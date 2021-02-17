package com.polish.uploadimagetodarotserver.repository

import android.util.Log
import com.polish.uploadimagetodarotserver.api.APIUploadImage
import com.polish.uploadimagetodarotserver.networkmodel.UploadResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

class IRepositoryImpl @Inject constructor(val apiImageUpload:APIUploadImage): IRepository {
    override suspend fun uploadImage(file: MultipartBody.Part): UploadResponse {
        // carryout a background thread
        return withContext(Dispatchers.IO){
            apiImageUpload.uploadImage(file)
        }
    }
}