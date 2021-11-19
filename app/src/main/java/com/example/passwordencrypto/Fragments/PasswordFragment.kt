package com.example.passwordencrypto.Fragments

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import com.example.passwordencrypto.R
import kotlinx.android.synthetic.main.fragment_password.*
import java.util.*
import android.widget.Toast





class PasswordFragment : Fragment(R.layout.fragment_password) {
    private lateinit var checkboxSymbols: CheckBox
    private lateinit var checkboxUppercase: CheckBox
    private lateinit var checkboxLowercase: CheckBox
    private lateinit var checkboxNumbers: CheckBox
    private lateinit var checkboxSavePasswd: CheckBox
    private var progress: Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seekBar = view.findViewById(R.id.seekbar) as SeekBar

        checkboxSymbols = view.findViewById(R.id.checkboxSymbols)
        checkboxUppercase = view.findViewById(R.id.checkboxUppercase)
        checkboxLowercase = view.findViewById(R.id.checkboxLowercase)
        checkboxNumbers = view.findViewById(R.id.checkboxNumbers)
        checkboxSavePasswd = view.findViewById(R.id.checkboxSavePasswd)

        var currentValue = seekBar.progress

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progresValue: Int, fromUser: Boolean) {
                progress = progresValue
                tvSeekMax.text = "${progress.toString()}"

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        tvGeneratedPasswd.setOnClickListener(View.OnClickListener {
            val cm: ClipboardManager = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setText(tvGeneratedPasswd.text)
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
        })

        btnGenerate.setOnClickListener {
            tvGeneratedPasswd.text = generateRandomPassword()
        }
    }

    private fun generateRandomPassword(): String {
        val upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val lowerCaseChars = "abcdefghijklmnopqrstuvwxyz"
        val numberChars = "0123456789"
        val specialChars = "!@#$%^&*()_-+=<>?/{}~|"
        var allowedChars = ""
        val rn = Random()
        val sb = StringBuilder(progress)

        if (checkboxUppercase.isChecked) {
            allowedChars += upperCaseChars
            sb.append(upperCaseChars[rn.nextInt(upperCaseChars.length - 1)])
        }
        if (checkboxLowercase.isChecked) {
            allowedChars += lowerCaseChars
            sb.append(lowerCaseChars[rn.nextInt(lowerCaseChars.length - 1)])
        }
        if (checkboxNumbers.isChecked) {
            allowedChars += numberChars
            sb.append(numberChars[rn.nextInt(numberChars.length - 1)])
        }
        if (checkboxSymbols.isChecked) {
            allowedChars += specialChars
            sb.append(specialChars[rn.nextInt(specialChars.length - 1)])
        }

        for (i in sb.length until progress) {
            sb.append(allowedChars[rn.nextInt(allowedChars.length)])
        }
        return sb.toString()
    }
}