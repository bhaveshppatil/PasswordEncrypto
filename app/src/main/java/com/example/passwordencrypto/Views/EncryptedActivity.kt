package com.example.passwordencrypto.Views

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordencrypto.R
import kotlinx.android.synthetic.main.activity_encrypted.*

class EncryptedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encrypted)

        var intent = Intent()
        val extras = getIntent().extras
        if (extras?.containsKey("encrypted") == true) {
            val encrypted = extras.getString("encrypted")
            tvEncryptedData.text = encrypted

        } else if (extras?.containsKey("decrypted") == true) {
            val decrypted = extras.getString("decrypted")
            tvEncryptedData.text = decrypted
        }

        btnCopyED.setOnClickListener {
            val manager: ClipboardManager =
                this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            manager.setText(tvEncryptedData.text)
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()

        }

        tvEncryptedData.setOnClickListener {
            val manager: ClipboardManager =
                this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            manager.setText(tvEncryptedData.text)
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()

        }

        btnHomeED.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}