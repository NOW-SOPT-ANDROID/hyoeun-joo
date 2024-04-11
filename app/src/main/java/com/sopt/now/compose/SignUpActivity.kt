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
import android.view.View
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.sopt.now.compose.TextField.CustomTextField

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
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
    var signup_pw by remember {
        mutableStateOf("")
    }
    var signup_name by remember { mutableStateOf("") }
    var signup_mbti by remember { mutableStateOf("") }
    val context = LocalContext.current
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
        Text(text = "MBTI", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = signup_mbti,
            onInputChange = { signup_mbti = it },
            label = stringResource(R.string.tv_sign_up_mbti_hint)
        )
        Spacer(modifier = Modifier.height(46.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    when {
                        signup_id.length !in 6..10 -> {
                            showToast(context, R.string.id_error)

                        }

                        signup_pw.length !in 8..12 -> {
                            showToast(context, R.string.pw_error)

                        }

                        signup_name.isBlank() -> {
                            showToast(context, R.string.nickname_error)

                        }

                        signup_mbti.length > 4 -> {
                            showToast(context, R.string.mbti_error)

                        }

                        else -> {
                            val toLogin = Intent(context, LoginActivity::class.java)
                            toLogin.putExtra("signup_id", signup_id)
                            toLogin.putExtra("signup_pw", signup_pw)
                            toLogin.putExtra("signup_name", signup_name)
                            toLogin.putExtra("signup_mbti", signup_mbti)
                            context.startActivity(toLogin)

                            showToast(context, R.string.log_in_success)

                        }
                    }
                },
                modifier = Modifier.width(280.dp)
            ) {
                Text(text = "회원가입 하기", fontSize = 30.sp)


            }
        }


    }

//
//    companion object {
//        const val MIN_ID_LENGTH = 6
//        const val MAX_ID_LENGTH = 10
//        const val MIN_PW_LENGTH = 8
//        const val MAX_PW_LENGTH = 12
//        const val MIN_NICKNAME_LENGTH = 1
//        const val MAX_MBTI_LENGTH = 4
//
//    }
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
        SignUpScreen()
    }
}
