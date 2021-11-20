package com.example.passwordencrypto.Views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordencrypto.R
import kotlinx.android.synthetic.main.activity_encrypted.*

class EncryptedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encrypted)

        var intent = Intent()
        intent = getIntent();
        val encrypted = intent.getStringExtra("encrypted")

        tvEncryptedData.text = encrypted
    }
}