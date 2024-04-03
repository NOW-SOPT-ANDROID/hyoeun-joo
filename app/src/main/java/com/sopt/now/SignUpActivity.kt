package com.sopt.now

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

            if (signid.isNullOrEmpty() || signpw.isNullOrEmpty() ||
                signname.isNullOrEmpty() || signmbti.isNullOrEmpty()
            ) {
                Toast.makeText(
                    applicationContext,
                    "회원가입 실패\n",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (signid.length < 6 || signid.length > 10) {
                Snackbar.make(
                    binding.root,
                    "ID는 6~~10글자 사이로 입력해주세요",
                    Snackbar.LENGTH_SHORT
                ).show()


            } else {
            }
        }
    }

}