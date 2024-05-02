package com.sopt.now
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.User.UserDataInput
import com.sopt.now.api.ServicePool.authService
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.dto.RequestLogInDto
import com.sopt.now.dto.ResponseLogInDto
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

    }

    private fun initViews(){
        binding.btnLogin.setOnClickListener{
            logIn()
        }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
           startActivity(intent)
       }
    }

    private fun logIn(){
        val loginRequest = getLogInRequestDto()
        authService.logIn(loginRequest).enqueue(object : Callback<ResponseLogInDto> {
            val userData = intent.getParcelableExtra<UserDataInput>(INTENT_USER_DATA)

            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>,
            ){
                if (response.isSuccessful) {
//                    val userId = response.headers()["location"]
                    Toast.makeText(
                        this@LoginActivity,
                        "로그인 성공",
                        Toast.LENGTH_SHORT,
                    ).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                    putExtra(MainActivity.INTENT_USER_DATA, userData)
                }
                    startActivity(intent)
                    finish()

                }else {
                    val error = response.message()
                    Toast.makeText(
                        this@LoginActivity,
                        "로그인 실패 $error",
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

