package com.tdc.nhom8.appdoctruyentranhonline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GioHangAdapter(
    private val truyenList: MutableList<DataTruyen>,  // Dùng MutableList thay vì List để có thể thay đổi trạng thái của các phần tử
    private val onTotalAmountChanged: (totalAmount: String) -> Unit
) : RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GioHangViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_giohang, parent, false)
        return GioHangViewHolder(view)
    }

    override fun onBindViewHolder(holder: GioHangViewHolder, position: Int) {
        val truyen = truyenList[position]
        holder.bind(truyen)
    }

    override fun getItemCount(): Int = truyenList.size

    inner class GioHangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cbSelected: CheckBox = itemView.findViewById(R.id.cbChon)
        private val tvTenTruyen: TextView = itemView.findViewById(R.id.tvTenTruyen)
        private val tvGia: TextView = itemView.findViewById(R.id.tvGia)

        fun bind(truyen: DataTruyen) {
            tvTenTruyen.text = truyen.tenTruyen
            tvGia.text = truyen.gia
            cbSelected.isChecked = truyen.isChecked

            cbSelected.setOnCheckedChangeListener { _, isChecked ->
                truyen.isChecked = isChecked
                updateTotalAmount()
            }
        }

        private fun updateTotalAmount() {
            var totalAmount = 0
            truyenList.filter { it.isChecked }.forEach { truyen ->
                try {
                    // Loại bỏ các ký tự không phải số trước khi chuyển sang Int
                    val price = truyen.gia.replace(" VND", "").replace(",", "").toInt()
                    totalAmount += price
                } catch (e: NumberFormatException) {
                    // Bỏ qua nếu không thể chuyển giá trị sang Int
                    e.printStackTrace()
                }
            }
            // Cập nhật tổng tiền
            onTotalAmountChanged("Tổng Thanh Toán: $totalAmount VND")
        }
    }
}
