package com.tdc.nhom8.appdoctruyentranhonline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {

    // Khai báo biến
    private lateinit var tvQuayLai: TextView
    private lateinit var btnXacThuc: Button
    private lateinit var edtGmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_thong)

        setControl()
        setEvent()
    }

    private fun setControl() {
        tvQuayLai = findViewById(R.id.tvQuayLai)
        btnXacThuc = findViewById(R.id.btnXacThuc)
        edtGmail = findViewById(R.id.edtGmail)
    }

    private fun setEvent() {
        tvQuayLai.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnXacThuc.setOnClickListener {
            val email = edtGmail.text.toString().trim()

            // khởi tạo lớp lưu trữ getsharepre
            val share = getSharedPreferences("user",Context.MODE_PRIVATE)
            val savepassword = share.getString(email,null)

            if(savepassword != null)
            {
                val editor = share.edit()
                editor.remove(email)
                editor.apply()

                val intent = Intent(this,NewPasswordActivity::class.java)
                intent.putExtra("email",email)
                startActivity(intent)
            }
            else
            {
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("Lỗi")
                    .setMessage("email Không tồn tại ")
                    .setPositiveButton("OK"){dialog,_->dialog.dismiss()}
                    .create()
                alertDialog.show()
            }
        }
    }
}