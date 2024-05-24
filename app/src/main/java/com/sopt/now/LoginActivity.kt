package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.dto.LoginDto.RequestLogInDto


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        initViews()
        observeLoginViewModel()

    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            val loginRequest = getLogInRequestDto()
            viewModel.logIn(loginRequest)
        }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeLoginViewModel() {
        viewModel.loginResult.observe(this, Observer { success ->
            if (success) {
                viewModel.userId.value?.let { userId ->
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("userId", userId)
                    startActivity(intent)
                    finish()
                }
            }
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }

    private fun getLogInRequestDto(): RequestLogInDto {
        val id = binding.etId.text.toString()
        val password = binding.pw2.text.toString()
        return RequestLogInDto(
            authenticationId = id,
            password = password
        )
    }
}

