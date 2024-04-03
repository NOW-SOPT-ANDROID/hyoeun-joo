package com.sopt.now

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signid = intent.getStringExtra("userId")
        val signpw = intent.getStringExtra("userPw")
        val signname = intent.getStringExtra("userName")
        val signmbti = intent.getStringExtra("userMbti")

        binding.btnLogin.setOnClickListener {
            val editid = binding.etId.text.toString()
            val editpw = binding.pw2.text.toString()
            if (editid == signid && editpw == signpw) {
                Snackbar.make(
                    binding.root,
                    "로그인 성공",
                    Snackbar.LENGTH_SHORT
                ).show()

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("userId", signid.toString())
                intent.putExtra("userPw", signpw.toString())
                intent.putExtra("userName", signname.toString())
                intent.putExtra("userMbti", signmbti.toString())
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    "로그인 실패",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }



        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}

