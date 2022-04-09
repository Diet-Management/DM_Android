package com.example.dietmanagement.bottomnavigation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.FragmentBoardBinding

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private lateinit var dialog: Dialog
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
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_set_standard)

        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.findViewById<AppCompatButton>(R.id.standard_apply).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<AppCompatButton>(R.id.standard_cancel).setOnClickListener {
            dialog.dismiss()
        }
    }
}