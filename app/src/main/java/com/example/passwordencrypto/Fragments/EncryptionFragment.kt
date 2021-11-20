package com.example.passwordencrypto.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.passwordencrypto.R
import com.example.passwordencrypto.Views.CipherTextEncryption
import com.example.passwordencrypto.Views.RailFenceEncryption
import kotlinx.android.synthetic.main.encryption_password.*

class EncryptionFragment : Fragment(R.layout.encryption_password) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCipherText.setOnClickListener {
            val intent = Intent(context, CipherTextEncryption::class.java)
            startActivity(intent)
        }

        btnRailFence.setOnClickListener {
            val intent = Intent(context, RailFenceEncryption::class.java)
            startActivity(intent)
        }
    }
}