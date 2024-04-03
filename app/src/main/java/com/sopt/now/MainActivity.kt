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

        // SignUpActivity로부터 전달된 값들을 받아옴
        val userId = intent.getStringExtra("userId")
        val userPw = intent.getStringExtra("userPw")
        val userName = intent.getStringExtra("userName")
        val userMbti = intent.getStringExtra("userMbti")

        // 받아온 값을 각각의 TextView에 설정
        binding.tvUserId.text = "ID: $userId"
        binding.tvUserPw.text = "Password: $userPw"
        binding.tvUserName.text = "Name: $userName"
        binding.tvUserMbti.text = "MBTI: $userMbti"
    }
}

