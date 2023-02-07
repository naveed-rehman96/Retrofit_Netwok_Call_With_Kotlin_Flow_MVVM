package com.navdroid.apicallwithkotlinflow.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.navdroid.apicallwithkotlinflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStartApiCall.setOnClickListener {
            startActivity(Intent(this, SingleNetworkCallActivity::class.java))
        }
    }
}
