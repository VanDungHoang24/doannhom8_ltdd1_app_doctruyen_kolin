package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MangaCRUDAdpaterTien(
    private val context: Context,
    private var comics: ArrayList<DataMangaAdminCRUD>,
    private val onEdit: (DataMangaAdminCRUD) -> Unit,
    private val onDelete: (DataMangaAdminCRUD) -> Unit
) : BaseAdapter() {

    fun updateData(newComics: ArrayList<DataMangaAdminCRUD>) {
        comics = newComics
        notifyDataSetChanged()
    }

    override fun getCount(): Int = comics.size

    override fun getItem(position: Int): DataMangaAdminCRUD = comics[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_manga_admin_tien, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val comic = comics[position]
        holder.tvComicID.text = "ID Truyện: ${comic.id}"
        holder.tvComicName.text = "Tên truyện: ${comic.name}"
        holder.tvComicCategory.text = "Thể loại: ${comic.category}"
        holder.tvComicStatus.text = "Trạng thái: ${comic.status}"

        // Sửa hàm xóa: Hiển thị hộp thoại xác nhận trước khi xóa
        holder.btnDelete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa truyện '${comic.name}' không?")
                .setPositiveButton("Yes") { _, _ ->
                    onDelete(comic) // Gọi hàm xóa sau khi xác nhận
                    notifyDataSetChanged()
                }
                .setNegativeButton("No", null)
                .show()
        }

        // Hàm sửa
        holder.btnEdit.setOnClickListener {
            onEdit(comic)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val tvComicID: TextView = view.findViewById(R.id.tvComicID)
        val tvComicName: TextView = view.findViewById(R.id.tvComicName)
        val tvComicCategory: TextView = view.findViewById(R.id.tvComicCategory)
        val tvComicStatus: TextView = view.findViewById(R.id.tvComicStatus)
        val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }
}
