package com.manju23reddy.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

/**
 *
 * setUrl Method
 * SetDest ImageView
 *
 */

class MainActivity : AppCompatActivity() {

    lateinit var imgView : ImageView
    lateinit var imgLoader : ImageLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.imgv_holder)

        imgLoader = ImageLoader.
        Builder("https://pixabay.com/get/g0786e1cea873ed629b3144632880e5ebb53516295bcfb7fc4ad4b30297fab2c2686381f035ace9e0e38c87868c437fe1dda625233ea7fa7309cf4ccf277e87de_1280.jpg")
            .destHolderView(imgView).build()


    }

    override fun onDestroy() {
        super.onDestroy()
        imgLoader.cancel()
    }
}