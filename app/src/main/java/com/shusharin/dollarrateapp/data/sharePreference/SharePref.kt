package com.shusharin.dollarrateapp.data.sharePreference

import android.content.Context

interface SharePref {
    fun save(rate: String)
    fun readOldRate(): String

    class Base(context: Context) : SharePref {

        private val sharedPreferences =
            context.getSharedPreferences(DOLLAR_RATE_CONTROL, Context.MODE_PRIVATE)

        override fun save(rate: String) = sharedPreferences.edit().putString(RATE, rate).apply()
        override fun readOldRate(): String = sharedPreferences.getString(RATE, "0.0") ?: "0.0"
    }

    private companion object {
        const val DOLLAR_RATE_CONTROL = "dollar"
        const val RATE = "rate"
    }
}