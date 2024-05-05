package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.api.ServicePool.authService
import com.sopt.now.databinding.ActivitySignupBinding
import com.sopt.now.dto.SignUp.RequestSignUpDto
import com.sopt.now.dto.SignUp.ResponseSignUpDto
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {
        binding.btnSignup2.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    Toast.makeText(
                        this@SignUpActivity,
                        "회원가입 성공 유저의 ID는 $userId 입니다",
                        Toast.LENGTH_SHORT,
                    ).show()

                    userId?.let{
                        val intent = Intent(this@SignUpActivity,LoginActivity::class.java).apply {
                            putExtra("userId", it)
                        }
                        startActivity(intent)
                    }

                    finish()

                } else {
                    val errorBody = response.errorBody()?.string() ?: "No error message"
                    val errorMessage = JSONObject(errorBody).getString("message")

                    Toast.makeText(
                        this@SignUpActivity,
                        "회원가입 실패 $errorMessage",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "서버 에러 발생 ", Toast.LENGTH_SHORT).show()
            }
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
