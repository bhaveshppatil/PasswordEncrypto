package com.example.passwordencrypto.views

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.passwordencrypto.R
import com.example.passwordencrypto.db.CipherEncryptoModel
import com.example.passwordencrypto.db.DAOEncrypto
import com.example.passwordencrypto.db.EncryptoDatabase
import com.example.passwordencrypto.repo.EncryptoRepo
import com.example.passwordencrypto.viewModel.EncryptoViewModel
import com.example.passwordencrypto.viewModel.EncryptoViewModelFactory
import kotlinx.android.synthetic.main.activity_encrypted.*

class EncryptedActivity : AppCompatActivity() {

    private lateinit var encryptoDatabase: EncryptoDatabase
    private lateinit var daoEncrypto: DAOEncrypto
    private lateinit var encryptoViewModel: EncryptoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encrypted)

        daoEncrypto = this.let { EncryptoDatabase.getDatabaseObject(this).getEncryptedData() }
        val encryptoRepo = EncryptoRepo(daoEncrypto)
        val encryptoViewModelFactory = EncryptoViewModelFactory(encryptoRepo)
        encryptoViewModel = ViewModelProviders.of(this, encryptoViewModelFactory).get(EncryptoViewModel::class.java)

        var intent = Intent()
        val extras = getIntent().extras
        val key = extras?.getInt("key")
        tvKey.text = "Key Used $key"

        if (extras?.containsKey("encrypted") == true) {
            val encrypted = extras.getString("encrypted")
            tvEncryptedData.text = encrypted
        } else if (extras?.containsKey("decrypted") == true) {
            val decrypted = extras.getString("decrypted")
            tvEncryptedData.text = decrypted
        } else if (extras?.containsKey("RailFenceEncrypted") == true) {
            val railFenceEncrypted = extras.getString("RailFenceEncrypted")
            tvEncryptedData.text = railFenceEncrypted
        } else if (extras?.containsKey("RailFenceDecrypted") == true) {
            val railFenceDecrypted = extras.getString("RailFenceDecrypted")
            tvEncryptedData.text = railFenceDecrypted
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

        btnSaveED.setOnClickListener {
            val encryptionTech = getIntent().extras
            val data = tvEncryptedData.text.toString()
            val cipherEncryptoModel = CipherEncryptoModel(data, key!!, "encryptionTech" )
            encryptoViewModel.addEncryptData(cipherEncryptoModel)
        }
    }
}