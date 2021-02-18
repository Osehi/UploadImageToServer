package com.polish.uploadimagetodarotserver.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

fun convertUriToBitmap(selectedUriImg: Uri, context: Context): Bitmap?{
    var image: Bitmap? = null
    try {
        val parcelFileDescriptor = context.contentResolver.openFileDescriptor(selectedUriImg, "r")
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor
        image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return image
}

fun convertBitmapToFile(bitmapData:Bitmap, context: Context): File?{
    val extStorageDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    Log.d("show me_b-f", "to see content:$extStorageDirectory")
    var outStream: OutputStream? = null
    var file: File? = null
    val timeStamp:String = java.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())   //SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
    val child = "JPEG_${timeStamp}_.jpg"

    if (extStorageDirectory != null){
        file = File(extStorageDirectory, child)
        if (file.exists()){
            file.delete()
            file = File(extStorageDirectory, child)
        }
        try {
            outStream = FileOutputStream(file)
            bitmapData.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()
        } catch (e:Exception){
            e.printStackTrace()
            return null
        }
    }
    return file
}