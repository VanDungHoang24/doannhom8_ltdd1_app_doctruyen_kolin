package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.tdc.nhom8.appdoctruyentranhonline.databinding.FragmentFrColectionBinding

class FrColection : Fragment() {

    private lateinit var binding: FragmentFrColectionBinding
    private lateinit var cartIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Bạn có thể xử lý các tham số ở đây nếu cần
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Sử dụng View Binding để liên kết giao diện
        binding = FragmentFrColectionBinding.inflate(inflater, container, false)

        // Trả về root view từ binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bây giờ bạn có thể sử dụng binding một cách an toàn
        setControl()
        setEvent()
    }

    private fun setControl() {
        cartIcon = binding.cartIconColection
    }

    private fun setEvent() {
        cartIcon.setOnClickListener {
            val intent = Intent(requireActivity(), ShopTruyen::class.java)
            startActivity(intent)
        }
    }
}
