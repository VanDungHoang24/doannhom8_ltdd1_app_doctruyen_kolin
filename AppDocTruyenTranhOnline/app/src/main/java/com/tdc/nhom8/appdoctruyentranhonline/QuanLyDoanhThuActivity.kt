package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter

class QuanLyDoanhThuActivity : AppCompatActivity() {
    // Khai báo biến
    private lateinit var ivBack: ImageView
    private lateinit var tvTongDoanhThu: TextView
    private lateinit var spLoaiBieuDo: Spinner
    private lateinit var spChonThang: Spinner
    private lateinit var bieuDoCot: BarChart
    private lateinit var bieuDoTron: PieChart
    private lateinit var rvList: RecyclerView
    private lateinit var btnXuatBaoCao: Button

    // Dữ liệu mẫu
    private val itemDoanhThuList = listOf(
        DoanhThuItem("Truyện A", 100000, month = "Tháng 1"),
        DoanhThuItem("Truyện B", 150000, month = "Tháng 1"),
        DoanhThuItem("Truyện C", 175000, month = "Tháng 1"),
        DoanhThuItem("Truyện D", 99000, month = "Tháng 1"),
        DoanhThuItem("Truyện E", 36000, month = "Tháng 1"),
        DoanhThuItem("Truyện F", 32000, month = "Tháng 1"),
        DoanhThuItem("Truyện C", 200000, month = "Tháng 2"),
        DoanhThuItem("Truyện D", 250000, month = "Tháng 2"),
        DoanhThuItem("Truyện E", 120000, month = "Tháng 3"),
        DoanhThuItem("Truyện G", 890000, month = "Tháng 3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quan_ly_doanh_thu)

        setControl()
        setEvent()

        // Mặc định hiển thị tháng 1
        spChonThang.setSelection(0)  // Thiết lập mặc định là "Tháng 1"
        val filteredData = itemDoanhThuList.filter { it.month == "Tháng 1" }
        setupRecyclerView(filteredData)
        TongDoanhThu(filteredData)
        CapNhatBieuDo(filteredData)
    }

    private fun setControl() {
        ivBack = findViewById(R.id.ivBack)
        tvTongDoanhThu = findViewById(R.id.tvTongDoanhThu)
        spLoaiBieuDo = findViewById(R.id.spLoaiBieuDo)
        spChonThang = findViewById(R.id.spChonThang)
        bieuDoCot = findViewById(R.id.bieuDoCot)
        bieuDoTron = findViewById(R.id.bieuDoTron)
        rvList = findViewById(R.id.rvList)
        btnXuatBaoCao = findViewById(R.id.btnXuatBaoCao)
    }

    private fun setEvent() {
        ivBack.setOnClickListener {
            finish()
        }

        btnXuatBaoCao.setOnClickListener {
            // Tạo báo cáo dưới dạng chuỗi
            val reportData = DuLieuBaoCao()

            // Chuyển sang màn hình báo cáo
            val intent = Intent(this, BaoCaoActivity::class.java).apply {
                putExtra("REPORT_DATA", reportData)
            }
            startActivity(intent)

            // Hiển thị thông báo Toast
            Toast.makeText(this, "Báo cáo xuất thành công!", Toast.LENGTH_SHORT).show()
        }

        // Chọn loại biểu đồ
        val DanhSachBieuDo = arrayOf("Biểu đồ cột", "Biểu đồ tròn")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, DanhSachBieuDo)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spLoaiBieuDo.adapter = adapter

        spLoaiBieuDo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        bieuDoCot.visibility = View.VISIBLE
                        bieuDoTron.visibility = View.GONE
                    }

