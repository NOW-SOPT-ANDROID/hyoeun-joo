package com.sopt.now.compose.presentation.SignUp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.presentation.theme.NOWSOPTAndroidTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.TextField.CustomTextField
import com.sopt.now.compose.domain.model.AuthEntity
import com.sopt.now.compose.presentation.Login.LoginActivity
import kotlin.math.sign
class SignUpActivity : ComponentActivity() {
    private val viewModel: SignUpViewModel by viewModels { SignUpViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpScreen(viewModel)
                }
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signUpResult.observe(this) { success ->
            if (success) {
                val toLogIn = Intent(this, LoginActivity::class.java)
                startActivity(toLogIn)
                finish()
            }
        }
        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel) {
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
        TextField(
            value = signUpViewModel.signUpId.value,
            onValueChange = { signUpViewModel.signUpId.value = it },
            label = { Text(stringResource(R.string.string_id_hint)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "PW", fontSize = 30.sp, color = Color.Black)
        TextField(
            value = signUpViewModel.signUpPw.value,
            onValueChange = { signUpViewModel.signUpPw.value = it },
            label = { Text(stringResource(R.string.string_pw_hint)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "Name", fontSize = 30.sp, color = Color.Black)
        TextField(
            value = signUpViewModel.signUpName.value,
            onValueChange = { signUpViewModel.signUpName.value = it },
            label = { Text(stringResource(R.string.tv_sign_up_nickname_hint)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Phone", fontSize = 30.sp, color = Color.Black)
        TextField(
            value = signUpViewModel.signUpPhone.value,
            onValueChange = { signUpViewModel.signUpPhone.value = it },
            label = { Text(stringResource(R.string.tv_sign_up_phone_hint)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val authData = AuthEntity(
                        id = signUpViewModel.signUpId.value,
                        pw = signUpViewModel.signUpPw.value,
                        name = signUpViewModel.signUpName.value,
                        phone = signUpViewModel.signUpPhone.value,
                    )
                    signUpViewModel.signUp(authData)
                },
                modifier = Modifier.width(280.dp)
            ) {
                Text(text = stringResource(R.string.sign_up_btn), fontSize = 30.sp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    NOWSOPTAndroidTheme {
//        SignUpScreen()
    }
}
