package com.tdc.nhom8.appdoctruyentranhonline

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BaoCaoActivity : AppCompatActivity() {

    private lateinit var tvBaoCao: TextView
    private lateinit var btnQuayLai: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bao_cao)

        setControl()
        setEvent()
    }

    private fun setControl() {
        tvBaoCao = findViewById(R.id.tvBaoCao)
        btnQuayLai = findViewById(R.id.btnQuayLai)
    }

    private fun setEvent() {
        // Nhận dữ liệu từ Intent
        val reportData = intent.getStringExtra("REPORT_DATA")
        tvBaoCao.text = reportData

        btnQuayLai.setOnClickListener {
            finish()
        }
    }
}