                    1 -> {
                        bieuDoCot.visibility = View.GONE
                        bieuDoTron.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Chọn tháng
        val DanhSachThang =
            arrayOf("Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4") // Danh sách các tháng
        val adapterThang = ArrayAdapter(this, android.R.layout.simple_spinner_item, DanhSachThang)
        adapterThang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spChonThang.adapter = adapterThang

        spChonThang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedMonth = spChonThang.selectedItem.toString()
                val filteredData = itemDoanhThuList.filter { it.month == selectedMonth }

                // Cập nhật doanh thu theo tháng
                setupRecyclerView(filteredData)
                TongDoanhThu(filteredData)

                // Cập nhật biểu đồ cột và tròn
                CapNhatBieuDo(filteredData)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun DuLieuBaoCao(): String {
        // Lấy tháng đã chọn từ Spinner
        val selectedMonth = spChonThang.selectedItem.toString()

        val tongDoanhThu = itemDoanhThuList.sumOf { it.amount }

        val duLieuDoanhThuTheoThang = itemDoanhThuList.filter { it.month == selectedMonth }

        val tongDoanhThuCuaThang = duLieuDoanhThuTheoThang.sumOf { it.amount }

        // Tính phần trăm chiếm của doanh thu tháng đã chọn trên tổng doanh thu
        val percentage = (tongDoanhThuCuaThang.toFloat() / tongDoanhThu) * 100

        // Tạo chuỗi báo cáo
        return "Tổng doanh thu: $tongDoanhThu VNĐ\n\n" +
                "Danh sách doanh thu theo $selectedMonth:\n" +
                itemDoanhThuList.filter { it.month == selectedMonth }
                    .joinToString("\n") { item ->
                        "${item.title}: ${item.amount} VNĐ"
                    } + "\n\n" +
                "Doanh thu $selectedMonth chiếm ${"%.2f".format(percentage)}% tổng doanh thu."
    }

    // Hiển thị danh sách các item doanh thu
    private fun setupRecyclerView(filteredData: List<DoanhThuItem>) {
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = DoanhThuAdapter(filteredData)
    }

    // Hàm tính tổng doanh thu
    private fun TongDoanhThu(filteredData: List<DoanhThuItem>) {
        val tongDoanhThu = itemDoanhThuList.sumOf { it.amount }
        val tongDoanhThuThang = filteredData.sumOf { it.amount }
        val selectedMonth = spChonThang.selectedItem.toString()

        tvTongDoanhThu.text = "Tổng doanh thu: $tongDoanhThu VNĐ \n" +
                "Doanh thu $selectedMonth: $tongDoanhThuThang VNĐ"
    }

    // Hàm cập nhật biểu đồ khi chọn xem theo tháng
    private fun CapNhatBieuDo(filteredData: List<DoanhThuItem>) {
        val totalAmount = filteredData.sumOf { it.amount }
        filteredData.forEach { item ->
            item.percentage = (item.amount.toFloat() / totalAmount) * 100
        }

        // Biểu đồ cột
        val entries = filteredData.mapIndexed { index, item ->
            BarEntry(index.toFloat(), item.amount.toFloat())
        }
        val dataSet = BarDataSet(entries, "Doanh thu").apply {
            colors = listOf(
                resources.getColor(R.color.teal_200, null),
                resources.getColor(R.color.purple_200, null),
                resources.getColor(R.color.teal_700, null),
                resources.getColor(R.color.purple_500, null) // Thêm màu nếu cần
            )
            valueTextSize = 12f
            valueFormatter = object : ValueFormatter() {
                override fun getBarLabel(barEntry: BarEntry?): String {
                    val index = barEntry?.x?.toInt() ?: 0
                    return if (index < filteredData.size) {
                        "${filteredData[index].percentage.toInt()}%"
                    } else ""
                }
            }
        }
        bieuDoCot.data = BarData(dataSet)
        bieuDoCot.invalidate()
        bieuDoCot.description.isEnabled = false // Tắt mô tả

        // Biểu đồ tròn
        val pieEntries = filteredData.map { PieEntry(it.amount.toFloat(), it.title) }
        val pieDataSet = PieDataSet(pieEntries, "Doanh thu").apply {
            colors = listOf(
                resources.getColor(R.color.teal_200, null),
                resources.getColor(R.color.purple_200, null),
                resources.getColor(R.color.teal_700, null),
                resources.getColor(R.color.purple_500, null) // Thêm màu nếu cần
            )
            valueTextSize = 12f
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return String.format("%.1f%%", value)
                }
            }
        }
        bieuDoTron.data = PieData(pieDataSet)
        bieuDoTron.setUsePercentValues(true)
        bieuDoTron.invalidate()
        bieuDoTron.description.isEnabled = false // Tắt mô tả
    }
}