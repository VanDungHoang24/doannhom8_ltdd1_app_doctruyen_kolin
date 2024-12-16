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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tdc.nhom8.appdoctruyentranhonline.ComicAdapter
import com.tdc.nhom8.appdoctruyentranhonline.ComicRecyclerViewAdapter
import com.tdc.nhom8.appdoctruyentranhonline.Comic
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
    private   var comicList: List<Comic> = listOf()
    private   var comicListTopTuan: List<Comic> = listOf()
    private   var comicListTopThang: List<Comic> = listOf()
    private   var comicListTopNam: List<Comic> = listOf()


    private var userAvatarID: Int = 0
    private lateinit var user1: User


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Sử dụng View Binding để liên kết giao diện
        binding = FragmentFrHomeBinding.inflate(inflater, container, false)

        // Khởi tạo các view trong Fragment
        setControl()

        // Thiết lập sự kiện
        setEvent()

        return binding.root

    }

    private fun setControl() {
//        recyclerView = binding.recyclerView
        recyclerView = binding.recyclerView
        listView = binding.lvDanhSach
        userAvatar = binding.userAvatar
        cartIcon = binding.cartIcon
        tvWelcome = binding.tvWelcome
        btnTuan = binding.btnTuan
        btnThang = binding.btnThang
        btnNam = binding.btnNam


            comicList = listOf(
                Comic("Item01", "Detective Conan Movie 27", Author("Au001", "Honda", "Lorem ipsum", "aau1"), 99.9, "cover_image_cm1", ""),
                Comic("Item02", "Daemon", Author("Au002", "Yamaha", "Placeat consectetur", "aau2"), 88.8, "cover_image_cm2", ""),
                Comic("Item03", "Thỏ 7 màu", Author("Au003", "Nguyễn Nguyên Ngọc", "Amet consectetur", "aau3"), 77.7, "tho7mau", ""),
                Comic("Item04", "One Piece", Author("Au004", "Kataminano", "Consectetur doloribus", "aau4"), 66.6, "cover_image_cm4", "")
            )

        comicListTopTuan = listOf(
            Comic("Item09", "Tokyo Ghoul", Author("Au009", "Ishida", "A ghoul's life and survival in Tokyo", "aau9"), 98.4, "cover_image_cm9", ""),
            Comic("Item10", "One Punch Man", Author("Au010", "Murata", "A hero who defeats anyone with a single punch", "aau10"), 112.3, "cover_image_cm10", ""),
            Comic("Item16", "Hunter x Hunter", Author("Au016", "Togashi", "A young boy's journey to find his father", "aau16"), 140.3, "cover_image_cm16", ""),
            Comic("Item11", "Demon Slayer", Author("Au011", "Koyoharu", "The fight against demons to save loved ones", "aau11"), 130.0, "cover_image_cm11", "")
        )

       comicListTopThang = listOf(
            Comic("Item13", "Fullmetal Alchemist", Author("Au013", "Arakawa", "Two brothers seek the Philosopher's Stone", "aau13"), 120.8, "cover_image_cm13", ""),
            Comic("Item14", "Death Note", Author("Au014", "Ohba", "A high school student discovers a deadly notebook", "aau14"), 110.2, "cover_image_cm14", ""),
            Comic("Item15", "Sword Art Online", Author("Au015", "Kawahara", "Trapped in a virtual world, players must survive", "aau15"), 85.4, "cover_image_cm15", ""),
           Comic("Item12", "Fairy Tail", Author("Au012", "Mashima", "A guild of mages embarking on epic adventures", "aau12"), 75.0, "cover_image_cm12", "")

        )


        comicListTopNam = listOf(
            Comic("Item17", "JoJo's Bizarre Adventure", Author("Au017", "Araki", "A supernatural adventure across generations", "aau17"), 145.5, "cover_image_cm17", ""),
            Comic("Item18", "Attack on Titan", Author("Au018", "Isayama", "The war against titans continues", "aau18"), 155.6, "cover_image_cm18", ""),
            Comic("Item19", "One Piece", Author("Au019", "Oda", "The journey for the ultimate treasure", "aau19"), 200.1, "cover_image_cm19", ""),
            Comic("Item20", "Dragon Ball Z", Author("Au020", "Toriyama", "The ultimate fight to save the universe", "aau20"), 210.0, "cover_image_cm20", "")
        )


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

    // Thiết lập defaul ListView
        val listViewAdapter = ComicAdapter(requireContext(), comicListTopTuan)
        listView.adapter = listViewAdapter
        handleButtonClick(btnTuan)

        // Button click event handling
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


    }

    private fun handleButtonClick(clickedButton: Button) {
        // Reset all buttons to default color
        btnTuan.setBackgroundResource(R.drawable.button_selector)
        btnTuan.setTextColor(getResources().getColor(R.color.black))

        btnThang.setBackgroundResource(R.drawable.button_selector)
        btnThang.setTextColor(getResources().getColor(R.color.black));

        btnNam.setBackgroundResource(R.drawable.button_selector)
        btnNam.setTextColor(getResources().getColor(R.color.black));

        // Change the color of the clicked button
        clickedButton.setBackgroundResource(R.drawable.radius_vip)
        clickedButton.setTextColor(getResources().getColor(R.color.white))
    }


//        // Sự kiện click vào icon giỏ hàng
//        cartIcon.setOnClickListener {
//            val intent = Intent(requireActivity(), giohang::class.java)
//            startActivity(intent)
//        }


    private fun onComicClick(comic: Comic) {
        val intent = Intent(requireActivity(), MangaDetail::class.java).apply {
            putExtra("comic_title", comic.name)
            putExtra("comic_description", comic.description)
            putExtra("comic_image", comic.coverImage) // Truyền URL hoặc ID tài nguyên của ảnh
        }
        startActivity(intent)
    }

    private fun getDrawableId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }



}