package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import androidx.compose.ui.res.stringResource
import com.sopt.now.compose.TextField.CustomTextField
import com.sopt.now.compose.api.ServicePool.authService
import com.sopt.now.compose.dto.RequestLogInDto
import com.sopt.now.compose.dto.ResponseLogInDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(intent)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(intent: Intent) {
    var login_id by remember { mutableStateOf("") }
    var login_pw by remember { mutableStateOf("") }
    val context = LocalContext.current

    fun getLogInRequestDto(): RequestLogInDto {
        val id = login_id
        val password = login_pw
        return RequestLogInDto(
            authenticationId = id,
            password = password
        )
    }

    fun logIn() {
        val loginRequest = getLogInRequestDto()
        authService.logIn(loginRequest).enqueue(object : Callback<ResponseLogInDto> {
            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    Toast.makeText(
                        context,
                        "로그인 성공",
                        Toast.LENGTH_SHORT,
                    ).show()

                    val intent = Intent(context, MainActivity::class.java).apply {
                        putExtra("userId", userId)
                    }
                    context.startActivity(intent)
                } else {
                    val error = response.message()
                    Toast.makeText(
                        context,
                        "로그인 실패 $error",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {
                Toast.makeText(context, "서버 에러 발생 ", Toast.LENGTH_SHORT).show()
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.tv_log_in_title),
            fontSize = 40.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(86.dp))
        Text(text = "ID", fontSize = 30.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = login_id,
            onInputChange = { login_id = it },
            label = stringResource(R.string.string_id_hint)
        )

        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "PW", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = login_pw,
            onInputChange = { login_pw = it },
            label = stringResource(R.string.string_pw_hint),
            isPwSecret = true
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        val toSignup = Intent(context, SignUpActivity::class.java)
                        context.startActivity(toSignup)
                    },
                    modifier = Modifier.width(180.dp)
                ) {
                    Text(text = stringResource(R.string.sign_up_btn), fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = {
                    logIn()
                }, modifier = Modifier.width(180.dp)) {
                    Text(text = stringResource(R.string.log_in_btn), fontSize = 20.sp)
                }
            }
        }
    }
}
