package com.sopt.now.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.sopt.now.compose.Profile.ProfileFriendItem
import com.sopt.now.compose.Profile.ProfileUserItem
import com.sopt.now.compose.TextField.mainInfoText
import com.sopt.now.compose.feature.model.UserDataInput
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import kotlin.collections.listOf


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
                       MyPageScreen(user)
                    }
                    ScaffoldExample(userData = userData)
                }
            }

        }
    }
}
@Composable
fun ScaffoldExample(userData: UserDataInput?) {
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
                        icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.label)},
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
            when(selectedItem) {
                0 -> HomeScreen(viewModel = HomeViewModel())
                1 -> SearchScreen()
                2 -> userData?.let { MyPageScreen(it) }
            }

        }
    }
}


@Composable
fun MyPageScreen(userData: UserDataInput) {
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
            text = "Name: ${userData.userNickName}",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        mainInfoText(label = stringResource(R.string.string_id), value = userData.userId)
        mainInfoText(label = stringResource(R.string.string_pw), value = userData.userPW)
        mainInfoText(label = stringResource(R.string.string_mbti), value = userData.userMbti)
    }
}
@Composable
fun SearchScreen(){
    Text(text = "search화면")
}

@Composable
fun HomeScreen(viewModel: HomeViewModel){
    LazyColumn {
            items(viewModel.mockUserList){
                friend -> ProfileUserItem(friend = friend)
            }
         items(viewModel.mockFriendList){
                friend -> ProfileFriendItem(friend = friend)
        }
        }
    }


