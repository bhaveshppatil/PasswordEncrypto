package com.example.passwordencrypto.Views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordencrypto.R
import kotlinx.android.synthetic.main.activity_rail_fence.*

class RailFenceEncryption : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rail_fence)

        btnRailFenceData.setOnClickListener {
            val str: String = etRailFenceData.text.toString()
            val data = encryptData(str)

            val intent = Intent(this, EncryptedActivity::class.java)
            intent.putExtra("RailFenceEncrypted", data)
            startActivity(intent)
        }
    }

    private fun encryptData(str: String): String {
        val len: Int = str.length
        var start = ""
        var end = ""
        var temp = ""
        for (i in 0 until len) {
            if (str[i] != ' ') {
                temp += str[i]
            }
        }
        for (i in temp.indices) {
            if (i % 2 == 0) {
                start += temp[i]
            } else {
                end += temp[i]
            }
        }
        return start + end
    }
}