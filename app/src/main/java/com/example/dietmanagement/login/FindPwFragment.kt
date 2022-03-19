package com.example.dietmanagement.login

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.FragmentFindPwBinding

class FindPwFragment : Fragment() {

    private lateinit var binding: FragmentFindPwBinding
    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFindPwBinding.inflate(inflater, container, false)

        binding.pwCertificationButton.setOnClickListener {
            binding.noticeText.visibility = View.INVISIBLE
            binding.certificationInfo.visibility = View.INVISIBLE
            checkCertification()
        }

        binding.backFindIdLogin.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_findPwFragment_to_loginFragment)
        }

        return binding.root
    }

    private fun checkCertification() {

        val newEmailResult = binding.editTextNewEmail.text.toString()
        if (!newEmailResult.contains("@") || newEmailResult.isEmpty()) {
            binding.noticeText.visibility = View.VISIBLE
        } else {
            Toast.makeText(context, "인증메일이 발송되었습니다.", Toast.LENGTH_SHORT).show()
            binding.certificationInfo.visibility = View.VISIBLE

            binding.successCertificationButton.setOnClickListener {
                if (binding.editTextEmailCertification.text.toString().isNotEmpty()) {
                    Toast.makeText(context, "인증되었습니다.", Toast.LENGTH_SHORT).show()
                    openCustomDialog()
                } else {
                    binding.noticeTextCertification.visibility = View.VISIBLE
                }
                // 메일 인증 코드
            }
        }
    }

    private fun openCustomDialog() {
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_change_pw)

        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        dialog.findViewById<ImageView>(R.id.back_dialog).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<AppCompatButton>(R.id.select_new_pw_button).setOnClickListener {
            dialog.dismiss()
        }
    }

}