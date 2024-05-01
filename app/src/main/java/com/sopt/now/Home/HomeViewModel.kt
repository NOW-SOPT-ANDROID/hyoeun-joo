package com.sopt.now.Home

import androidx.lifecycle.ViewModel
import com.sopt.now.Friend.FriendData
import com.sopt.now.R
import com.sopt.now.User.UserData

class HomeViewModel : ViewModel() {

    val mockFriendList = listOf<FriendData>(
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
        ),
        FriendData(
            profileImage = R.drawable.ic_person_black_24,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
        ),


        )

    val mockUserList = listOf<UserData>(
        UserData(
            profileImage = R.drawable.pic,
            name = "주효은",
            selfDescription = "34기 YB입니다!!",
        ),
    )
}