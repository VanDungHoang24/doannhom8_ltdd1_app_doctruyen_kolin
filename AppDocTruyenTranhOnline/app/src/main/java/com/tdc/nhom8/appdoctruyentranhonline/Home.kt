package com.tdc.nhom8.appdoctruyentranhonline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tdc.nhom8.appdoctruyentranhonline.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng ViewBinding để liên kết layout
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Thay thế Fragment chính là HomeFragment
        replaceFragment(FrHome())

        // Thiết lập sự kiện cho Bottom Navigation
        setBottomNavigationListener()
    }

    private fun setBottomNavigationListener() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(FrHome())
                R.id.nav_colection -> replaceFragment(FrColection())
                R.id.nav_shop -> replaceFragment(FrShop())
                R.id.nav_profile -> replaceFragment(FrProfile())
                else -> {}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
