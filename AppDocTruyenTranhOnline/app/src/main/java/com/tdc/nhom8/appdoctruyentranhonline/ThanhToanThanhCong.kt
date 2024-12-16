package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ThanhToanThanhCong : AppCompatActivity() {
    // Khai báo biến
    private lateinit var btnTiepTuc : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thanhtoanthanhcong)

        setControl()
        setEvent()
    }

    private fun setControl(){
        btnTiepTuc = findViewById(R.id.btnTiepTuc)
    }

    private fun setEvent() {
        btnTiepTuc.setOnClickListener {
            val intent = Intent(this, ShopVip::class.java)
            startActivity(intent)
        }
    }
}