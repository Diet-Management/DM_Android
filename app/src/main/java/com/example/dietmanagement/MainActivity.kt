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

    lateinit var nav: BottomNavigationView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.linear_main, MainFragment()).commit()

        val fragment = MainFragment()
        val memberIdx = intent.getStringExtra("memberIdx")
        if (memberIdx != null) {
            fragment.apply {
                this.arguments = savedInstanceState.apply {
                    this?.putString("memberIdx", memberIdx.toString())
                }
            }
            Log.d("INTENT", "onCreate intent putExtra: $memberIdx")
        }

        popUpMenu()

        binding.profileImage.setOnClickListener {

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.homeFragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.linear_main, MainFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.boardFragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.linear_main, BoardFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.alertFragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.linear_main, AlertFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.profileFragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.linear_main, ProfileFragment()).commitAllowingStateLoss()
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
                    R.id.logout -> finish()
                }
                false
            }
            popup.show()
        }
    }

}