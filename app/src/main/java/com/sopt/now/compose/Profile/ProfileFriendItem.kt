package com.sopt.now.compose.Profile
import androidx.compose.foundation.Image
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.presentation.Home.Friend
import com.sopt.now.compose.R


@Composable
fun ProfileFriendItem(friend: Friend) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .height(110.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        val image: Painter = painterResource(id = R.drawable.ic_friend_black_24)
        Image(
            painter = image,
            contentDescription = "Profile Image",
            modifier = Modifier
                .padding(start = 8.dp)
                .padding(top = 8.dp)
                .padding(bottom = 8.dp)


        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = friend.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = friend.selfDescription,
            fontSize = 14.sp,
        )
    }
}

@Preview
@Composable
fun ProfileFriendItemPreview() {
    val friend = Friend(name = "hi", selfDescription = "sd", profileImage = R.drawable.profile )
    ProfileFriendItem(friend = friend)
}
