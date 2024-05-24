package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.dto.SignUp.RequestSignUpDto


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        initViews()
        observeSignUpViewModel()
    }

    private fun initViews() {
        binding.btnSignup2.setOnClickListener {
            val signUpRequest = getSignUpRequestDto()
            viewModel.signUp(signUpRequest)
        }
    }

    private fun observeSignUpViewModel() {
        viewModel.signUpResult.observe(this, Observer { success ->
            if (success) {
                viewModel.userId.value?.let { userId ->
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java).apply {
                        putExtra("userId", userId)
                    }
                    Toast.makeText(
                        this@SignUpActivity,
                        R.string.sign_up_success,
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(intent)
                    finish()
                }
            }
        })
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            Toast.makeText(this@SignUpActivity, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.etIdSignup.text.toString()
        val password = binding.etPwSignup.text.toString()
        val nickname = binding.etNicknameSignup.text.toString()
        val phoneNumber = binding.etMbtiSignup.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }
}
