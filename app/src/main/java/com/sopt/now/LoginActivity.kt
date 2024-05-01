package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.User.UserDataInput
import com.sopt.now.api.ServicePool.authService
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.dto.RequestLogInDto
import com.sopt.now.dto.RequestSignUpDto
import com.sopt.now.dto.ResponseLogInDto
import com.sopt.now.dto.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
       val userData = intent.getParcelableExtra<UserDataInput>(INTENT_USER_DATA)

//        fun showSnacker(view: View, message: String) {
//            Snackbar.make(
//                view,
//                message,
//                Snackbar.LENGTH_SHORT
//            ).show()
//        }
//        setContentView(binding.root)
//        val userData = intent.getParcelableExtra<UserDataInput>(INTENT_USER_DATA)
//
//        val signId = userData?.getUserSignUpId()
//        val signPw = userData?.getUserSignUpPw()
//
//        binding.btnLogin.setOnClickListener {
//            val editid = binding.etId.text.toString()
//            val editpw = binding.pw2.text.toString()
//            if (editid == signId && editpw == signPw) {
//                showSnacker(binding.root, getString(R.string.log_in_success))
//
//
//                val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
//                    putExtra(MainActivity.INTENT_USER_DATA, userData)
//
//                }
//                Log.d("IntentData", "User data: $userData")
//                startActivity(intent)
//                finish()
//            } else {
//                showSnacker(binding.root, getString(R.string.log_in_fail))
//            }
//        }
//        binding.btnSignup.setOnClickListener {
//            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun initViews(){
        binding.btnLogin.setOnClickListener{
            LogIn()
        }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
           startActivity(intent)
       }
    }

    private fun LogIn(){
        val loginRequest = getLogInRequestDto()
        authService.logIn(loginRequest).enqueue(object : Callback<ResponseLogInDto> {
            val userData = intent.getParcelableExtra<UserDataInput>(INTENT_USER_DATA)

            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>,
            ){
                if (response.isSuccessful) {
//                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    Toast.makeText(
                        this@LoginActivity,
                        "로그인 성공",
                        Toast.LENGTH_SHORT,
                    ).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                    putExtra(MainActivity.INTENT_USER_DATA, userData)
                }
                    startActivity(intent)

//                    val signUpModel: UserDataInput = UserDataInput(
//                        binding.etIdSignup.text.toString(),
//                        binding.etPwSignup.text.toString(),
//                        binding.etNicknameSignup.text.toString(),
//                        binding.etMbtiSignup.text.toString()
//                    )

//                    Log.d("SignUp", "data: $data, userId: $userId")

                    finish()

                }else {
                    val error = response.message()
                    Toast.makeText(
                        this@LoginActivity,
                        "회원가입 실패 $error",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "서버 에러 발생 ", Toast.LENGTH_SHORT).show()
            }
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
    companion object {
        const val INTENT_USER_DATA = "userData"
    }
}

