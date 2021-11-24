package com.amangarg.localizationdemo

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.fasterxml.jackson.databind.ObjectMapper
import dev.b3nedikt.restring.Restring
import dev.b3nedikt.reword.Reword.reword
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.collections.HashMap

class MainActivity : BaseActivity() {
    lateinit var btnEnglish: AppCompatButton
    lateinit var btnSpanish: AppCompatButton
    lateinit var englishLocale: Locale
    lateinit var spanishLocale: Locale
    var englishStringsMap: HashMap<String, String> = HashMap()
    var spanishStringsMap: HashMap<String, String> = HashMap()

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEnglish = findViewById(R.id.btn_english)
        btnSpanish = findViewById(R.id.btn_spanish)

        configureLocales()
        updateStringsHashmap()

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

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun updateStringsHashmap() {
        getLocalStringJsonHashmap("en").forEach {
            englishStringsMap[it.key] = it.value
        }
        getLocalStringJsonHashmap("es").forEach {
            spanishStringsMap[it.key] = it.value
        }
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

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun getLocalStringJsonHashmap(language: String): HashMap<String, String> {
        val listTypeJson: HashMap<String, String> = HashMap()
        try {
            applicationContext.assets.open("$language.json").use { inputStream ->
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                val jsonString = String(buffer, StandardCharsets.UTF_8)
                JsonHelper().getFlattenedHashmapFromJsonForLocalization(
                    "",
                    ObjectMapper().readTree(jsonString),
                    listTypeJson
                )
            }
        } catch (exception: IOException) {
        }
        return listTypeJson
    }
}