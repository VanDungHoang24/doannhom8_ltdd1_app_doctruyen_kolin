package com.tdc.nhom8.appdoctruyentranhonline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoanhThuAdapter(private val revenueList: List<DoanhThuItem>) :
    RecyclerView.Adapter<DoanhThuAdapter.DoanhThuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoanhThuViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_doanhthu, parent, false)
        return DoanhThuViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoanhThuViewHolder, position: Int) {
        val item = revenueList[position]
        holder.tvTenTruyen.text = item.title
        holder.tvGia.text = "${item.amount} VNĐ"
    }

    override fun getItemCount(): Int {
        return revenueList.size
    }

    class DoanhThuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTenTruyen: TextView = view.findViewById(R.id.tvTenTruyen)
        val tvGia: TextView = view.findViewById(R.id.tvGia)
    }
}