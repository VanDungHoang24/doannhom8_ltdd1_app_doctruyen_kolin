package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var IMG_hienthi:ImageView
    private lateinit var btnDangNhap: Button
    private lateinit var tvDangKy: TextView
    private lateinit var edtGmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var tvQuenMK: TextView
    private lateinit var rdoUser: RadioButton
    private lateinit var rdoAdmin: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_thong)

        setControl()
        setEvent()
    }

    private fun setControl() {
        IMG_hienthi = findViewById(R.id.IMG_hienthi)
        btnDangNhap = findViewById(R.id.btnDangNhap)
        tvDangKy = findViewById(R.id.tvDangKy)
        edtGmail = findViewById(R.id.edtGmail)
        edtPassword = findViewById(R.id.edtPassword)
        tvQuenMK = findViewById(R.id.tvQuenMK)
        rdoUser = findViewById(R.id.rdoUser)
        rdoAdmin = findViewById(R.id.rdoAdmin)

    }

    private fun setEvent() {
        btnDangNhap.setOnClickListener {
            val inUsername = edtGmail.text.toString().trim()
            val inPassword = edtPassword.text.toString().trim()

            // Check for Admin credentials
            if (rdoAdmin.isChecked) {
                if (inUsername == "admin@gmail.com" && inPassword == "admin123") {
                    // Navigate to Admin activity
                    val intent = Intent(this, AdminHomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this,
                        "Sai tên đăng nhập hoặc mật khẩu Admin",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
//                val share = getSharedPreferences("user",Context.MODE_PRIVATE)
//                val savepassword = share.getString(inUsername,null)
//                if(savepassword == inPassword)
//                {
                    val intent = Intent(this,Home::class.java)
                    startActivity(intent)
//                }
//                else
//                {
//                    Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu người dùng", Toast.LENGTH_SHORT).show()
//                }
            }
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
        tvDangKy.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        tvQuenMK.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}