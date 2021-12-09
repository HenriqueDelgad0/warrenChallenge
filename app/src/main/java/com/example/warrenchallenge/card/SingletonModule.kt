package com.example.warrenchallenge.card

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.warrenchallenge.cardAPI.CardAPI
import com.example.warrenchallenge.data.EnigmaticApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Provides = usar quando vc quiser instanciar uma classe/interface que NAO ESTA NO SEU PROJETO
// Binds = usar quando vc quiser ensinar pro Hilt quem implementa uma interface, e essa implemtação ESTÁ NO SEU PROJETO
// todo o resto o Hilt manda bala sozinhooooo

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("sharedPrefs", AppCompatActivity.MODE_PRIVATE)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://enigmatic-bayou-48219.herokuapp.com/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCardApi(retrofit: Retrofit): CardAPI {
        return retrofit.create(CardAPI::class.java)
    }

    @Provides
    fun provideEnigmaticApi(retrofit: Retrofit) : EnigmaticApi{
        return retrofit.create(EnigmaticApi::class.java)
    }

    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

}