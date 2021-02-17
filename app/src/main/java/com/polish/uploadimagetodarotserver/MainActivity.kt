package com.polish.uploadimagetodarotserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.polish.uploadimagetodarotserver.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    /**
     * viewBinding reference
     */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * initialize the binding
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}