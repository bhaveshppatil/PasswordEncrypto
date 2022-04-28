package com.example.passwordencrypto.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordencrypto.R
import kotlinx.android.synthetic.main.activity_cipher_text_encryption.*

class CipherTextEncryption : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cipher_text_encryption)

        btnEncryptData.setOnClickListener {
            val str: String = etEncryptData.text.toString()
            val key: Int = etEncryptKey.text.toString().toInt()
            val data = encryptData(str, key)
            val intent = Intent(this, EncryptedActivity::class.java)
            intent.putExtra("encrypted", data)
            intent.putExtra("key", key)
            startActivity(intent)
        }

        tvShowList.setOnClickListener {
            val intent = Intent(this, SavedDataActivity::class.java)
            startActivity(intent)
        }
    }

    private fun encryptData(str: String, key: Int): String {
        var encryptedStr = StringBuilder()
        for (i in str.indices) {
            if (str[i] in 'a'..'z') {
//                encryptedStr += ((str[i].hashCode() + key - 97) % 26 + 97).toChar()
                encryptedStr.append((str[i].hashCode() + key - 97) + 97)
            } else if (str[i] in 'A'..'Z') {
                encryptedStr.append((str[i].hashCode() + key - 65) + 65)
            } else if (str[i] in '0'..'9') {
                encryptedStr.append((str[i].hashCode() + key - 48) + 48)
            } else {
                encryptedStr.append(str[i])
            }
        }
        return encryptedStr.toString()
    }
}