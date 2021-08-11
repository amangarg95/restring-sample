package com.amangarg.localizationdemo

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import dev.b3nedikt.restring.Restring.init
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump.init

class LocalizationDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        demoApplication = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        init(this)
        init(RewordInterceptor)
    }

    companion object {
        var context: Context? = null
            private set
        var demoApplication: LocalizationDemoApplication? = null
        operator fun get(activity: Activity): LocalizationDemoApplication {
            return activity.application as LocalizationDemoApplication
        }
    }
}