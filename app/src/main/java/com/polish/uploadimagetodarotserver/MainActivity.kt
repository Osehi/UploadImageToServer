package com.polish.uploadimagetodarotserver

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.polish.uploadimagetodarotserver.databinding.ActivityMainBinding
import com.polish.uploadimagetodarotserver.helper.convertBitmapToFile
import com.polish.uploadimagetodarotserver.helper.convertUriToBitmap
import com.polish.uploadimagetodarotserver.viewmodel.UploadImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val TAG = "MAIN_ACTIVITY"

    companion object {
        const val REQUEST_CODE_IMAGE_PICKER = 100
    }
    /**
     * capture the file format
     */
    private lateinit var imageInFileFormat: File

    /**
     * reference
     */
    lateinit var pickImage:FloatingActionButton
    lateinit var uploadButton:Button
    lateinit var displayImage:ImageView
    private var selectedImageUri:Uri? = null

    /**
     * viewBinding reference
     */
    private lateinit var binding: ActivityMainBinding
    /**
     * reference to the uploadImageviewmodel
     */
    private val uploadImageViewModel:UploadImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * initialize the binding
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        /**
         * initialize the views
         */
        pickImage = binding.activityMainFloatingActionButtonFab
        uploadButton = binding.activityMainButtonBtn
        displayImage = binding.activityMainImageViewIv

        /**
         * post the image to server
         */
        uploadButton.setOnClickListener {
            val imageBody = imageInFileFormat.asRequestBody("image/jpg".toMediaTypeOrNull())
            val imageFile = MultipartBody.Part.createFormData("file",imageInFileFormat.name, imageBody)
            uploadImageViewModel.postMyImage(imageFile)
            Log.d(TAG, "at uploadButton:$imageInFileFormat")
        }

        /**
         * pick image
         */
        pickImage.setOnClickListener {
            imageChooser()
        }

    }
    private fun imageChooser(){
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_IMAGE_PICKER -> {
                    selectedImageUri = data?.data
                    // set the selected image to the image view
                    displayImage.setImageURI(selectedImageUri)
                    // convert uri to Bitmap
                    val theBitmapValue = convertUriToBitmap(selectedImageUri!!, this)
                    Log.d(TAG, "result in bitmap:$theBitmapValue")
                    // convert bitmap to file
                    imageInFileFormat = convertBitmapToFile(theBitmapValue!!, this)!!
                    Log.d(TAG, "here is the file format:$imageInFileFormat")
//                    val file = File(selectedImageUri?.path!!)
//                    imageInFileFormat = file
//                    Log.d(TAG, "content of file:$file")
//                    val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                }
            }
        }
    }


}