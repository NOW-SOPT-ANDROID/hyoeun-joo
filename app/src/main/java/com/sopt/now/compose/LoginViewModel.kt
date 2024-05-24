import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.MainActivity
import com.sopt.now.compose.api.ServicePool
import com.sopt.now.compose.dto.RequestLogInDto
import com.sopt.now.compose.dto.ResponseLogInDto
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    var loginId = mutableStateOf("")
    var loginPw = mutableStateOf("")

    private val authService = ServicePool.authService

    fun getLogInRequestDto(): RequestLogInDto {
        return RequestLogInDto(
            authenticationId = loginId.value,
            password = loginPw.value
        )
    }

    fun logIn(context: Context) {
        val loginRequest = getLogInRequestDto()
        authService.logIn(loginRequest).enqueue(object : Callback<ResponseLogInDto> {
            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    Toast.makeText(
                        context,
                        "로그인 성공",
                        Toast.LENGTH_SHORT,
                    ).show()

                    val intent = Intent(context, MainActivity::class.java).apply {
                        putExtra("userId", userId)
                    }
                    context.startActivity(intent)
                } else {
                    val error = response.message()
                    Toast.makeText(
                        context,
                        "로그인 실패 $error",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {
                Toast.makeText(context, "서버 에러 발생 ", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
