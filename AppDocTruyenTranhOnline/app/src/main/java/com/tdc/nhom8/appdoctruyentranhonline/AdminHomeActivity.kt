package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.tdc.nhom8.appdoctruyentranhonline.R

class AdminHomeActivity : AppCompatActivity() {

    // Khai báo biến
    private lateinit var btnQuanLyTruyen : Button
    private lateinit var btnQuanLyDoanhThu : Button
    private lateinit var btnQuanLyShop : Button
    private lateinit var btnQuanLyTaiKhoan : Button
    private lateinit var btnDangXuat : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_home)

        setControl()
        setEvent()
    }

    private fun setControl(){
        btnQuanLyTruyen = findViewById(R.id.btnQuanLyTruyen)
        btnQuanLyDoanhThu = findViewById(R.id.btnQuanLyDoanhThu)
        btnQuanLyShop = findViewById(R.id.btnQuanLyShop)
        btnQuanLyTaiKhoan = findViewById(R.id.btnQuanLyTaiKhoan)
        btnDangXuat = findViewById(R.id.btnDangXuat)
    }

    private fun setEvent(){
//        btnQuanLyTruyen.setOnClickListener{
//            val intent = Intent(this, AdminMangaActivity::class.java)
//            startActivity(intent)
//        }
        //chuyen qua trang quan ly doanh thu
        btnQuanLyDoanhThu.setOnClickListener{
            val intent = Intent(this, QuanLyDoanhThuActivity::class.java)
            startActivity(intent)
        }

        btnDangXuat.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}