package com.example.materialdesign.view.ux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityUxBinding

class UxActivity : AppCompatActivity() {

    lateinit var binding: ActivityUxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MoonTheme)
        super.onCreate(savedInstanceState)

        binding = ActivityUxBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}