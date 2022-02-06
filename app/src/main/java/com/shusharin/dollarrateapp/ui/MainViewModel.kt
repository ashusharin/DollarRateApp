package com.shusharin.dollarrateapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shusharin.dollarrateapp.data.sharePreference.SharePref
import com.shusharin.dollarrateapp.domain.RateDomainListToUiMapper
import com.shusharin.dollarrateapp.domain.RateInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val songsInteractor: RateInteractor,
    private val communication: RateUiCommunication,
    private val mapper: RateDomainListToUiMapper,
    private val sharePref: SharePref,
) : ViewModel(
) {

    fun fetchRates() {
        communication.map(listOf(RateUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = songsInteractor.fetchRates()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<RateUi>>) {
        communication.observe(owner, observer)
    }

    fun saveDollarRate(rate: String) {
        sharePref.save(rate)
    }

    fun readDollarRate(): String = sharePref.readOldRate()

}