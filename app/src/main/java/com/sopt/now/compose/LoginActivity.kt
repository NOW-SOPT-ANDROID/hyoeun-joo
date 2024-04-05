package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getString

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                // A surface container using the 'background' color from the theme
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
fun LoginScreen(intent: Intent ) {
    var login_id by remember { mutableStateOf("") }
    var login_pw by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Welcome to sopt",
            fontSize = 40.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(86.dp))
        Text(text = "ID", fontSize = 30.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = login_id,
            onValueChange = { login_id = it },
            label = { Text(text = "ID를 입력하세요") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "PW", fontSize = 30.sp, color = Color.Black)
        TextField(
            value = login_pw,
            onValueChange = { login_pw = it },
            label = { Text(text = "비밀번호를 입력하세요") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(96.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    val loginId = login_id
                    val loginPw = login_pw
                    val toSignup = Intent(context,SignUpActivity::class.java)
                    context.startActivity(toSignup)
                          },
                modifier = Modifier.width(180.dp)
            ){
                Text(text = "회원가입"
                    , fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {

                val loginId = login_id
                val loginPw = login_pw
                val toMain = Intent(context,MainActivity::class.java)

                val signupId = intent.getStringExtra("signup_id")
                val signupPw = intent.getStringExtra("signup_pw")
                when{
                    loginId == signupId && loginPw == signupPw -> {
                        context.startActivity(toMain)
                        Toast.makeText(
                            context,
                            "로그인 성공"
                            ,Toast.LENGTH_SHORT
                        ).show()


                    }
                    else -> {
                        Toast.makeText(
                            context,
                            "ID 또는 비밀번호가 맞지 않습니다"
                            ,Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
                ,modifier = Modifier.width(180.dp)) {
                Text(text = "로그인"
                    , fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NOWSOPTAndroidTheme {
        Column {
//            LoginScreen()
        }
    }
}
