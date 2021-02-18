package com.polish.uploadimagetodarotserver.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polish.uploadimagetodarotserver.networkmodel.UploadResponse
import com.polish.uploadimagetodarotserver.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class UploadImageViewModel@Inject constructor(private val repository:IRepository):ViewModel() {
    val TAG = "UploadImageViewModel"
    /**
     * use livedata to hold the response from the call
     */
    private var _getImageUploadResponse = MutableLiveData<UploadResponse>()
    val getImageUploadResponse:LiveData<UploadResponse>
    get() = _getImageUploadResponse

    /**
     * create a coroutine scope to handle the data output from the input IO
     * and channel it to the main thread
     */
    private var job = Job()
    private var uploadImageViewModelScope = CoroutineScope(job + Dispatchers.IO)

    /**
     * post image to server
     */
    fun postMyImage(file:MultipartBody.Part){
        uploadImageViewModelScope.launch {
            val output = repository.uploadImage(file)
            // to channel the data the the mainThread
            withContext(Dispatchers.Main){
                _getImageUploadResponse.value = output
                Log.d(TAG, "the output:${output.copy()}")
            }
        }
    }

}