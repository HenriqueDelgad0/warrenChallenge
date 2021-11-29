package com.example.warrenchallenge.card

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

@Module
@InstallIn(CardViewModel::class)

object CardModule {
    @ActivityScoped
    @Provides
    @Named("sharedPrefences")
    fun provideSharedPreferences() = "Testes"
}