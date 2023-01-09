package com.example.passwordencrypto.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordencrypto.R
import com.example.passwordencrypto.db.CipherEncryptoModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var intent = intent
        val extras = getIntent().extras

        val cipherModel = extras?.getSerializable("detail") as CipherEncryptoModel

        tvEncText.text = cipherModel.data

        tvEncKey.text = "Key ${cipherModel.key}"
    }
}