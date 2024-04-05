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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

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
fun SignUpScreen( ) {
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
            text = "Sign Up",
            fontSize = 40.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "ID", fontSize = 30.sp, color = Color.Black)
        TextField(
            value = signup_id,
            onValueChange = { signup_id = it
                            },
            label = { Text(text = "ID를 입력하세요") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "PW", fontSize = 30.sp, color = Color.Black)
        TextField(
            value = signup_pw,
            onValueChange = { signup_pw = it
                },
            label = { Text(text = "비밀번호를 입력하세요") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "Name", fontSize = 30.sp, color = Color.Black)
        TextField(
            value = signup_name,
            onValueChange = { signup_name = it },
            label = { Text(text = "NickName를 입력하세요") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "MBTI", fontSize = 30.sp, color = Color.Black)
        TextField(
            value = signup_mbti,
            onValueChange = { signup_mbti = it },
            label = { Text(text = "MBTI를 입력하세요") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = {
                when {
                    signup_id.length !in 6..10 -> {
                        Toast.makeText(
                            context,
                            "ID는 6자 이상, 10자 이하이어야 합니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    signup_pw.length !in 8..12 -> {
                        Toast.makeText(
                            context,
                            "비밀번호는 8자 이상, 12자 이하이어야 합니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    signup_name.isBlank() -> {
                        Toast.makeText(
                            context,
                            "닉네임을 입력하세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    signup_mbti.length > 4 -> {
                        Toast.makeText(
                            context,
                            "MBTI는 4자 이하여야 합니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        val toLogin = Intent(context,LoginActivity::class.java)
                        toLogin.putExtra("signup_id", signup_id)
                        toLogin.putExtra("signup_pw",signup_pw)
                        toLogin.putExtra("signup_name", signup_name)
                        toLogin.putExtra("signup_mbti", signup_mbti)
                        context.startActivity(toLogin)

                        Toast.makeText(
                            context,
                            "로그인 성공",
                            Toast.LENGTH_SHORT
                        )
                    }
                }
            },
                modifier = Modifier.width(280.dp)
            ) {
                Text(text = "회원가입 하기", fontSize = 30.sp)


            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    NOWSOPTAndroidTheme {
        SignUpScreen()
    }
}
