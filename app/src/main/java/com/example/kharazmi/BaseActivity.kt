package com.example.kharazmi

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import org.intellij.lang.annotations.Language

abstract class BaseActivity: AppCompatActivity() {

    var language = "en"
    override fun attachBaseContext(newBase: Context) {
        LocaleContextWrapper.update(newBase, language)
        super.attachBaseContext(newBase)
    }


}