package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.CheckBox

class PhuongThucThanhToan : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var btnTiepTheo: Button
    private lateinit var txtDanhSachTruyen: TextView
    private lateinit var phuongThucThanhToanCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phuongthuocthanhtoan)

        setControl()
        showDataFromIntent() // Hiển thị dữ liệu
        setEvent()
    }

    private fun setControl() {
        btnBack = findViewById(R.id.btnBack)
        btnTiepTheo = findViewById(R.id.btnTiepTheo)
        txtDanhSachTruyen = findViewById(R.id.txtDanhSachTruyen)
        phuongThucThanhToanCheckBox = findViewById(R.id.phuongthuthanhtoan)
    }

    private fun showDataFromIntent() {
        // Nhận dữ liệu từ Intent và hiển thị
        val sb = StringBuilder()

        var index = 0
        while (true) {
            val tenTruyen = intent.getStringExtra("tentruyen$index") ?: break
            val tacGia = intent.getStringExtra("tacgia$index") ?: break
            val gia = intent.getStringExtra("gia$index") ?: break
            val soLuong = intent.getIntExtra("soluong$index", 0)

            sb.append("Tên truyện: $tenTruyen\n")
            sb.append("Tác giả: $tacGia\n")
            sb.append("Giá: $gia\n")
            sb.append("Số lượng: $soLuong\n\n")

            index++
        }

        // Hiển thị danh sách truyện trong TextView
        txtDanhSachTruyen.text = sb.toString()
    }

    private fun setEvent() {
        btnBack.setOnClickListener {
            finish() // Quay lại màn hình trước
        }

        btnTiepTheo.setOnClickListener {
            // Kiểm tra trạng thái của CheckBox
            val paymentMethod = if (phuongThucThanhToanCheckBox.isChecked) "VNPT" else "MOMO"

            // Tạo intent và truyền dữ liệu qua màn hình QR
            val intent = Intent(this, QR::class.java)

            // Truyền thông tin về truyện
            var index = 0
            while (true) {
                val tenTruyen = intent.getStringExtra("tentruyen$index") ?: break
                val tacGia = intent.getStringExtra("tacgia$index") ?: break
                val gia = intent.getStringExtra("gia$index") ?: break
                val soLuong = intent.getIntExtra("soluong$index", 0)

                intent.putExtra("tentruyen$index", tenTruyen)
                intent.putExtra("tacgia$index", tacGia)
                intent.putExtra("gia$index", gia)
                intent.putExtra("soluong$index", soLuong)

                index++
            }

            // Truyền thông tin về phương thức thanh toán
            intent.putExtra("paymentMethod", paymentMethod)

            // Bắt đầu màn hình QR
            startActivity(intent)
        }
    }
}