package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tdc.nhom8.appdoctruyentranhonline.MangaDetail
import com.tdc.nhom8.appdoctruyentranhonline.Object.*
import com.tdc.nhom8.appdoctruyentranhonline.R

class ComicAdapter(
    private val context: Context,
    private val comicList: List<Comic>
) : ArrayAdapter<Comic>(context, R.layout.item_comic, comicList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_comic, parent, false)
        val comic = comicList[position]

        val comicTitle: TextView = view.findViewById(R.id.tvTitileItem)
        val comicImage: ImageView = view.findViewById(R.id.imgItem)

        // Set tiêu đề
        comicTitle.text = comic.name

        // Kiểm tra và đặt ảnh
        val imageId = getDrawableId(context, comic.cover_image)
        if (imageId != 0) {
            comicImage.setImageResource(imageId) // Sử dụng ảnh từ tài nguyên
        } else {
            comicImage.setImageResource(R.drawable.default_image) // Sử dụng ảnh mặc định
        }

        // Sự kiện click vào item
        view.setOnClickListener {
            val intent = Intent(context, MangaDetail::class.java).apply {
                putExtra("comic_title", comic.name)
                putExtra("comic_description", comic.description)
                putExtra("comic_image", comic.cover_image)
            }
            context.startActivity(intent)
        }

        return view
    }

    // Hàm lấy ID tài nguyên ảnh
    private fun getDrawableId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}
