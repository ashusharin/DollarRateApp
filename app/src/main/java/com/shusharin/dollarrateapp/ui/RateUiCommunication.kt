package com.shusharin.dollarrateapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shusharin.dollarrateapp.core.Abstract

interface RateUiCommunication : Abstract.Mapper {
    fun map(rates: List<RateUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<RateUi>>)

    class Base : RateUiCommunication {
        private val listLiveData = MutableLiveData<List<RateUi>>()
        override fun map(rates: List<RateUi>) {
            listLiveData.value = rates
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<RateUi>>) {
            listLiveData.observe(owner, observer)
        }
    }
}