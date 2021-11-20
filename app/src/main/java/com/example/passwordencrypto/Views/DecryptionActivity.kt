package com.example.passwordencrypto.Views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordencrypto.R
import kotlinx.android.synthetic.main.activity_decryption.*

class DecryptionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decryption)

        btnDecryptData.setOnClickListener {
            val encryptedData = etDecryptData.text.toString()
            val key = etDecryptKey.text.toString().toInt()
            val decryptedData = decryptData(encryptedData, key)
            val intent = Intent(this, EncryptedActivity::class.java)
            intent.putExtra("decrypted", decryptedData)
            startActivity(intent)
        }
    }

    private fun decryptData(str: String, key: Int): String {
        var encryptedStr: String = ""
        for (i in str.indices) {

            if (str[i] in 'a'..'z') {
                encryptedStr += ((str[i].hashCode() - key - 97) % 26 + 97).toChar()
            } else if (str[i] in 'A'..'Z') {
                val upper = ((str[i].hashCode() - key - 65) % 26 + 65).toChar()
                encryptedStr += upper
            } else if (str[i] in '0'..'9') {
                val number = ((str[i].hashCode() - key - 48) % 10 + 48).toChar()
                encryptedStr += number
            } else {
                encryptedStr += str[i]
            }
        }
        return encryptedStr
    }
}