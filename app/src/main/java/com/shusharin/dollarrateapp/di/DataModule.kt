package com.shusharin.dollarrateapp.di

import com.shusharin.dollarrateapp.data.RateDataListToDomainMapper
import com.shusharin.dollarrateapp.data.RateDataToDomainMapper
import com.shusharin.dollarrateapp.data.RateRepository
import com.shusharin.dollarrateapp.data.net.RateCloudDataSource
import com.shusharin.dollarrateapp.data.net.RateCloudListMapper
import com.shusharin.dollarrateapp.data.net.RateCloudToDataMapper
import com.shusharin.dollarrateapp.data.sharePreference.SharePref
import com.shusharin.dollarrateapp.domain.BaseRateDataListToDomainMapper
import com.shusharin.dollarrateapp.domain.BaseRateDataToDomainMapper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class DataModule() {

    @Provides
    fun provideRateDataListToDomain(impl: BaseRateDataListToDomainMapper): RateDataListToDomainMapper {
        return impl
    }

    @Provides
    fun provideRateDataToDomain(impl: BaseRateDataToDomainMapper): RateDataToDomainMapper {
        return impl
    }

    @Provides
    fun provideSharePref(impl: SharePref.Base): SharePref {
        return impl
    }

    @Provides
    fun provideCloudDataSource(impl: RateCloudDataSource.Base): RateCloudDataSource {
        return impl
    }

    @Provides
    fun provideRateCloudListMapper(impl: RateCloudListMapper.Base): RateCloudListMapper {
        return impl
    }
    @Provides
    fun provideRateCloudToDataMapper(impl: RateCloudToDataMapper.Base): RateCloudToDataMapper {
        return impl
    }

}