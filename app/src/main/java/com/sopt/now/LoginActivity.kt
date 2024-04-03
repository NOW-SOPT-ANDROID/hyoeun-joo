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
        binding.btnLogin.setOnClickListener {
            val editid = binding.etId.text
            val editpw = binding.pw2.text
            if (editid.length < 6) {
                Snackbar.make(
                    binding.root,
                    "ID가 잘못되었습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else if (editpw.length < 6 || editpw.length > 10) {
                Snackbar.make(
                    binding.root,
                    "PW가 잘못되었습니다",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                Snackbar.make(
                    binding.root,
                    "로그인 성공",
                    Snackbar.LENGTH_SHORT
                ).show()

                startActivity(intent)
            }
            binding.btnSignup.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(intent)
            }
        }


    }


}