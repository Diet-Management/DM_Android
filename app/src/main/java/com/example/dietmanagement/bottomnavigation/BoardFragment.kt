package com.example.dietmanagement.bottomnavigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.Navigation
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.FragmentBoardBinding

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBoardBinding.inflate(inflater, container, false)

        binding.addPostButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_boardFragment_to_addPostActivity)
        }
        return binding.root
    }
}