package com.shusharin.dollarrateapp.ui

import com.shusharin.dollarrateapp.core.Abstract

sealed class RateUi : Abstract.Object<Unit, RateUi.StringMapper> {
    override fun map(mapper: StringMapper) = Unit

    object Progress : RateUi()

    class Base(
        private val value: String,
        private val data: String,
    ) : RateUi() {
        override fun map(mapper: StringMapper) = mapper.setupText(value, data)
    }

    class Fail(
        private val message: String,
    ) : RateUi() {
        override fun map(mapper: StringMapper) = mapper.setupError(message)
    }


    interface StringMapper : Abstract.Mapper {
        fun setupText(value: String, data: String) = Unit
        fun setupError(message: String) = Unit
    }
}
