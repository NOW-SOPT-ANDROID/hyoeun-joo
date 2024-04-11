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
        val userData = intent.getParcelableExtra<UserDataInput>(LoginActivity.INTENT_USER_DATA)

        val signId = userData?.getUserSignUpId()
        val signPw = userData?.getUserSignUpPw()
        val signNickname = userData?.getUserSignUpNickName()
        val signMbti = userData?.getUserSignUpMbti()

        with(binding) {
            tvUserId.text = "ID: $signId"
            tvUserPw.text = "Password: $signPw"
            tvUserName.text = "Name: $signNickname"
            tvUserMbti.text = "MBTI: $signMbti"
        }
    }

    companion object {
        const val INTENT_USER_DATA = "userData"
    }
}

