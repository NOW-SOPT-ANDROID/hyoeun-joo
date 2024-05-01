package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.User.UserDataInput
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        fun showSnacker(view: View, message: String) {
            Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_SHORT
            ).show()
        }
        setContentView(binding.root)
        val userData = intent.getParcelableExtra<UserDataInput>(INTENT_USER_DATA)

        val signId = userData?.getUserSignUpId()
        val signPw = userData?.getUserSignUpPw()

        binding.btnLogin.setOnClickListener {
            val editid = binding.etId.text.toString()
            val editpw = binding.pw2.text.toString()
            if (editid == signId && editpw == signPw) {
                showSnacker(binding.root, getString(R.string.log_in_success))


                val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                    putExtra(MainActivity.INTENT_USER_DATA, userData)

                }
                Log.d("IntentData", "User data: $userData")
                startActivity(intent)
                finish()
            } else {
                showSnacker(binding.root, getString(R.string.log_in_fail))
            }
        }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val INTENT_USER_DATA = "userData"
    }
}

