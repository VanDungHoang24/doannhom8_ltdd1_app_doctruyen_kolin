package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ShopTruyen : AppCompatActivity() {
    // Khai báo biến
    private lateinit var btnGioHang : ImageView
    private lateinit var btnThongBao: ImageView
    private lateinit var btnShopVIP: TextView
    private lateinit var btnAddShoppingCart: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_truyen)

        setControl()
        setEvent()
    }

    private fun setControl() {
        btnGioHang = findViewById(R.id.btnGioHang)
        btnThongBao = findViewById(R.id.btnThongBao)
        btnShopVIP = findViewById(R.id.btnShopVIP)
        btnAddShoppingCart = findViewById(R.id.btnAddShoppingCart1)
    }

    private fun setEvent() {
        btnGioHang.setOnClickListener {
            val intent = Intent(this, GioHang::class.java)
            startActivity(intent)
        }
        /*
        btnThongBao.setOnClickListener {
            val intent = Intent(this, ShopVip::class.java)
            startActivity(intent)
        }

         */
        btnShopVIP.setOnClickListener {
            val intent = Intent(this, ShopVip::class.java)
            startActivity(intent)
        }
        btnAddShoppingCart.setOnClickListener {
            val intent = Intent(this, GioHang::class.java)
            startActivity(intent)
        }
    }
}
