package com.example.hellointernationalization7

import android.content.Context
import android.os.LocaleList
import java.util.*


// https://stackoverflow.com/questions/39705739/android-n-change-language-programmatically
class ContextWrapper(base: Context) : android.content.ContextWrapper(base) {
    companion object {

        fun wrap(context: Context, newLocale: Locale): ContextWrapper {
            var context = context
            val res = context.resources
            val configuration = res.configuration

            if (android.os.Build.VERSION.SDK_INT  >= 24) {
                configuration.setLocale(newLocale)

                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)

                context = context.createConfigurationContext(configuration)

            } else if (android.os.Build.VERSION.SDK_INT >= 17) {
                configuration.setLocale(newLocale)
                context = context.createConfigurationContext(configuration)

            } else {
                configuration.locale = newLocale
                res.updateConfiguration(configuration, res.displayMetrics)
            }

            return ContextWrapper(context)
        }
    }
}