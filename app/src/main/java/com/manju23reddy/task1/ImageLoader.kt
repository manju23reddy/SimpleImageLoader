package com.manju23reddy.task1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import kotlinx.coroutines.*
import retrofit2.Retrofit
import java.net.URL

class ImageLoader constructor(val imgUri : String?, val destView : ImageView?){

    data class Builder(var  ImgUri : String?){

        var destHolderView : ImageView? = null

        fun ImgUri(ImgUri : String) = apply{this.ImgUri = ImgUri}
        fun destHolderView(imgView : ImageView) = apply { this.destHolderView = imgView }

        fun build() = ImageLoader(ImgUri, destHolderView)
    }

    lateinit var imgJob : Job

    init {
        /**
         * use network lib to load the image and apply to view
         * loading should in IO context, apply shouldbe in UI Context
         *
         */

        imgJob = CoroutineScope(Dispatchers.IO).launch {
            _loadImageData()

        }



    }


    private suspend  fun _loadImageData() = withContext(Dispatchers.IO){

       try{
           val url = URL(imgUri)
           val bitmap_decoded = BitmapFactory.decodeStream(url.openConnection().getInputStream())
           _applyImageData(bitmap_decoded)
       }
       catch (exp : Exception){
           Log.e(this.javaClass.simpleName, exp.toString())
       }

    }

    private suspend fun _applyImageData(data : Bitmap) = with(Dispatchers.Main){
        destView?.setImageBitmap(data)
    }

    fun cancel(){
        if(imgJob?.isActive && !imgJob?.isCompleted){
            CoroutineScope(Dispatchers.Default).launch {
                imgJob.cancelAndJoin()
            }

        }
    }




}