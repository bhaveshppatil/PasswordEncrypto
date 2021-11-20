package com.example.passwordencrypto.Views

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordencrypto.R
import kotlinx.android.synthetic.main.activity_decryption.*


class DecryptionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decryption)

        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        val cipher = findViewById<RadioButton>(R.id.checkboxCipher)
        val railFence = findViewById<RadioButton>(R.id.checkboxRailFence)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: Int = radioGroup.checkedRadioButtonId

            if (cipher.id == radioButton) {
                Toast.makeText(
                    this,
                    "Cipher Encryption Selected",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (railFence.id == radioButton) {
                val data = "No need to select key"
                etDecryptKey.setText(data)
                etDecryptKey.isClickable = false
                Toast.makeText(
                    this,
                    "Rail Fence Encryption Selected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnDecryptData.setOnClickListener {
            if (cipher.isChecked) {
                val encryptedData = etDecryptData.text.toString()
                val key = etDecryptKey.text.toString().toInt()
                val decryptedData = decryptData(encryptedData, key)
                val intent = Intent(this, EncryptedActivity::class.java)
                intent.putExtra("decrypted", decryptedData)
                startActivity(intent)
            } else if (railFence.isChecked) {
                val encryptedData = etDecryptData.text.toString()
                val decryptedData = decryptRailFenceData(encryptedData)
                val intent = Intent(this, EncryptedActivity::class.java)
                intent.putExtra("RailFenceDecrypted", decryptedData)
                startActivity(intent)
            }
        }
    }

    private fun decryptRailFenceData(encryptedData: String): String {
        var str = ""
        val k: Int
        var j: Int

        if (encryptedData.length % 2 == 0) {
            j = encryptedData.length / 2
        } else {
            j = encryptedData.length / 2 + 1
        }
        k = j
        var i = 0
        while (i < k) {
            str += encryptedData[i]
            str += encryptedData[j]
            i++
            j++
        }
        return str
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