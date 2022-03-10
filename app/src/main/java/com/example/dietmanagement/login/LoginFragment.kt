package com.example.dietmanagement.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dietmanagement.MainActivity
import com.example.dietmanagement.databinding.FragmentLoginBinding

class LoginFragment : AppCompatActivity() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}