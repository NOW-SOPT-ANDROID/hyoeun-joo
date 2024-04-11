package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getStringExtra("userId")
        val userPw = intent.getStringExtra("userPw")
        val userName = intent.getStringExtra("userName")
        val userMbti = intent.getStringExtra("userMbti")

        with(binding) {
            tvUserId.text = "ID: $userId"
            tvUserPw.text = "Password: $userPw"
            tvUserName.text = "Name: $userName"
            tvUserMbti.text = "MBTI: $userMbti"
        }
    }
}

