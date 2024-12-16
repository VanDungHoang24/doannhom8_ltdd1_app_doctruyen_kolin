package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.view.View // Import View để sử dụng View.GONE

class QR : AppCompatActivity() {
    private lateinit var btnQuayLai: Button
    private lateinit var btnThanhToan: Button
    private lateinit var txtPaymentMethod: TextView
    private lateinit var txtVipDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        setControl()
        showDataFromIntent()
        setEvent()
    }

    private fun setControl() {
        btnQuayLai = findViewById(R.id.btnQuayLai)
        btnThanhToan = findViewById(R.id.btnThanhToan)
        txtPaymentMethod = findViewById(R.id.txtPaymentMethod)
        txtVipDetails = findViewById(R.id.txtVipDetails)
    }

    private fun showDataFromIntent() {
        // Nhận dữ liệu từ Intent
        val paymentMethod = intent.getStringExtra("paymentMethod") ?: "Không rõ"
        val vipType = intent.getStringExtra("vipType") ?: ""
        val price = intent.getStringExtra("price") ?: ""

        // Hiển thị dữ liệu phương thức thanh toán
        txtPaymentMethod.text = "Phương thức thanh toán: $paymentMethod"

        // Kiểm tra dữ liệu VIP
        if (vipType.isBlank() && price.isBlank()) {
            // Nếu không có dữ liệu VIP, ẩn TextView
            txtVipDetails.visibility = View.GONE
        } else {
            // Nếu có dữ liệu VIP, hiển thị thông tin
            val vipDetailsText = buildString {
                if (vipType.isNotBlank()) append("Loại VIP: $vipType\n")
                if (price.isNotBlank()) append("Giá: $price")
            }
            txtVipDetails.text = vipDetailsText.trim()
            txtVipDetails.visibility = View.VISIBLE
        }
    }

    private fun setEvent() {
        btnQuayLai.setOnClickListener {
            finish() // Quay lại màn hình trước
        }

        btnThanhToan.setOnClickListener {
            // Chuyển sang màn hình thanh toán thành công
            val intent = Intent(this, ThanhToanThanhCong::class.java)
            startActivity(intent)
        }
    }
}