package com.shusharin.dollarrateapp.di

import com.shusharin.dollarrateapp.data.RateRepository
import com.shusharin.dollarrateapp.domain.RateDomainListToUiMapper
import com.shusharin.dollarrateapp.domain.RateDomainToUiMapper
import com.shusharin.dollarrateapp.domain.RateInteractor
import com.shusharin.dollarrateapp.ui.BaseRateDomainListToUiMapper
import com.shusharin.dollarrateapp.ui.BaseRateDomainToUiMapper
import com.shusharin.dollarrateapp.ui.RateUiCommunication
import com.shusharin.dollarrateapp.ui.ResourceProvider
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
   abstract fun provideRepository(impl: RateRepository.Base): RateRepository

    @Binds
    abstract  fun provideRateDomainListToUiMapper(impl: BaseRateDomainListToUiMapper): RateDomainListToUiMapper

    @Binds
    abstract fun provideRateDomainToUiMapper(impl: BaseRateDomainToUiMapper): RateDomainToUiMapper

    @Binds
    abstract fun provideRateInteractor(impl: RateInteractor.Base): RateInteractor

    @Binds
    abstract fun provideRateUiCommunication(impl: RateUiCommunication.Base): RateUiCommunication

    @Binds
    abstract fun provideResourceProvider(impl: ResourceProvider.Base): ResourceProvider
}