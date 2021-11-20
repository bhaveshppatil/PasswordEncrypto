package com.example.passwordencrypto.Fragments

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.passwordencrypto.R
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import kotlinx.android.synthetic.main.fragment_password.*
import java.util.*


class PasswordFragment : Fragment(R.layout.fragment_password) {

    private lateinit var checkboxSymbols: CheckBox
    private lateinit var checkboxUppercase: CheckBox
    private lateinit var checkboxLowercase: CheckBox
    private lateinit var checkboxNumbers: CheckBox
    private lateinit var checkboxSavePasswd: CheckBox
    private lateinit var fam: FloatingActionMenu
    private lateinit var fabSavedList: FloatingActionButton
    private lateinit var fabCopy: FloatingActionButton

    private var progress: Int = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seekBar = view.findViewById(R.id.seekbar) as SeekBar

        checkboxSymbols = view.findViewById(R.id.checkboxSymbols)
        checkboxUppercase = view.findViewById(R.id.checkboxUppercase)
        checkboxLowercase = view.findViewById(R.id.checkboxLowercase)
        checkboxNumbers = view.findViewById(R.id.checkboxNumbers)
        checkboxSavePasswd = view.findViewById(R.id.checkboxSavePasswd)

        fabCopy = view.findViewById(R.id.fabCopy)
        fabSavedList = view.findViewById(R.id.fabSavedList)
        fabCopy = view.findViewById(R.id.fabCopy)
        fam = view.findViewById(R.id.fab_menu)


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
            val manager: ClipboardManager =
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            manager.setText(tvGeneratedPasswd.text)
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
        })

        btnGenerate.setOnClickListener {
            tvGeneratedPasswd.text = generateRandomPassword()
        }

        fam.setOnMenuToggleListener { opened ->
            if (opened) {
                btnGenerate.visibility = View.GONE
            } else {
                btnGenerate.visibility = View.VISIBLE
            }
        }

        fabCopy.setOnClickListener(onButtonClick());
        fabSavedList.setOnClickListener(onButtonClick());

        fam.setOnClickListener {
            if (fam.isOpened) {
                fam.close(true)
            }
        }
    }

    private fun showToast(s: String) {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()

    }

    private fun onButtonClick(): View.OnClickListener? {
        return View.OnClickListener { view ->
            if (view === fabCopy) {
                tvGeneratedPasswd.setOnClickListener(View.OnClickListener {
                    val manager: ClipboardManager =
                        requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    manager.setText(tvGeneratedPasswd.text)
                    Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
                })

            } else if (view === fabSavedList) {
                showToast("Button Delete clicked")
            }
            fam.close(true)
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

        if (checkboxSavePasswd.isChecked) {
            Toast.makeText(context, "Password saved successfully", Toast.LENGTH_SHORT).show();
        }

        for (i in sb.length until progress) {
            sb.append(allowedChars[rn.nextInt(allowedChars.length)])
        }
        return sb.toString()
    }
}