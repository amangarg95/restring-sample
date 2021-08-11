package com.amangarg.localizationdemo

import android.content.Context
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.ViewPumpAppCompatDelegate
import dev.b3nedikt.restring.Restring.wrapContext


open class BaseActivity : AppCompatActivity() {
    private var appCompatDelegate: AppCompatDelegate? = null

    @NonNull
    override fun getDelegate(): AppCompatDelegate {
        if (appCompatDelegate == null) {
            appCompatDelegate = ViewPumpAppCompatDelegate(
                super.getDelegate(),
                this
            ) { base: Context -> wrapContext(base) }
        }
        return appCompatDelegate as AppCompatDelegate
    }
}