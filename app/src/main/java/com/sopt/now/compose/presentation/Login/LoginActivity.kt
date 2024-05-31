package com.sopt.now.compose.presentation.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.presentation.theme.NOWSOPTAndroidTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.sopt.now.compose.R
import com.sopt.now.compose.domain.model.AuthEntity
import com.sopt.now.compose.presentation.MainActivity
import com.sopt.now.compose.presentation.SignUp.SignUpActivity

class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(viewModel)
                }
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { success ->
            if (success) {
                val toMain = Intent(this, MainActivity::class.java)
                toMain.putExtra("userId", viewModel.userId.value)
                startActivity(toMain)
                finish()
            }
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
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
        TextField(
            value = viewModel.loginId.value,
            onValueChange = { viewModel.loginId.value = it },
            label = { Text(stringResource(R.string.string_id_hint)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "PW", fontSize = 30.sp, color = Color.Black)
        TextField(
            value = viewModel.loginPw.value,
            onValueChange = { viewModel.loginPw.value = it },
            label = { Text(stringResource(R.string.string_pw_hint)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
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
                    val authData = AuthEntity(
                        id = viewModel.loginId.value,
                        pw = viewModel.loginPw.value,
                    )
                    viewModel.login(authData)
                }, modifier = Modifier.width(180.dp)) {
                    Text(text = stringResource(R.string.log_in_btn), fontSize = 20.sp)
                }
            }
        }
    }
}


