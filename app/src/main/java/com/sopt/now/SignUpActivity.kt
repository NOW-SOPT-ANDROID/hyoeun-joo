package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.Home.HomeFragment
import com.sopt.now.Home.MyPageFragment
import com.sopt.now.databinding.ActivitySignupBinding
import kotlinx.parcelize.Parcelize


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignup2.setOnClickListener {
            val signid = binding.etIdSignup.text.toString()
            val signpw = binding.etPwSignup.text.toString()
            val signname = binding.etNicknameSignup.text.toString()
            val signmbti = binding.etMbtiSignup.text.toString()

            val userData = UserDataInput(signid, signpw, signname, signmbti)

            when {
                signid.isNullOrEmpty() || signpw.isNullOrEmpty() ||
                        signname.isNullOrEmpty() || signmbti.isNullOrEmpty()
                -> {
                    Toast.makeText(
                        this,
                        getString(R.string.sign_up_fail),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                signid.length !in MIN_ID_LENGTH..MAX_ID_LENGTH -> {
                    showSnackbar(binding.root, getString(R.string.id_error))
                }

                signpw.length !in MIN_PW_LENGTH..MAX_PW_LENGTH -> {
                    showSnackbar(binding.root, getString(R.string.pw_error))
                }

                signname.length < MIN_NICKNAME_LENGTH || signname.isBlank() -> {
                    showSnackbar(binding.root, getString(R.string.nickname_error))
                }

                (signmbti.length > MAX_MBTI_LENGTH) -> {
                    showSnackbar(binding.root, getString(R.string.mbti_error))
                }

                else -> {
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java).apply {
                        putExtra(LoginActivity.INTENT_USER_DATA, userData)


                    }
                    startActivity(intent)

                    Toast.makeText(
                        this,
                        getString(R.string.sign_up_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        const val MIN_ID_LENGTH = 6
        const val MAX_ID_LENGTH = 10
        const val MIN_PW_LENGTH = 8
        const val MAX_PW_LENGTH = 12
        const val MIN_NICKNAME_LENGTH = 1
        const val MAX_MBTI_LENGTH = 4

    }

    fun showSnackbar(view: View, message: String) {
        Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}
