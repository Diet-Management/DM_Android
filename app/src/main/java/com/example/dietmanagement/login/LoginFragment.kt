package com.example.dietmanagement.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.data.data.LoginData
import com.example.data.retrofit.api.DmLoginService
import com.example.data.retrofit.builder.DmLoginServiceBuilder
import com.example.dietmanagement.R
import com.example.dietmanagement.databinding.FragmentLoginBinding
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
        DmLoginServiceBuilder.dmLoginService.loginResponse(user).enqueue(object : Callback<JSONObject> {
            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                if (response.body() != null) {
                    try {
                        Log.d("SUCCESS", "onResponse body: $response")
                        Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_mainActivity)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {

                    val error = response.errorBody()!!.string()
                    Toast.makeText(context, "로그인에 실패함", Toast.LENGTH_SHORT).show()
                    Log.d("ERROR", "onResponse: $error")
                }
            }

            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}