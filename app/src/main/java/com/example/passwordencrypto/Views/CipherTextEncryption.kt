package com.example.passwordencrypto.Views

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordencrypto.R
import kotlinx.android.synthetic.main.activity_cipher_text_encryption.*
import kotlinx.android.synthetic.main.fragment_password.*

class CipherTextEncryption : AppCompatActivity() {

    private var progres: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cipher_text_encryption)

        val seekBar = findViewById<SeekBar>(R.id.seekbarLevel)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progresValue: Int, fromUser: Boolean) {
                progres = progresValue
                tvSeekMax.text = "${progres.toString()}"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        btnEncryptData.setOnClickListener {
            val str: String = etEncryptData.text.toString()
            val data = encryptData(str, progres)
            val intent = Intent(this, EncryptedActivity::class.java)
            intent.putExtra("encrypted", data)
            startActivity(intent)
        }

    }

    private fun encryptData(str: String, key: Int): String {
        var encryptedStr: String = ""
        for (i in str.indices) {

            if (str[i] in 'a'..'z') {
                encryptedStr += ((str[i].hashCode() + key - 97) % 26 + 97).toChar()
            } else if (str[i] in 'A'..'Z') {
                val upper = ((str[i].hashCode() + key - 65) % 26 + 65).toChar()
                encryptedStr += upper
            } else if (str[i] in '0'..'9') {
                val pos: Int = Char(str[i].toInt()).toInt()
                val number = ((str[i].hashCode() + key - 48) % 10 + 48).toChar()
                encryptedStr += number
            } else {
                encryptedStr += str[i]
            }
        }
        return encryptedStr
    }
}