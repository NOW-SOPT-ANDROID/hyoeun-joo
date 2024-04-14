package com.sopt.now.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.now.MainActivity
import com.sopt.now.SignUpActivity
import com.sopt.now.UserDataInput
import com.sopt.now.databinding.FragmentMyPageBinding


class MyPageFragment: Fragment() {
    //Nullable하고, OnDestroyView()에서 null로 해제해주는 객체
    private var _binding: FragmentMyPageBinding? = null
    //NonNullable하고, get()을 이용하여 값을 넣어주는 객체
    //binding은 항상 _binding을 반환하기 때문에 null이 될 수 없다. val로 선언하기
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val userData = arguments?.getParcelable<UserDataInput>(MainActivity.INTENT_USER_DATA)

        val signId = userData?.getUserSignUpId()
        val signPw = userData?.getUserSignUpPw()
        val signNickname = userData?.getUserSignUpNickName()
        val signMbti = userData?.getUserSignUpMbti()

        with(binding) {
            tvUserId.text = "ID: $signId"
            tvUserPw.text = "Password: $signPw"
            tvUserName.text = "Name: $signNickname"
            tvUserMbti.text = "MBTI: $signMbti"
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val INTENT_USER_DATA = "userData"
    }
}