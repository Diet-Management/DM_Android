package com.example.dietmanagement.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.data.data.request.LoginData
import com.example.data.retrofit.builder.DmLoginServiceBuilder
import com.example.dietmanagement.MainActivity
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.FragmentLoginBinding
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.newUserBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_signInFragment)
        }

        binding.nextBtn.setOnClickListener {
            val email = binding.editEmailText.text.toString()
            val pw = binding.editPwText.text.toString()
            val user = LoginData(email, pw)

            checkLogin(user)
        }

        binding.findId.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_findIdFragment)
        }

        return binding.root
    }

    private fun checkLogin(user: LoginData) {
        DmLoginServiceBuilder.dmLoginService.loginResponse(user).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null) {
                    try {
                        val json = response.body()!!.string()
                        Log.d("SUCCESS", "onResponse body: $json")

                        val jsonObject = JSONObject(json)
                        val data = jsonObject.getString("data")
                        val dataJsonOption = JSONObject(data)
                        val accessToken = dataJsonOption.getString("accessToken")
                        val refreshToken = dataJsonOption.getString("refreshToken")

                        Log.d("JSONData", "memberIdx: ${dataJsonOption.getString("memberIdx")}")
                        Log.d("JSONData", "accessToken: $accessToken")

                        val intent = Intent(context, MainActivity::class.java)
                            .putExtra("memberIdx", dataJsonOption.getString("memberIdx"))
                            .putExtra("accessToken", accessToken)
                            .putExtra("refreshToken", refreshToken)
                        startActivity(intent)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {

                    val error = response.errorBody()!!.string()
                    Toast.makeText(context, "로그인에 실패함", Toast.LENGTH_SHORT).show()
                    Log.d("ERROR", "onResponse: $error")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context, "아이디 혹은 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
            }

        })
    }
}