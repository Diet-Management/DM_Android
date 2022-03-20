package com.example.dietmanagement

import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.dietmanagement.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    lateinit var nav: BottomNavigationView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        nav = binding.bottomNavigationView
        setBottomNav(navHostFragment)
        popUpMenu()

        val navController = navHostFragment.navController
        binding.profileImage.setOnClickListener {
            navController.navigateUp()
            navController.navigate(R.id.profileFragment)
        }
    }

    private fun setBottomNav(navHost: NavHostFragment) {
        val navController = navHost.navController
        nav.setupWithNavController(navController)
    }

    private fun popUpMenu() {
        binding.moreVertImage.setOnClickListener {
            val popup = PopupMenu(applicationContext, it)
            menuInflater.inflate(R.menu.more_vert_menu, popup.menu)
            popup.setOnMenuItemClickListener(object: PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(p0: MenuItem?): Boolean {
                    when(p0?.itemId) {
                        R.id.logout -> finish()
                    }
                    return false
                }
            })
            popup.show()
        }
    }
}