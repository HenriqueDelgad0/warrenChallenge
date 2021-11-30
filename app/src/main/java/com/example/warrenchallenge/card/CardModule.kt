package com.example.warrenchallenge.card

import com.example.warrenchallenge.cardAPI.CardAPI
import com.example.warrenchallenge.cardAPI.CardRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)

abstract class CardModule {
    @Binds
    abstract fun bindCardRepository(bind: CardRepository): CardAPI


}