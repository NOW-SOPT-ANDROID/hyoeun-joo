package com.sopt.now.presentation.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.domain.model.AuthEntity
import com.sopt.now.presentation.MainActivity
import com.sopt.now.presentation.SignUpActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        observeLoginViewModel()

    }

    private fun initViews() {
        binding.btnLogin.setOnClickListener {
            val loginRequest = getLoginData()
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

    private fun getLoginData(): AuthEntity {
        val id = binding.etId.text.toString()
        val password = binding.pw2.text.toString()
        return AuthEntity(
            id = id,
            pw = password
        )
    }

}

