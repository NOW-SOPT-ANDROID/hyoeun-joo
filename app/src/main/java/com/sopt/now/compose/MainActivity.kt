package com.sopt.now.compose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.TextField.mainInfoText
import com.sopt.now.compose.feature.model.UserDataInput
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userData: UserDataInput? = intent.getParcelableExtra("user_data")

        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    userData?.let { user ->
                        MainScreen(user)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(userData: UserDataInput) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp)
            .padding(start = 8.dp)
    ) {
        val image: Painter = painterResource(id = R.drawable.profile)
        Image(
            painter = image,
            contentDescription = "Profile Image",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .padding(bottom = 150.dp)
            .padding(start = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Name: ${userData.userNickName}",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        mainInfoText(label = "ID", value = userData.userId)
        mainInfoText(label = "PW", value = userData.userPW)
        mainInfoText(label = "MBTI", value = userData.userMbti)
    }
}


@Preview(showBackground = true)
@Composable
fun Preview2() {
    NOWSOPTAndroidTheme {

    }
}
