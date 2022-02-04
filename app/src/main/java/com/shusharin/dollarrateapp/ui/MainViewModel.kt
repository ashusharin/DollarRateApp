package com.shusharin.dollarrateapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shusharin.dollarrateapp.domain.RateDomainListToUiMapper
import com.shusharin.dollarrateapp.domain.RateInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val songsInteractor: RateInteractor,
    private val communication: RateUiCommunication,
    private val mapper: RateDomainListToUiMapper,
) : ViewModel(
) {

    fun fetchRates() {
        communication.map(listOf(RateUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = songsInteractor.findListSong()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<RateUi>>) {
        communication.observe(owner, observer)
    }
}