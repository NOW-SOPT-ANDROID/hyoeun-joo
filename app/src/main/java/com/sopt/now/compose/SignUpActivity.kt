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
import androidx.lifecycle.viewmodel.compose.viewModel
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
                    val signUpViewModel: SignUpViewModel = viewModel()
                    SignUpScreen(signUpViewModel)
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel) {
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
            value = signUpViewModel.signupId.value,
            onInputChange = { signUpViewModel.signupId.value = it },
            label = stringResource(R.string.string_id_hint)
        )

        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "PW", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = signUpViewModel.signupPw.value,
            onInputChange = { signUpViewModel.signupPw.value = it },
            label = stringResource(R.string.string_pw_hint)
        )
        Spacer(modifier = Modifier.height(46.dp))
        Text(text = "Name", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = signUpViewModel.signupName.value,
            onInputChange = { signUpViewModel.signupName.value = it },
            label = stringResource(R.string.tv_sign_up_nickname_hint)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Phone", fontSize = 30.sp, color = Color.Black)
        CustomTextField(
            value = signUpViewModel.signupPhone.value,
            onInputChange = { signUpViewModel.signupPhone.value = it },
            label = stringResource(R.string.tv_sign_up_phone_hint)
        )
        Spacer(modifier = Modifier.height(46.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    signUpViewModel.signUp(context)
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
