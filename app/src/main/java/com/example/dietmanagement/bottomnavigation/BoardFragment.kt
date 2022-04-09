package com.example.dietmanagement.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        binding.settingStandard.setOnClickListener {
            setStandard()
        }
        return binding.root
    }

    // TODO :: 기준 작성 다이얼로그 만들기
    private fun setStandard() {
//        val dialog
    }
}