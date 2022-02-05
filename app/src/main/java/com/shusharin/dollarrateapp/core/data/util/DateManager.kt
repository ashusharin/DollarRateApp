package com.shusharin.dollarrateapp.core.data.util

import java.text.SimpleDateFormat
import java.util.*

interface DateManager {
    fun getRange(): Pair<String, String>
    fun getToday(): String
    fun getTenDay(): Pair<String, String>


    class Base() : DateManager {
        override fun getRange(): Pair<String, String> {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

            val currentDate = Calendar.getInstance().time
            val pastMonthDate = Calendar.getInstance()
            pastMonthDate.add(Calendar.MONTH, -1)// FIXME: 04.02.2022 названия

            val dateCurrentText = dateFormat.format(currentDate.time)
            val pastMonthDateText = dateFormat.format(pastMonthDate.time)

            return Pair(pastMonthDateText, dateCurrentText)
        }

        override fun getToday(): String {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val currentDate = Calendar.getInstance().time
            return dateFormat.format(currentDate.time)
        }

        override fun getTenDay(): Pair<String, String> {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

            val currentDate = Calendar.getInstance().time
            val pastTenDate = Calendar.getInstance()
            pastTenDate.add(Calendar.DAY_OF_MONTH, -10)// FIXME: 04.02.2022 названия

            val dateCurrentText = dateFormat.format(currentDate.time)
            val pastMonthDateText = dateFormat.format(pastTenDate.time)

            return Pair(pastMonthDateText, dateCurrentText)
        }
    }

}
