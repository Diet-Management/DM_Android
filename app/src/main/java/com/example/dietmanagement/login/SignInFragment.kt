package com.example.dietmanagement.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.finishButton.setOnClickListener {
            binding.errorID.visibility = View.INVISIBLE
            binding.errorPw.visibility = View.INVISIBLE
            binding.errorPwTest.visibility = View.INVISIBLE
            checkNewMember()
        }

        binding.backLogin.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_signInFragment_to_loginFragment)
        }

        return binding.root
    }

    // 회원가입 예외처리
    private fun checkNewMember() {
        val newId = binding.editTextNewId.text.toString()
        val errorId = binding.errorID

        val newPw = binding.editTextNewPw.text.toString()
        val errorPw = binding.errorPw

        val newPwTest = binding.editTextNewPwTest.text.toString()
        val errorPwTest = binding.errorPwTest

        if (newId.isEmpty()) {
            errorId.visibility = View.VISIBLE
        } else if (!newId.contains("@")) {
            errorId.text = "※ Email 양식으로 입력하세요."
            errorId.visibility = View.VISIBLE
        }

        else if (newPw.isEmpty()) {
            errorPw.visibility = View.VISIBLE
        } else if (newPw.length < 8) {
            errorPw.text = "※ PW는 8글자 이상이어야 합니다."
            errorPw.visibility = View.VISIBLE
        }

        else if (newPwTest.isEmpty()) {
            errorPwTest.visibility = View.VISIBLE
        } else if (!newPwTest.equals(newPw)) {
            errorPwTest.text = "※ PW확인은 PW와 같아야 합니다."
            errorPwTest.visibility = View.VISIBLE
        } else {
            Toast.makeText(context, "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_signInFragment_to_loginFragment)
        }


    }
}