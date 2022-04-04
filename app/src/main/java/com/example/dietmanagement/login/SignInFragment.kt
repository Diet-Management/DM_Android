package com.example.dietmanagement.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.data.data.JoinData
import com.example.data.retrofit.RetrofitBuilder
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.FragmentSignInBinding
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.finishButton.setOnClickListener {
            binding.errorEmail.visibility = View.INVISIBLE
            binding.errorName.visibility = View.INVISIBLE
            binding.errorPw.visibility = View.INVISIBLE
            binding.errorPwTest.visibility = View.INVISIBLE

            val email = binding.editTextNewEmail.text.toString()
            val name = binding.editTextNewName.text.toString()
            val pw = binding.editTextNewPw.text.toString()
            val checkPw = binding.editTextNewPw.text.toString()
            val theme = binding.spinnerTheme.selectedItem.toString()
            if (email.isNotEmpty() && name.isNotEmpty() && pw.isNotEmpty() && checkPw.isNotEmpty()) {
                // data 처리
                val data = JoinData(email, name, pw, theme)
                Log.d("DATA", "onCreateView joinData: $data")
                checkJoin(data)
            } else {
                checkNewMember()
            }
        }

        binding.backLogin.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_signInFragment_to_loginFragment)
        }

        return binding.root
    }

    // 서버 접속
    private fun checkJoin(data: JoinData) {
        RetrofitBuilder.dmJoinService.joinResponse(data).enqueue(object : Callback<JSONObject> {
                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                    if (response.body() != null) {
                        Log.d("SUCCESS", "onResponse raw: ${response.raw()}")
                        Log.d("SUCCESS", "onResponse body: ${response.body()}")

                        try {
                            val jsonObject = JSONObject(response.body().toString())
                            Log.d("SUCCESS", "onResponse jsonObj body: $jsonObject")
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                        Toast.makeText(context, "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(binding.root).navigate(R.id.action_signInFragment_to_loginFragment)
                    } else if (response.code() == 400) {
                        val errorCode = response.errorBody().toString()
                        Log.d("400ERROR", "onResponse raw: $errorCode")
                        Toast.makeText(context, "이미 가입한 이메일입니다.", Toast.LENGTH_SHORT).show()
                    } else {

                        val error = response.errorBody()!!.string()
                        Toast.makeText(context, "회원가입에 실패함", Toast.LENGTH_SHORT).show()
                        Log.d("ERROR", "onResponse: $error")
                    }
                }

            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                Log.d("ERROR11", "onFailure: ${t.message.toString()}, $call")
                Toast.makeText(context, "회원가입에 실패함", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

    // 회원가입 예외처리
    private fun checkNewMember() {
        val newEmail = binding.editTextNewEmail.text.toString()
        val errorEmail = binding.errorEmail

        val newName = binding.editTextNewName.text.toString()
        val errorName = binding.errorName

        val newPw = binding.editTextNewPw.text.toString()
        val errorPw = binding.errorPw

        val newPwTest = binding.editTextNewPwTest.text.toString()
        val errorPwTest = binding.errorPwTest

        if (newEmail.isEmpty()) {
            errorEmail.visibility = View.VISIBLE
        } else if (!newEmail.contains("@")) {
            errorEmail.text = "※ Email 양식으로 입력하세요."
            errorEmail.visibility = View.VISIBLE
        }

        else if (newName.isEmpty()) {
            errorName.visibility = View.VISIBLE
        }

        else if (newPw.isEmpty()) {
            errorPw.visibility = View.VISIBLE
        } else if (newPw.length < 8) {
            errorPw.text = "※ PW는 8글자 이상이어야 합니다."
            errorPw.visibility = View.VISIBLE
        }

        else if (newPwTest.isEmpty()) {
            errorPwTest.visibility = View.VISIBLE
        } else if (newPwTest != newPw) {
            errorPwTest.text = "※ PW확인은 PW와 같아야 합니다."
            errorPwTest.visibility = View.VISIBLE
        }

    }
}