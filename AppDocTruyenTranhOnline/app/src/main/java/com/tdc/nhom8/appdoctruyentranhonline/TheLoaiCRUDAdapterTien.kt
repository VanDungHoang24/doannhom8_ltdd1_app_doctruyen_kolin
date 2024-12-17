package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class TheLoaiCRUDAdapterTien(
    private val context: Context,
    private var theLoai: ArrayList<DataTheLoaiAdminCRUD>,
    private val onEdit: (DataTheLoaiAdminCRUD) -> Unit,
    private val onDelete: (DataTheLoaiAdminCRUD) -> Unit
) : BaseAdapter() {

    // Cập nhật lại dữ liệu thể loại
    fun updateData(newGenres: ArrayList<DataTheLoaiAdminCRUD>) {
        theLoai = newGenres
        notifyDataSetChanged()
    }

    override fun getCount(): Int = theLoai.size

    override fun getItem(position: Int): DataTheLoaiAdminCRUD = theLoai[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            // Inflate view nếu chưa có view cũ
            view = LayoutInflater.from(context).inflate(R.layout.item_theloai_admin_tien, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val genre = theLoai[position]
        holder.tvTheLoaiID.text = "ID: ${genre.id}"
        holder.tvTenTheLoai.text = "${genre.name}"

        // Sửa: Hiển thị hộp thoại xác nhận trước khi xóa thể loại
        holder.btnDelete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa thể loại '${genre.name}' không?")
                .setPositiveButton("Yes") { _, _ ->
                    onDelete(genre) // Gọi hàm xóa sau khi xác nhận
                    notifyDataSetChanged()
                }
                .setNegativeButton("No", null)
                .show()
        }

        // Sửa: Hàm chỉnh sửa thể loại
        holder.btnEdit.setOnClickListener {
            onEdit(genre)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val tvTheLoaiID: TextView = view.findViewById(R.id.tvTheLoaiID)
        val tvTenTheLoai: TextView = view.findViewById(R.id.tvTenTheLoai)
        val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }
}
