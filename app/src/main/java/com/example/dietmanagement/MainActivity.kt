package com.example.dietmanagement

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.dietmanagement.bottomnavigation.AlertFragment
import com.example.dietmanagement.bottomnavigation.BoardFragment
import com.example.dietmanagement.bottomnavigation.MainFragment
import com.example.dietmanagement.bottomnavigation.ProfileFragment
import com.example.dietmanagement.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

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
                        // TODO :: 로그아웃 로직 만들기
                    }
                }
                false
            }
            popup.show()
        }
    }

}