package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GioHang : AppCompatActivity() {
    // Khai báo biến
    private lateinit var ivBack: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnThanhToan: Button
    private lateinit var tvTongTien: TextView

    // Thêm dữ liệu mẫu
    // Add the missing parameters for 'isChecked' and 'soLuong'
    private val truyenList: MutableList<DataTruyen> = mutableListOf(
        DataTruyen("One Piece", "Oda, Eiichiro", "38,000 VND", imageResId = R.drawable.ic_manga_onepiece, soLuong = 1, isChecked = false),
        DataTruyen("Skill Level Up", "Oda, Eiichiro", "48,000 VND", imageResId = R.drawable.onepiece, soLuong = 1, isChecked = false),
        DataTruyen("Ta Có Một Sơn Trại", "Oda, Eiichiro", "30,000 VND", imageResId = R.drawable.tacomotsonrtai, soLuong = 1, isChecked = false),
        DataTruyen("Võ Luyện Đỉnh Phong", "Oda, Eiichiro", "38,000 VND", imageResId = R.drawable.voluyendinhphong, soLuong = 1, isChecked = false),
        DataTruyen("Bại Gia Tử", "Oda, Eiichiro", "38,000 VND", imageResId = R.drawable.ic_manga_baigiatu, soLuong = 1, isChecked = false)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giohang)

        setControl()
        setEvent()
    }

    private fun setControl() {
        ivBack = findViewById(R.id.ivBack)
        recyclerView = findViewById(R.id.recyclerView)
        btnThanhToan = findViewById(R.id.btnThanhToan)
        tvTongTien = findViewById(R.id.tvTongTien)
    }

    private fun setEvent() {
        ivBack.setOnClickListener {
            finish()
        }

        // Cài đặt RecyclerView
        val adapter = GioHangAdapter(truyenList) { tongTien ->
            tvTongTien.text = "Tổng Thanh Toán: ${tongTien} VND"
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Xử lý khi nhấn nút Thanh Toán
        btnThanhToan.setOnClickListener {
            val intent = Intent(this, PhuongThucThanhToan::class.java)

            // Truyền dữ liệu các truyện đã chọn
            val selectedTruyen = truyenList.filter { it.isChecked }
            selectedTruyen.forEachIndexed { index, truyen ->
                intent.putExtra("tentruyen$index", truyen.tenTruyen)
                intent.putExtra("tacgia$index", truyen.tacGia)
                intent.putExtra("gia$index", truyen.gia)
                intent.putExtra("soluong$index", truyen.soLuong)
                intent.putExtra("imageResId$index", truyen.imageResId) // Truyền hình ảnh
            }
            startActivity(intent)
        }
    }
}