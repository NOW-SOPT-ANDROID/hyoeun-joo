package com.sopt.now.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.domain.model.AuthEntity
import com.sopt.now.presentation.Login.LoginActivity
import com.sopt.now.presentation.User.SignUpViewModelFactory


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel: SignUpViewModel by viewModels { SignUpViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        observeSignUpViewModel()
    }

    private fun initViews() {
        binding.btnSignup2.setOnClickListener {
            val signUpRequest = getSignUpData()
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

    private fun getSignUpData(): AuthEntity {
        val id = binding.etIdSignup.text.toString()
        val password = binding.etPwSignup.text.toString()
        val nickname = binding.etNicknameSignup.text.toString()
        val phoneNumber = binding.etMbtiSignup.text.toString()
        return AuthEntity(
            id = id,
            pw = password,
            name = nickname,
            phone = phoneNumber
        )
    }
}
