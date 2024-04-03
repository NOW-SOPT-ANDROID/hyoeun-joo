package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignup2.setOnClickListener {
            val signid = binding.etIdSignup.text
            val signpw = binding.etPwSignup.text
            val signname = binding.etNicknameSignup.text
            val signmbti = binding.etMbtiSignup.text

            if (signid.length < 6 || signid.length > 10) {
                Snackbar.make(
                    binding.root,
                    "ID는 6~10글자 사이로 입력해주세요",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else if (signpw.length < 8 || signpw.length >12) {
                Snackbar.make(
                    binding.root,
                    "패스워드는 8~12글자 사이로 입력해주세요",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else if (signname.length < 1 || signname.isBlank()) {
                Snackbar.make(
                    binding.root,
                    "닉네임은 한 글자 이상이어야 하며, 공백은 불가합니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else if (signmbti.length < 4) {
                Snackbar.make(
                    binding.root,
                    "MBTI 형식에 맞게 입력해주세요",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else if (signid.isNullOrEmpty() || signpw.isNullOrEmpty() ||
                signname.isNullOrEmpty() || signmbti.isNullOrEmpty()
            ) {
                Toast.makeText(
                    applicationContext,
                    "회원가입 실패",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                intent.putExtra("userId", signid.toString())
                intent.putExtra("userPw", signpw.toString())
                intent.putExtra("userName", signname.toString())
                intent.putExtra("userMbti", signmbti.toString())
                startActivity(intent)


                Toast.makeText(
                    // 애플리케션 전체적인 컨엑스를 나타내는 객체,
                    applicationContext,
                    "회원가입 성공",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }



        }
    }

}