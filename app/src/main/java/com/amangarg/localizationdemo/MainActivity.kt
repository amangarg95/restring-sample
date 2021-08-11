package com.amangarg.localizationdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import dev.b3nedikt.restring.Restring
import dev.b3nedikt.reword.Reword
import dev.b3nedikt.reword.Reword.reword
import java.util.*
import kotlin.collections.HashMap

class MainActivity : BaseActivity() {
    lateinit var btnEnglish: AppCompatTextView
    lateinit var btnSpanish: AppCompatTextView
    lateinit var englishLocale: Locale
    lateinit var spanishLocale: Locale
    var englishStringsMap: HashMap<String, String> = HashMap()
    var spanishStringsMap: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEnglish = findViewById(R.id.tv_english)
        btnSpanish = findViewById(R.id.tv_spanish)
        englishStringsMap["text_greeting"] = "Have a nice day!"
        spanishStringsMap["text_greeting"] = "Que tenga un lindo día!"
        configureLocales()
        btnEnglish.setOnClickListener {
            updateAppLanguage("en")
        }

        btnSpanish.setOnClickListener {
            updateAppLanguage("es")
        }
    }

    private fun configureLocales() {
        englishLocale = Locale("en")
        spanishLocale = Locale("es")
    }

    private fun updateAppLanguage(language: String) {
        if (language == "en") {
            Restring.putStrings(englishLocale, englishStringsMap)
            Restring.locale = englishLocale
        } else {
            Restring.putStrings(spanishLocale, spanishStringsMap)
            Restring.locale = spanishLocale
        }
        updateView()
    }

    private fun updateView() {
        val rootView: View = window.decorView.findViewById(android.R.id.content)
        reword(rootView)
    }
}