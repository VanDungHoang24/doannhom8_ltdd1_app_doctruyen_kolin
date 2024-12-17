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

class AdminMangaMangaActivity : AppCompatActivity() {


    // Khai báo biến
    private lateinit var ivBack: ImageView
    private lateinit var lvDanhSachTruyen: ListView
    private lateinit var etSearch: EditText
    private lateinit var btnAdd: ImageButton
    private lateinit var adapter: MangaCRUDAdpaterTien
    private val mangas = ArrayList<DataMangaAdminCRUD>()

    private fun loadData() {
        mangas.apply {
            add(DataMangaAdminCRUD(1, "Hot Search Dự Định", "Manhwa", "Đang phát hành"))
            add(DataMangaAdminCRUD(2, "Thám tử Conan", "Trinh thám", "Đã hoàn thành"))
            add(DataMangaAdminCRUD(3, "One Piece", "Phiêu lưu", "Đang phát hành"))
            add(DataMangaAdminCRUD(4, "Naruto", "Hành động", "Đã hoàn thành"))
            add(DataMangaAdminCRUD(5, "Attack on Titan", "Giả tưởng", "Đã hoàn thành"))
        }
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_manga_manga)

        setControl()
        setEvent()
        loadData()
    }

    private fun setControl() {
        ivBack = findViewById(R.id.ivBack)
        lvDanhSachTruyen = findViewById(R.id.lvDanhSachTruyen)
        etSearch = findViewById(R.id.etSearch)
        btnAdd = findViewById(R.id.btnAdd)
    }

    private fun setEvent() {
        ivBack.setOnClickListener {
            finish()
        }

        adapter = MangaCRUDAdpaterTien(this, mangas, ::editComic, ::deleteComic)
        lvDanhSachTruyen.adapter = adapter

        btnAdd.setOnClickListener { showDialog() }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().toLowerCase()
                val filtered = mangas.filter { it.name.toLowerCase().contains(query) }
                adapter.updateData(ArrayList(filtered))
            }
        })
    }

    private fun showDialog(manga: DataMangaAdminCRUD? = null) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_crud, null)
        val etName = dialogView.findViewById<EditText>(R.id.etTitle)
        val etCategory = dialogView.findViewById<EditText>(R.id.etDescription)

        // Nếu sửa, đổ dữ liệu cũ vào dialog
        manga?.let {
            etName.setText(it.name)
            etCategory.setText(it.category)
        }

        // Tạo Dialog
        AlertDialog.Builder(this)
            .setTitle(if (manga == null) "Thêm Truyện" else "Sửa Truyện")
            .setView(dialogView)
            .setPositiveButton("Lưu") { _, _ ->
                val name = etName.text.toString()
                val category = etCategory.text.toString()

                if (name.isNotBlank() && category.isNotBlank()) {
                    if (manga == null) {
                        mangas.add(
                            DataMangaAdminCRUD(
                                id = mangas.size + 1,
                                name = name,
                                category = category,
                                status = "Đang phát hành"
                            )
                        )
                    } else {
                        manga.name = name
                        manga.category = category
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    private fun editComic(manga: DataMangaAdminCRUD) {
        showDialog(manga)
    }

    private fun deleteComic(manga: DataMangaAdminCRUD) {
        mangas.remove(manga)
        adapter.updateData(ArrayList(mangas))
    }
}
