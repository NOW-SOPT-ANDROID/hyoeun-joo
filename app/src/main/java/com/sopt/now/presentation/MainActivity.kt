package com.sopt.now.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.presentation.Home.HomeFragment
import com.sopt.now.R
import com.sopt.now.presentation.Home.MyPageFragment
import com.sopt.now.presentation.Home.SearchFragment
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userId = intent.getStringExtra("userId")
        Log.d("main","$userId")

        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvHome.id, HomeFragment())
                .commit()
        }
        clickBottomNavigation()
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.menu_mypage -> {
                    val userId = intent.getStringExtra("userId")

                    userId?.let {
                        val myPageFragment = MyPageFragment()
                        val bundle = Bundle().apply {
                            putString("userId", it)
                        }
                        myPageFragment.arguments = bundle
                        replaceFragment(myPageFragment)
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

}


