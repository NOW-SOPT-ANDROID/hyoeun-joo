package com.sopt.now.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sopt.now.User.UserDataInput
import com.sopt.now.api.ApiFactory
import com.sopt.now.api.ServicePool
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.dto.ResponseLogInDto
import com.sopt.now.dto.ResponseUserProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MyPageFragment: Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding)

    private var userId: String? = null

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

        userId = arguments?.getString("userId")
        Log.d("MyPageFragment", "userId: $userId")
        userId?.let { getUserInfo(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUserInfo(userId: String) {
        ServicePool.authService.getUserInfo(userId.toInt()).enqueue(object : Callback<ResponseUserProfile> {
            override fun onResponse(
                call: Call<ResponseUserProfile>,
                response: Response<ResponseUserProfile>
            ) {
                if (response.isSuccessful) {
                    val userProfile = response.body()
                    Log.d("MyPageFragment", "User profile: $userProfile")

                    userProfile?.let {
                        with(binding) {
                            tvUserId.text = "ID: ${it.data.authenticationId}"
//                            tvUserPw.text = "Password: ${it.password}"
                            tvUserName.text = "Name: ${it.data.nickname}"
                            tvUserMbti.text = "MBTI: ${it.data.phone}"

                        }
                    }
                } else {
                    onFailure(call, Throwable("Fail: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<ResponseUserProfile>, t: Throwable) {
                Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }
}
