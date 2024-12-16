package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var IMG_hienthi: ImageView
    private lateinit var IMG_hienthi2: ImageView
    private lateinit var edtemail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var  edtComfirmPassword:EditText
    private lateinit var tvQuayLai: TextView
    private lateinit var tvDangNhap: TextView
    private lateinit var btnDangKy: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_thong)

        setControl()
        setEvent()
    }

    private fun setControl() {
        IMG_hienthi = findViewById(R.id.IMG_hienthi)
        IMG_hienthi2 = findViewById(R.id.IMG_hienthi2)
        edtemail = findViewById(R.id.edtGmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtComfirmPassword = findViewById(R.id.edtComfirmPassword)
        tvQuayLai = findViewById(R.id.tvQuayLai)
        tvDangNhap = findViewById(R.id.tvDangNhap)
        btnDangKy = findViewById(R.id.btnDangKy)
    }

    private fun setEvent() {
        tvQuayLai.setOnClickListener {
            finish()
        }
        btnDangKy.setOnClickListener {
            taomatkhau()
        }
        tvDangNhap.setOnClickListener{
            finish()
        }
        IMG_hienthi.setOnClickListener{
            if(edtPassword.inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD))
            {
                edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                IMG_hienthi.setImageResource(R.drawable.close_password)
            }
            else
            {
                edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                IMG_hienthi.setImageResource(R.drawable.open_password)
            }
            edtPassword.setSelection(edtPassword.text.length)
        }

        IMG_hienthi2.setOnClickListener{
            if(edtComfirmPassword.inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD))
            {
                edtComfirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                IMG_hienthi2.setImageResource(R.drawable.close_password)
            }
            else
            {
                edtComfirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                IMG_hienthi2.setImageResource(R.drawable.open_password)
            }
            edtComfirmPassword.setSelection(edtComfirmPassword.text.length)
        }
    }
    private fun taomatkhau()
    {
        val email = edtemail.text.toString().trim();
        val password = edtPassword.text.toString().trim();
        val comfirmPassword = edtComfirmPassword.text.toString().trim()

        if(email.isEmpty() || password.isEmpty()|| comfirmPassword.isEmpty())
        {
            Toast.makeText(this," Vui Lòng điền đầy đủ thông tin!",Toast.LENGTH_SHORT).show()
            return
        }
        if(!email.contains("@"))
        {
            Toast.makeText(this,"vui lòng nhập đúng định dạng Email ",Toast.LENGTH_SHORT).show()
            return
        }
        if(password.length< 8)
        {
            Toast.makeText(this,"Mật khẩu phải trên 8 kí tự ",Toast.LENGTH_SHORT).show()
            return
        }
        if(password != comfirmPassword)
        {
            Toast.makeText(this,"Mật khẩu không khớp!", Toast.LENGTH_SHORT).show()
            return
        }

        // tạo sharedpre với tên user
        val share = getSharedPreferences("user",Context.MODE_PRIVATE)
        val editor = share.edit();
        val kiemtra = share.getString(email,null)
        if(kiemtra!= null)
        {
            Toast.makeText(this,"Email đã được đăng kí!",Toast.LENGTH_SHORT).show()
            return
        }
        // lưu thông tin email và password vàoo sharedpre
        editor.putString(email,password)
        editor.apply()
        dialoghienthi()
    }

    private fun dialoghienthi() {
       val dialog = AlertDialog.Builder(this)
        dialog.setTitle(" Đăng kí thành công")
        dialog.setMessage(" Tài khoản của bạn đã được tạo thành công !")
        dialog.setPositiveButton(" Đăng nhập "){_,_->
            val intent = Intent (this,LoginActivity::class.java)
            intent.putExtra("email",edtemail.text.toString())
            startActivity(intent)
            finish()
        }
        dialog.setNegativeButton("Đóng"){dialoghienthi,_->
            dialoghienthi.dismiss()
        }
        dialog.create().show()
    }
}