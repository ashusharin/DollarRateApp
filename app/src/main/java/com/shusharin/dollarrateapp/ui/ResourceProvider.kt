package com.shusharin.dollarrateapp.ui

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

interface ResourceProvider {
    fun getString(@StringRes id:Int):String

    class Base @Inject constructor(private val context: Context): ResourceProvider{
        override fun getString(id: Int): String = context.getString(id)
    }
}