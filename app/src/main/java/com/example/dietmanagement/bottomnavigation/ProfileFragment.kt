package com.example.dietmanagement.bottomnavigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.data.retrofit.builder.DmMemberIdxServiceBuilder
import com.example.dietmanagement.databinding.FragmentProfileBinding
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d("TAG", "onCreateView extra: ${arguments?.getString("memberIdx")}")
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        val memberIdx = arguments?.getString("memberIdx")!!.toInt()
        getUser(memberIdx)

        return binding.root
    }

    private fun getUser(memberIdx: Int) {
        DmMemberIdxServiceBuilder.dmMemberIdxService.getMemberIdx(memberIdx).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null) {
                    try {
                        val jsonData = response.body()!!.string()
                        Log.d("SUCCESS", jsonData)
                        val json = JSONObject(jsonData).getString("data")
                        val data = JSONObject(json)
                        setProfile(data.getString("name"))
                    } catch (e: JSONException) {
                        Log.e("ERROR", e.printStackTrace().toString(), e.cause)
                    }
                } else {
                    val error = response.errorBody()!!.toString()
                    Log.d("ERROR", "onResponse error: ${response.code()}")
                    Log.d("ERROR", "onResponse error: $error")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    // 프로필 설정
    private fun setProfile(name: String) {
        binding.textUserName.text = name
    }
}