package com.justappz.aniyomitv.di

import com.justappz.aniyomitv.data.remote.ApiClient
import com.justappz.aniyomitv.data.remote.ApiService
import com.justappz.aniyomitv.data.remote.repo.AllAnimeRepoImpl
import com.justappz.aniyomitv.domain.repo.AllAnimeRepo
import com.justappz.aniyomitv.domain.usecase.GetAnimeListUseCase
import com.justappz.aniyomitv.domain.usecase.GetEpisodeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApiService(): ApiService = ApiClient.apiService

    @Provides
    fun provideAnimeRepository(apiService: ApiService): AllAnimeRepo {
        return AllAnimeRepoImpl(apiService)
    }

    @Provides
    fun provideGetAnimeListUseCase(repository: AllAnimeRepo): GetAnimeListUseCase =
        GetAnimeListUseCase(repository)

    @Provides
    fun provideGetEpisodeListUseCase(repository: AllAnimeRepo): GetEpisodeListUseCase =
        GetEpisodeListUseCase(repository)

}