package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MangaGridAdapter(private val context: Context, private val mangaList: List<Comic>) : BaseAdapter() {

    override fun getCount(): Int = mangaList.size

    override fun getItem(position: Int): Comic = mangaList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        val comic = mangaList[position]

        val textView: TextView = view.findViewById(R.id.grid_item_text)
        textView.text = comic.name

        val imageView: ImageView = view.findViewById(R.id.grid_item_image)
        val imageId = getDrawableId(context, comic.coverImage)
        if (imageId != 0) {
            imageView.setImageResource(imageId) // Set image resource from drawable
        } else {
            imageView.setImageResource(R.drawable.default_image) // Default image
        }

        return view
    }

    // Hàm lấy ID tài nguyên ảnh
    private fun getDrawableId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}
