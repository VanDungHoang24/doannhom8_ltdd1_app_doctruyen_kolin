package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tdc.nhom8.appdoctruyentranhonline.databinding.FragmentFrHomeBinding

class FrHome : Fragment() {
    private lateinit var userAvatar: ImageView
    private lateinit var cartIcon: ImageView
    private lateinit var tvWelcome: TextView
    private lateinit var listView: ListView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnTuan: Button
    private lateinit var btnThang: Button
    private lateinit var btnNam: Button
    private lateinit var binding: FragmentFrHomeBinding
    private var comicList: List<Comic> = listOf()
    private var comicListTopTuan: List<Comic> = listOf()
    private var comicListTopThang: List<Comic> = listOf()
    private var comicListTopNam: List<Comic> = listOf()

    private var userAvatarID: Int = 0
    private lateinit var user1: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFrHomeBinding.inflate(inflater, container, false)

        // Khởi tạo các view trong Fragment
        setControl()

        // Thiết lập sự kiện
        setEvent()

        return binding.root
    }

    private fun setControl() {
        recyclerView = binding.recyclerView
        listView = binding.lvDanhSach
        userAvatar = binding.userAvatar
        cartIcon = binding.cartIcon
        tvWelcome = binding.tvWelcome
        btnTuan = binding.btnTuan
        btnThang = binding.btnThang
        btnNam = binding.btnNam

        comicList = listOf(
            Comic(
                "Item01", "Detective Conan Movie 27",
                Author("Au001", "Honda", "Lorem ipsum", "aau1"), 99.9, "cover_image_cm1", ""
            ),
            Comic(
                "Item02", "Daemon",
                Author("Au002", "Yamaha", "Placeat consectetur", "aau2"), 88.8, "cover_image_cm2", ""
            ),
            Comic(
                "Item03", "Thỏ 7 màu",
                Author("Au003", "Nguyễn Nguyên Ngọc", "Amet consectetur", "aau3"), 77.7, "tho7mau", ""
            ),
            Comic(
                "Item04", "One Piece",
                Author("Au004", "Kataminano", "Consectetur doloribus", "aau4"), 66.6, "cover_image_cm4", ""
            )
        )

        comicListTopTuan = listOf(
            Comic(
                "Item09", "Tokyo Ghoul",
                Author("Au009", "Ishida", "A ghoul's life and survival in Tokyo", "aau9"), 98.4, "cover_image_cm9", ""
            ),
            Comic(
                "Item10", "One Punch Man",
                Author("Au010", "Murata", "A hero who defeats anyone with a single punch", "aau10"), 112.3, "cover_image_cm10", ""
            )
        )

        // Khởi tạo adapter cho RecyclerView
        val recyclerViewAdapter = ComicRecyclerViewAdapter(requireContext(), comicList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun setEvent() {
        // Cập nhật avatar và tên người dùng
        if (userAvatarID != 0 && user1.name.isNotEmpty()) {
            userAvatar.setImageResource(userAvatarID)
            tvWelcome.text = user1.name
        }

        // Thiết lập mặc định ListView
        val listViewAdapter = ComicAdapter(requireContext(), comicListTopTuan)
        listView.adapter = listViewAdapter
        handleButtonClick(btnTuan)

        // Xử lý sự kiện click các button
        btnTuan.setOnClickListener {
            handleButtonClick(btnTuan)
            val listViewAdapter = ComicAdapter(requireContext(), comicListTopTuan)
            listView.adapter = listViewAdapter
        }
        btnThang.setOnClickListener {
            handleButtonClick(btnThang)
            val listViewAdapter = ComicAdapter(requireContext(), comicListTopThang)
            listView.adapter = listViewAdapter
        }
        btnNam.setOnClickListener {
            handleButtonClick(btnNam)
            val listViewAdapter = ComicAdapter(requireContext(), comicListTopNam)
            listView.adapter = listViewAdapter
        }

        // Xử lý sự kiện click icon giỏ hàng
        cartIcon.setOnClickListener {
            val intent = Intent(requireActivity(), ShopTruyen::class.java)
            startActivity(intent)
        }
    }

    private fun handleButtonClick(clickedButton: Button) {
        // Reset tất cả các button về màu mặc định
        btnTuan.setBackgroundResource(R.drawable.button_selector)
        btnTuan.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

        btnThang.setBackgroundResource(R.drawable.button_selector)
        btnThang.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

        btnNam.setBackgroundResource(R.drawable.button_selector)
        btnNam.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

        // Thay đổi màu của button được click
        clickedButton.setBackgroundResource(R.drawable.radius_vip)
        clickedButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun onComicClick(comic: Comic) {
        val intent = Intent(requireActivity(), MangaDetail::class.java).apply {
            putExtra("comic_title", comic.name)
            putExtra("comic_description", comic.description)
            putExtra("comic_image", comic.coverImage) // Truyền URL hoặc ID tài nguyên của ảnh
        }
        startActivity(intent)
    }
}
