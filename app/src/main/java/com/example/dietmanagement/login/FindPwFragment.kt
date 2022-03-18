package com.example.dietmanagement.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dietmanagement.databinding.FragmentFindPwBinding

class FindPwFragment : Fragment() {

    private lateinit var binding: FragmentFindPwBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFindPwBinding.inflate(inflater, container, false)


        return binding.root
    }
}