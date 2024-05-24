package com.sopt.now.compose

import LoginViewModel
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
import androidx.lifecycle.viewmodel.compose.viewModel
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
                    val loginViewModel: LoginViewModel = viewModel()
                    LoginScreen(loginViewModel)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    val context = LocalContext.current

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
            value = loginViewModel.loginId.value,
            onInputChange = { loginViewModel.loginId.value = it },
            label = stringResource(R.string.string_id_hint)
        )

        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "PW", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = loginViewModel.loginPw.value,
            onInputChange = { loginViewModel.loginPw.value = it },
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
                    loginViewModel.logIn(context)
                }, modifier = Modifier.width(180.dp)) {
                    Text(text = stringResource(R.string.log_in_btn), fontSize = 20.sp)
                }
            }
        }
    }
}
