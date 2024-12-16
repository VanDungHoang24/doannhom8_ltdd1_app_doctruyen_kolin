package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MangaDetail : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var coverImageView: ImageView
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manga_detail)

        setControl()
        setEvent()
    }

    private fun setControl() {
        titleTextView = findViewById(R.id.titleTextView)
        descriptionTextView = findViewById(R.id.txt_mota)
        coverImageView = findViewById(R.id.imageView)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun setEvent() {
        val comicTitle = intent.getStringExtra("comic_title")
        val comicDescription = intent.getStringExtra("comic_description")
        val comicImageName = intent.getStringExtra("comic_image") // Lấy string name img

        //xự kiện quay lại
        btnBack.setOnClickListener{
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        }





        // Cập nhật tiêu đề và mô tả
        titleTextView.text = comicTitle ?: "Tên truyện không xác định"
        descriptionTextView.text = comicDescription ?: "Không có mô tả."

        // Đặt ảnh từ tên hình ảnh nếu có
        if (!comicImageName.isNullOrEmpty()) {
            val drawableId = getDrawableId(this, comicImageName)
            if (drawableId != 0) {
                coverImageView.setImageResource(drawableId) // Đặt ảnh từ tài nguyên
            } else {
                coverImageView.setImageResource(R.drawable.default_image) // Ảnh mặc định nếu không tìm thấy
            }
        } else {
            coverImageView.setImageResource(R.drawable.default_image) // Ảnh mặc định nếu không có tên ảnh
        }
    }

    private fun getDrawableId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}
