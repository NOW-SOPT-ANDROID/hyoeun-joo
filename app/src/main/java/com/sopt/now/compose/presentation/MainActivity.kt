package com.sopt.now.compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.BottomNavItem
import com.sopt.now.compose.presentation.Home.HomeViewModel
import com.sopt.now.compose.Profile.ProfileFriendItem
import com.sopt.now.compose.Profile.ProfileUserItem
import com.sopt.now.compose.R
import com.sopt.now.compose.TextField.mainInfoText
import com.sopt.now.compose.data.api.ServicePool
import com.sopt.now.compose.data.dto.ResponseUserProfile
import com.sopt.now.compose.presentation.theme.NOWSOPTAndroidTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.listOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId: String? = intent.getStringExtra("userId")

        setContent {
            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    userId?.let { user ->
                        ScaffoldExample(userId)
                    }
                }
            }
        }
    }
}

@Composable
fun ScaffoldExample(userId: String) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.MyPage
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when (selectedItem) {
                0 -> HomeScreen(viewModel = HomeViewModel())
                1 -> SearchScreen()
                2 -> {
                    userId?.let {
                        MyPageScreen(userId = it)
                    }
                }
            }
        }
    }
}

@Composable
fun MyPageScreen(userId: String) {
    var userProfile by remember { mutableStateOf<ResponseUserProfile?>(null) }

    fun getUserInfo(userId: String) {
        ServicePool.authService.getUserInfo(userId.toInt())
            .enqueue(object : Callback<ResponseUserProfile> {
                override fun onResponse(
                    call: Call<ResponseUserProfile>,
                    response: Response<ResponseUserProfile>,
                ) {
                    if (response.isSuccessful) {
                        userProfile = response.body()
                        userProfile?.let {
                            userProfile = it
                        }
                    } else {
                        onFailure(call, Throwable("Fail: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<ResponseUserProfile>, t: Throwable) {

                }
            })
    }
    getUserInfo(userId)


    userProfile?.let { profile ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
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
                    text = "Name: ${profile.data.nickname}",
                    fontSize = 30.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, start = 20.dp)
                )
                mainInfoText(
                    label = stringResource(R.string.string_id),
                    value = profile.data.authenticationId
                )
                mainInfoText(
                    label = stringResource(R.string.string_phone),
                    value = profile.data.phone
                )
            }
        }
    }
}

@Composable
fun SearchScreen() {
    Text(text = "Search Screen")
}

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    LazyColumn {
        items(viewModel.mockUserList) { friend ->
            ProfileUserItem(friend = friend)
        }
        items(viewModel.mockFriendList) { friend ->
            ProfileFriendItem(friend = friend)
        }
    }
}
