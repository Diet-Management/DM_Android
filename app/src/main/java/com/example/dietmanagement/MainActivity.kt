package com.example.dietmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.data.data.response.BaseResponse
import com.example.data.retrofit.builder.DmDeleteUserBuilder
import com.example.data.retrofit.builder.DmLogoutServiceBuilder
import com.example.dietmanagement.bottomnavigation.AlertFragment
import com.example.dietmanagement.bottomnavigation.BoardFragment
import com.example.dietmanagement.bottomnavigation.MainFragment
import com.example.dietmanagement.bottomnavigation.ProfileFragment
import com.example.dietmanagement.databinding.ActivityMainBinding
import com.example.dietmanagement.login.activity.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().add(R.id.linear_main, MainFragment()).commit()

        popUpMenu()

        binding.profileImage.setOnClickListener {

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.home_Fragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.linear_main, MainFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.board_Fragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.linear_main, BoardFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.alert_Fragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.linear_main, AlertFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.profile_Fragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.linear_main, ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString("memberIdx", intent.getStringExtra("memberIdx"))
                    }
                }).commitAllowingStateLoss()
                return true
            }
        }
        return false
    }

    private fun popUpMenu() {
        binding.moreVertImage.setOnClickListener {
            val popup = PopupMenu(applicationContext, it)
            menuInflater.inflate(R.menu.more_vert_menu, popup.menu)
            popup.setOnMenuItemClickListener { p0 ->
                when (p0?.itemId) {
                    R.id.logout -> {
                        Log.d("SUCCCESS", "popUpMenu access: ${intent.getStringExtra("accessToken")}")
                        DmLogoutServiceBuilder.dmLogoutService.logoutResponse(intent.getStringExtra("accessToken").toString(), intent.getStringExtra("refreshToken").toString())
                            .enqueue(object :Callback<ResponseBody> {
                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>,
                            ) {
                                if (response.body() != null) {
                                    try {
                                        val responseBody = response.body().toString()
                                        Log.d("SUCCESS", "logout response: $response")
                                        Log.d("SUCCESS", "logout response body: $responseBody")
                                        Toast.makeText(this@MainActivity, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                                    } catch (e: JSONException) {
                                        Toast.makeText(this@MainActivity, "로그아웃 실패함.", Toast.LENGTH_SHORT).show()
                                        Log.e("FAIL", "catch logout error: ${e.printStackTrace()}", e.cause)
                                    }
                                } else {
                                    Toast.makeText(this@MainActivity, "로그아웃 실패함.", Toast.LENGTH_SHORT).show()
                                    Log.d("FAIL", "logout error: ${response.errorBody()?.string()}, code: ${response.code()}, message: ${response.message()}")
                                }
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.e("FAIL", "onFailure error: ${t.printStackTrace()}", t.cause)
                            }

                        })
                    }

                    R.id.del_user -> {
                        DmDeleteUserBuilder.dmDeleteUserService.deleteUser(intent.getStringExtra("accessToken").toString(), intent.getStringExtra("refreshToken").toString())
                            .enqueue(object :Callback<ResponseBody> {
                                override fun onResponse(
                                    call: Call<ResponseBody>,
                                    response: Response<ResponseBody>
                                ) {
                                    if (response.body() != null) {
                                        val responseBody = response.body()?.string()
                                        Log.d("SUCCESS", "onResponse delete user: $responseBody")
                                        finish()
                                    } else {
                                        Toast.makeText(this@MainActivity, "오류 발생", Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                    Toast.makeText(this@MainActivity, "회원탈퇴 실패함", Toast.LENGTH_SHORT).show()
                                }
                        })
                    }
                }
                false
            }
            popup.show()
        }
    }

}