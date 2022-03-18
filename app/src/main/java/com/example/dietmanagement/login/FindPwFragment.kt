package com.example.dietmanagement.login

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.FragmentFindPwBinding

class FindPwFragment : Fragment() {

    private lateinit var binding: FragmentFindPwBinding
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
                    // 비밀번호 바꾸는 화면으로 넘어가기
                } else {
                    binding.noticeTextCertification.visibility = View.VISIBLE
                }
                // 메일 인증 코드
            }
        }
    }

}