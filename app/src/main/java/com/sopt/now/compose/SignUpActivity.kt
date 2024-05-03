package com.sopt.now.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import android.content.Context
import android.content.Intent
import android.os.Message
import android.util.Log
import android.view.View
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.sopt.now.compose.Constants.Constant.Companion.MAX_ID_LENGTH
import com.sopt.now.compose.Constants.Constant.Companion.MAX_MBTI_LENGTH
import com.sopt.now.compose.Constants.Constant.Companion.MAX_PW_LENGTH
import com.sopt.now.compose.Constants.Constant.Companion.MIN_ID_LENGTH
import com.sopt.now.compose.Constants.Constant.Companion.MIN_PW_LENGTH
import com.sopt.now.compose.TextField.CustomTextField
import com.sopt.now.compose.api.AuthService
import com.sopt.now.compose.api.ServicePool
import com.sopt.now.compose.api.ServicePool.authService
import com.sopt.now.compose.dto.RequestSignUpDto
import com.sopt.now.compose.dto.ResponseSignUpDto
import com.sopt.now.compose.feature.model.UserDataInput
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpScreen()
                }
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    var signup_id by remember { mutableStateOf("") }
    var signup_pw by remember { mutableStateOf("") }
    var signup_name by remember { mutableStateOf("") }
    var signup_phone by remember { mutableStateOf("") }
    val context = LocalContext.current

    fun getSignUpRequestDto(): RequestSignUpDto {
        return RequestSignUpDto(
            authenticationId = signup_id,
            password = signup_pw,
            nickname = signup_name,
            phone = signup_phone
        )
    }

    fun signUp(context: Context) {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    Toast.makeText(
                        context,
                        "회원가입 성공 유저의 ID는 $userId 입니둥",
                        Toast.LENGTH_SHORT,
                    ).show()
                    Log.d("SignUp", "data: $data, userId: $userId")

                    val toLogIn = Intent(context, LoginActivity::class.java)
                    context.startActivity(toLogIn)
                } else {
                    val error = response.message()
                    Toast.makeText(
                        context,
                        "로그인이 실패 $error",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                Log.e("SignUp", "서버 요청 실패", t)
                Toast.makeText(context, "서버 에러 발생 ", Toast.LENGTH_SHORT).show()
            }
        })
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.tv_sign_up_title),
            fontSize = 40.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "ID", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = signup_id,
            onInputChange = { signup_id = it },
            label = stringResource(R.string.string_id_hint)
        )

        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "PW", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = signup_pw,
            onInputChange = { signup_pw = it },
            label = stringResource(R.string.string_pw_hint)
        )
        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "Name", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = signup_name,
            onInputChange = { signup_name = it },
            label = stringResource(R.string.tv_sign_up_nickname_hint)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Phone", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = signup_phone,
            onInputChange = { signup_phone = it },
            label = stringResource(R.string.tv_sign_up_phone_hint)
        )
        Spacer(modifier = Modifier.height(46.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    signUp(context)
                },
                modifier = Modifier.width(280.dp)
            ) {
                Text(text = stringResource(R.string.sign_up_btn), fontSize = 30.sp)
            }
        }
    }
}

fun showToast(context: Context, message: Int) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    NOWSOPTAndroidTheme {
//        SignUpScreen()
    }
}
