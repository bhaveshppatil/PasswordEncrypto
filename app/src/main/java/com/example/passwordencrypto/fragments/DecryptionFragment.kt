package com.example.passwordencrypto.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.passwordencrypto.R
import com.example.passwordencrypto.views.DecryptionActivity
import kotlinx.android.synthetic.main.fragment_decryption.*

class DecryptionFragment : Fragment(R.layout.fragment_decryption) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDecryption.setOnClickListener {
            val intent = Intent(context, DecryptionActivity::class.java)
            startActivity(intent)
        }
    }
}