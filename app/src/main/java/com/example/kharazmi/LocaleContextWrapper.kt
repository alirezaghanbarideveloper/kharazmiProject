package com.example.kharazmi

import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.*


class LocaleContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {

        fun wrap(ctx: Context, language: String) =
            LocaleContextWrapper(
                apply(
                    ctx,
                    language
                )
            )

        fun update(ctx: Context, language: String) =
            apply(
                ctx,
                language
            )

        @SuppressWarnings("deprecation")
        private fun apply(ctx: Context, language: String): Context {
            var context = ctx
            val config = context.resources.configuration
            val sysLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getSystemLocale(
                    config
                )
            } else {
                getSystemLocaleLegacy(
                    config
                )
            }

            if (language != "" && sysLocale.language != language) {
                val locale = Locale(language)
                Locale.setDefault(locale)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    setSystemLocale(
                        config,
                        locale
                    )
                } else {
                    setSystemLocaleLegacy(
                        config,
                        locale
                    )
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    context = context.createConfigurationContext(config)
                } else {

                    val overrideConfiguration: Configuration =
                        context.resources.configuration

                    context.resources.updateConfiguration(config,
                        context.resources.displayMetrics)

                    //context.resources.updateConfiguration(config, context.resources.displayMetrics)
                }
            }
            return context
        }

        private fun getSystemLocaleLegacy(config: Configuration): Locale {
            return config.locale
        }

        @TargetApi(Build.VERSION_CODES.N)
        fun getSystemLocale(config: Configuration): Locale {
            return config.locales.get(0)
        }

        @SuppressWarnings("deprecation")
        private fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
            config.locale = locale
            config.setLayoutDirection(locale)
        }

        @TargetApi(Build.VERSION_CODES.N)
        fun setSystemLocale(config: Configuration, locale: Locale) {
            config.setLocale(locale)
            config.setLayoutDirection(locale)
        }
    }
}