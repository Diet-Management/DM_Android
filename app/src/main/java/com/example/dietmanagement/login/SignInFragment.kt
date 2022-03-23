package com.example.dietmanagement.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.dietmanagement.R
import com.example.dietmanagement.data.JoinData
import com.example.dietmanagement.databinding.FragmentSignInBinding
import com.example.dietmanagement.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            val email = binding.editTextNewEmail.text.toString()
            val name = binding.editTextNewName.text.toString()
            val pw = binding.editTextNewPw.text.toString()
            if (email.isNotEmpty() && name.isNotEmpty() && pw.isNotEmpty()) {
                Log.d("TAG", "onCreateView: $email, $name, $pw")

                // data 처리
                val data = HashMap<String, String>()
                data.put("email", email)
                data.put("name", name)
                data.put("pw", pw)
                data.put("theme", "WHITE")

                RetrofitBuilder.dmService.joinResponse(data).enqueue(object : Callback<JoinData> {
                    override fun onResponse(call: Call<JoinData>, response: Response<JoinData>) {
                        if (response.body() != null) {
                            Log.d("SUCCESS11", "onResponse: ${response.raw()}")
                            Log.d("SUCCESS11", "onResponse: ${response.body()}")
                            Log.d("SUCCESS11", "onResponse: ${response.code()}")
                            Navigation.findNavController(binding.root).navigate(R.id.action_signInFragment_to_loginFragment)
                        } else {
                            val error = response.errorBody()!!.string()
                            Log.d("ERROR22", "onResponse: $error")
                        }
                    }

                    override fun onFailure(call: Call<JoinData>, t: Throwable) {
                        Log.d("ERROR11", "onFailure: ${t.message.toString()}, $call")
                        Toast.makeText(context, "가입 실패 ㅅㄱ", Toast.LENGTH_SHORT).show()
                        t.printStackTrace()
                    }
                })


//                checkNewMember()
            } else {
                Log.d("TAG", "onCreateView: error")
            }
        }

        binding.backLogin.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_signInFragment_to_loginFragment)
        }

        return binding.root
    }

    // 회원가입 예외처리
    private fun checkNewMember() {
        val newId = binding.editTextNewEmail.text.toString()
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
//            Navigation.findNavController(binding.root).navigate(R.id.action_signInFragment_to_loginFragment)
        }


    }

}