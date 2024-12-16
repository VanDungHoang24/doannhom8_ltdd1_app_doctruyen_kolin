package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.CheckBox
import android.widget.Toast  // Import Toast


class ThanhToanVIP : AppCompatActivity() {
    private lateinit var btnQuayLai: Button
    private lateinit var btnThanhToanVIP: Button
    private lateinit var vipTypeTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var vnpayCheckBox: CheckBox
    private lateinit var momoCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanhtoanmuavip)

        setControl()
        setEvent()

        // Nhận dữ liệu từ Intent
        val vipType = intent.getStringExtra("vipType") ?: "Chưa chọn loại VIP"
        val price = intent.getStringExtra("price") ?: "0 VND"

        // Hiển thị dữ liệu
        vipTypeTextView.text = vipType
        priceTextView.text = price
    }

    private fun setControl() {
        btnQuayLai = findViewById(R.id.btnQuayLai)
        btnThanhToanVIP = findViewById(R.id.btnThanhToanVIP)
        vipTypeTextView = findViewById(R.id.vipTypeTextView)
        priceTextView = findViewById(R.id.priceTextView)
        vnpayCheckBox = findViewById(R.id.vnpay)
        momoCheckBox = findViewById(R.id.momo)
    }

    private fun setEvent() {
        btnQuayLai.setOnClickListener {
            // Quay lại màn hình ShopVip
            val intent = Intent(this, ShopVip::class.java)
            startActivity(intent)
        }

        btnThanhToanVIP.setOnClickListener {
            val selectedPaymentMethod = when {
                vnpayCheckBox.isChecked && !momoCheckBox.isChecked -> "VNPAY"
                momoCheckBox.isChecked && !vnpayCheckBox.isChecked -> "MOMO"
                else -> null // Xử lý khi không hoặc chọn nhiều phương thức thanh toán
            }

            if (selectedPaymentMethod == null) {
                Toast.makeText(this, "Vui lòng chọn một phương thức thanh toán", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Chuyển dữ liệu qua màn hình QR
            val intent = Intent(this, QR::class.java)
            intent.putExtra("paymentMethod", selectedPaymentMethod)
            intent.putExtra("vipType", vipTypeTextView.text.toString())
            intent.putExtra("price", priceTextView.text.toString())

            startActivity(intent)
        }
    }
}