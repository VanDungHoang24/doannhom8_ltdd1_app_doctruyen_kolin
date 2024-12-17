package com.tdc.nhom8.appdoctruyentranhonline

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class AdminMangaTheLoaiActivity : AppCompatActivity() {

    // Khai báo biến
    private lateinit var ivBack: ImageView
    private lateinit var etSearch: EditText
    private lateinit var lvDanhSachTheLoai: ListView
    private lateinit var btnAdd: ImageButton
    private lateinit var adapter: TheLoaiCRUDAdapterTien
    private val theLoaiList = ArrayList<DataTheLoaiAdminCRUD>()
    private var currentId = 1 // Biến quản lý ID cho thể loại mới

    private fun loadData() {
        theLoaiList.apply {
            add(DataTheLoaiAdminCRUD(currentId++, "Manhwa"))
            add(DataTheLoaiAdminCRUD(currentId++, "Trinh thám"))
            add(DataTheLoaiAdminCRUD(currentId++, "Phiêu lưu"))
            add(DataTheLoaiAdminCRUD(currentId++, "Hành động"))
            add(DataTheLoaiAdminCRUD(currentId++, "Giả tưởng"))
            add(DataTheLoaiAdminCRUD(currentId++, "Manhua"))
        }
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_manga_the_loai)

        setControl()
        setEvent()
        loadData()
    }

    private fun setControl() {
        ivBack = findViewById(R.id.ivBack)
        etSearch = findViewById(R.id.etSearch)
        lvDanhSachTheLoai = findViewById(R.id.lvDanhSachTheLoai)
        btnAdd = findViewById(R.id.btnAdd)
    }

    private fun setEvent() {
        ivBack.setOnClickListener {
            finish()
        }

        // Initialize adapter
        adapter = TheLoaiCRUDAdapterTien(this, theLoaiList, ::editTheLoai, ::deleteGenre)
        lvDanhSachTheLoai.adapter = adapter

        // Thêm thể loại mới
        btnAdd.setOnClickListener {
            showAddGenreDialog()
        }

        // Tìm kiếm thể loại
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().toLowerCase()
                val filtered = theLoaiList.filter { it.name.toLowerCase().contains(query) }
                adapter.updateData(ArrayList(filtered))
            }
        })
    }

    // Hàm hiển thị dialog để thêm hoặc chỉnh sửa thể loại
    private fun showGenreDialog(isEdit: Boolean, genre: DataTheLoaiAdminCRUD? = null) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_theloai, null)
        val editText = dialogView.findViewById<EditText>(R.id.etTheLoaiMoi)

        // Nếu là chỉnh sửa thể loại, set giá trị ban đầu
        if (isEdit && genre != null) {
            editText.setText(genre.name)
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle(if (isEdit) "Chỉnh sửa thể loại" else "Thêm thể loại mới")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                val genreName = editText.text.toString()
                if (genreName.isNotEmpty()) {
                    if (isEdit && genre != null) {
                        // Cập nhật thể loại nếu là chỉnh sửa
                        genre.name = genreName
                        adapter.notifyDataSetChanged() // Cập nhật giao diện
                    } else {
                        // Thêm thể loại mới nếu là thêm
                        val newGenre = DataTheLoaiAdminCRUD(currentId++, genreName)
                        theLoaiList.add(newGenre)
                        adapter.notifyDataSetChanged() // Cập nhật giao diện
                    }
                }
            }
            .setNegativeButton("Hủy", null)
            .create()

        dialog.show()
    }

    // Hàm gọi để thêm thể loại
    private fun showAddGenreDialog() {
        showGenreDialog(isEdit = false)
    }

    // Hàm gọi để chỉnh sửa thể loại
    private fun showEditGenreDialog(genre: DataTheLoaiAdminCRUD) {
        showGenreDialog(isEdit = true, genre = genre)
    }

    // Hàm xử lý chỉnh sửa thể loại
    private fun editTheLoai(genre: DataTheLoaiAdminCRUD) {
        showEditGenreDialog(genre)
    }

    // Hàm xử lý xóa thể loại
    private fun deleteGenre(genre: DataTheLoaiAdminCRUD) {
        theLoaiList.remove(genre)
        adapter.notifyDataSetChanged()
    }
}
