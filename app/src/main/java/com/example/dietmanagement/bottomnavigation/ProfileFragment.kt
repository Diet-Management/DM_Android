package com.example.dietmanagement.bottomnavigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dietmanagement.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d("TAG", "onCreateView extra: ${arguments?.getString("memberIdx")}")

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}