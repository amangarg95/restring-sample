package com.amangarg.localizationdemo

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : BaseActivity() {
    lateinit var btnEnglish: AppCompatTextView
    lateinit var btnSpanish: AppCompatTextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEnglish = findViewById(R.id.tv_english)
        btnSpanish = findViewById(R.id.tv_spanish)
        btnEnglish.setOnClickListener {

        }

        btnSpanish.setOnClickListener {

        }
    }
}