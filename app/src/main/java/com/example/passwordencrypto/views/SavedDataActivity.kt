package com.example.passwordencrypto.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passwordencrypto.R
import com.example.passwordencrypto.db.CipherEncryptoModel
import com.example.passwordencrypto.db.DAOEncrypto
import com.example.passwordencrypto.db.EncryptoDatabase
import com.example.passwordencrypto.repo.EncryptoRepo
import com.example.passwordencrypto.viewModel.EncryptoViewModel
import com.example.passwordencrypto.viewModel.EncryptoViewModelFactory
import com.example.passwordencrypto.views.adapter.CipherAdapter
import com.example.passwordencrypto.views.adapter.ItemClickListener
import kotlinx.android.synthetic.main.activity_saved_data.*

class SavedDataActivity : AppCompatActivity(), ItemClickListener {
    private val dataList = mutableListOf<CipherEncryptoModel>()
    private lateinit var cipherAdapter: CipherAdapter
    private lateinit var encryptoDatabase: EncryptoDatabase
    private lateinit var daoEncrypto: DAOEncrypto
    private lateinit var encryptoViewModel: EncryptoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_data)
        init()
        setUpRecyclerView()
        encryptoViewModel.getDataFromDB().observe(this, Observer {
            dataList.clear()
            dataList.addAll(it)
            updateUI(dataList)
            cipherAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpRecyclerView() {
        cipherAdapter = CipherAdapter(this, dataList, this)
        rcSavedDataList.adapter = cipherAdapter
        rcSavedDataList.layoutManager = LinearLayoutManager(this)
    }

    private fun updateUI(cipherDataList : List<CipherEncryptoModel>) {

        if (cipherDataList.isEmpty()) {
            rcSavedDataList.visibility = View.GONE
            tvNoDataFound.visibility = View.VISIBLE
        } else {
            tvNoDataFound.visibility = View.GONE
            rcSavedDataList.visibility = View.VISIBLE
        }
    }

    private fun init() {
        daoEncrypto = EncryptoDatabase.getDatabaseObject(this).getEncryptedData()
        val encryptoRepo = EncryptoRepo(daoEncrypto)
        val encryptoViewModelFactory = EncryptoViewModelFactory(encryptoRepo)
        encryptoViewModel =
            ViewModelProviders.of(this, encryptoViewModelFactory).get(EncryptoViewModel::class.java)
    }

    override fun onCipherCardClick(cipherEncryptoModel: CipherEncryptoModel) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("detail", cipherEncryptoModel)
        startActivity(intent)
        showToast("Item clicked")
    }

    private fun showToast(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }
}