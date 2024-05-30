//package com.sopt.now
//
//import android.content.pm.PackageManager
//import android.location.Location
//import android.os.Bundle
//import android.os.PersistableBundle
//import android.widget.Toast
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
//import com.sopt.now.databinding.ActivityLoginBinding
//import com.sopt.now.databinding.ActivityTestBinding
//
////class TestActivity : AppCompatActivity(){
////    private lateinit var binding: ActivityTestBinding
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        binding = ActivityTestBinding.inflate(layoutInflater)
////        setContentView(binding.root)
////    }
////}
//
//class TestActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityTestBinding
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityTestBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // 위치 제공자 초기화
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//        // 권한 요청 런처 초기화
//        requestPermissionLauncher =
//            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//                if (isGranted) {
//                    // 권한이 허용된 경우 위치 정보 가져오기
//                    getLastKnownLocation()
//                } else {
//                    // 권한이 거부된 경우 처리
//                    showPermissionDeniedMessage()
//                }
//            }
//
//        binding.getLocationButton.setOnClickListener {
//            checkPermissionAndRequestLocation()
//        }
//    }
//
//    private fun checkPermissionAndRequestLocation() {
//        when {
//            ContextCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED -> {
//                // 권한이 이미 허용된 경우
//                getLastKnownLocation()
//            }
//
//            ActivityCompat.shouldShowRequestPermissionRationale(
//                this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) -> {
//                // 권한 요청 이유 설명이 필요한 경우
//                showRationaleDialog()
//            }
//
//            else -> {
//                // 권한 요청
//                requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
//            }
//        }
//    }
//
//    private fun getLastKnownLocation() {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            fusedLocationClient.lastLocation
//                .addOnSuccessListener { location: Location? ->
//                    // 위치 정보를 성공적으로 가져온 경우
//                    if (location != null) {
//                        binding.locationTextView.text =
//                            "위도: ${location.latitude}, 경도: ${location.longitude}"
//                    } else {
//                        binding.locationTextView.text = "위치 정보를 가져올 수 없습니다."
//                    }
//                }
//        }
//    }
//
//    private fun showRationaleDialog() {
//        // 권한 요청 이유를 사용자에게 설명하는 다이얼로그 등을 표시
//        // 예시: AlertDialog를 사용하여 권한 요청 이유 설명
//        AlertDialog.Builder(this)
//            .setTitle("권한 필요")
//            .setMessage("위치 정보를 가져오려면 위치 접근 권한이 필요합니다.")
//            .setPositiveButton("확인") { _, _ ->
//                requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
//            }
//            .setNegativeButton("취소", null)
//            .show()
//    }
//
//    private fun showPermissionDeniedMessage() {
//        // 권한 거부 시 사용자에게 표시할 메시지
//        Toast.makeText(this, "권한이 거부되었습니다. 위치 정보를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
//    }
//}