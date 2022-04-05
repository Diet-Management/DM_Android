package com.example.dietmanagement.login.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dietmanagement.databinding.ActivityAddPostBinding

class AddPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}