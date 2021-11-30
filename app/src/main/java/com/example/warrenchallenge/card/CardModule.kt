package com.example.warrenchallenge.card

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CardModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTokenRepository(instance: SharedPreferencesTokenRepository): TokenRepository

}