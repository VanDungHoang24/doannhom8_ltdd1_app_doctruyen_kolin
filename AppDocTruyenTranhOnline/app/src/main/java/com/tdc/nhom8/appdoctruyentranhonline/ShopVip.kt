package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ShopVip : AppCompatActivity() {
    // Khai báo biến
    private lateinit var btnGioHang : ImageView
    private lateinit var btnThongBao: ImageView
    private  lateinit var btnShopTruyen : TextView
    private lateinit var btnVipTuan : Button
    private lateinit var btnVipThang : Button
    private lateinit var btnVipNam : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_vip)

        setControl()
        setEvent()

    }

    private fun setControl(){


        btnGioHang = findViewById(R.id.btnGioHang)
        btnThongBao = findViewById(R.id.btnThongBao)
        btnShopTruyen = findViewById(R.id.btnShopTruyen)
        btnVipTuan = findViewById(R.id.btnVipTuan)
        btnVipThang = findViewById(R.id.btnVipThang)
        btnVipNam = findViewById(R.id.btnVipNam)
    }

    private fun setEvent() {
        btnGioHang.setOnClickListener {
            val intent = Intent(this, GioHang::class.java)
            startActivity(intent)
        }

        btnShopTruyen.setOnClickListener {
            val intent = Intent(this, ShopTruyen::class.java)
            startActivity(intent)
        }

        // Truyền dữ liệu khi nhấn vào các nút thanh toán VIP
        btnVipTuan.setOnClickListener {
            val intent = Intent(this, ThanhToanVIP::class.java)
            // Truyền loại VIP và giá tiền
            intent.putExtra("vipType", "VIP Tuần")
            intent.putExtra("price", "10000VND")
            startActivity(intent)
        }

        btnVipThang.setOnClickListener {
            val intent = Intent(this, ThanhToanVIP::class.java)
            intent.putExtra("vipType", "VIP Tháng")
            intent.putExtra("price", "30000VND")
            startActivity(intent)
        }

        btnVipNam.setOnClickListener {
            val intent = Intent(this, ThanhToanVIP::class.java)
            intent.putExtra("vipType", "VIP Năm")
            intent.putExtra("price", "120000VND")
            startActivity(intent)
        }
    }

}