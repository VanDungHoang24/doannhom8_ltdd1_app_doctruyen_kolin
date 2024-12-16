package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class NewPasswordActivity : AppCompatActivity() {
    private lateinit var IMG_hienthi: ImageView
    private lateinit var IMG_hienthi2:ImageView
    private lateinit var edtNewPassword:EditText
    private lateinit var edtPassword:EditText

    private lateinit var btnXacThuc: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_password_thong)

        setControl()
        setEvent()
    }

    private fun setControl() {
        IMG_hienthi = findViewById(R.id.img_ShowPassword)
        IMG_hienthi2 = findViewById(R.id.img_ShowPassword2)
        edtNewPassword = findViewById(R.id.edtNewPassword)
        edtPassword = findViewById(R.id.edtComfirmPassword)

        btnXacThuc = findViewById(R.id.btnXacThuc)
    }

    private fun setEvent() {
        btnXacThuc.setOnClickListener {
            taomatkhau();
        }
        IMG_hienthi.setOnClickListener{
            if(edtNewPassword.inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD))
            {
                edtNewPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                IMG_hienthi.setImageResource(R.drawable.close_password)
            }
            else
            {
                edtNewPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                IMG_hienthi.setImageResource(R.drawable.open_password)
            }
            edtNewPassword.setSelection(edtNewPassword.text.length)
        }
        IMG_hienthi2.setOnClickListener{
            if(edtPassword.inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD))
            {
                edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                IMG_hienthi2.setImageResource(R.drawable.close_password)
            }
            else
            {
                edtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                IMG_hienthi2.setImageResource(R.drawable.open_password)
            }
            edtPassword.setSelection(edtPassword.text.length)
        }
    }
    private fun taomatkhau()
    {

        val password = edtNewPassword.text.toString().trim();
        val comfirmPassword = edtPassword.text.toString().trim();

        if(password.isEmpty()|| comfirmPassword.isEmpty())
        {
            Toast.makeText(this," Vui Lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
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
        val email = intent.getStringExtra("email")
        if (email.isNullOrEmpty()) {
            Toast.makeText(this, "Email không hợp lệ.", Toast.LENGTH_SHORT).show()
            return
        }

        val share = getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = share.edit();

        val kiemtra = share.getString(email,null)
        if(kiemtra!= null)
        {
            Toast.makeText(this,"Mật khẩu đã được thay đổi", Toast.LENGTH_SHORT).show()
            return
        }
        editor.putString(email,password)
        editor.apply()
        dialoghienthi()
    }
    private fun dialoghienthi() {
        val mkmoi = AlertDialog.Builder(this)
        mkmoi.setTitle(" Mật Khẩu mới")
        mkmoi.setMessage(" Tạo mật khẩu mới thành công.")

        mkmoi.setPositiveButton(" Đăng Nhập") { dialog, _ ->
            val inter = Intent(this, LoginActivity::class.java)
            startActivity(inter)
            dialog.dismiss()
        }
        mkmoi.setNegativeButton("Hủy") { dialog, _ ->
            dialog.dismiss()
        }
        mkmoi.create().show()
    }
}