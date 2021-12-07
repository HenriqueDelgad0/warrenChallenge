package com.example.warrenchallenge.repositories

import androidx.lifecycle.MutableLiveData
import com.example.warrenchallenge.cardAPI.*
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.data.PostData
import com.example.warrenchallenge.model.ApiException
import com.example.warrenchallenge.model.FeatureException
import io.mockk.MockKException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class CardRepositoryTest {
    lateinit var mockCardApi: CardAPI
    lateinit var cardRepository: CardRepository

    @Before
    fun before() {
        val background = Background("google.com", "google.com", "google.com",
            "google.com", "google.com")
        val data = Data("1", "Disney", background,
            100.0f, 1000.0, "10/10/21")
        val lista = mutableListOf<Data>(data, data, data)

        mockCardApi = mockk()
        coEvery {
            mockCardApi.makeRequest(any())
        } returns TokenData(portfolios = lista)
        cardRepository = CardRepository(mockCardApi)
    }

    @Test
    fun `test when the request is call`() = runTest {
        cardRepository.callRequest("teste")
        coVerify {
            mockCardApi.makeRequest("teste")
        }
    }

    // FAILURE TESTS REGION
    @Test
    fun `test when the wrong token is passed`() = runTest {
        mockCardApi = mockk()
        cardRepository.callRequest("abuble")
        coEvery {
            mockCardApi.makeRequest("ablebu")
        } throws(mockException())
    }

//    @Test
//    fun `test when the request are failing then the API error should be catched`() = runTest {
//        mockEnigmaticApi = mockk()
//        coEvery {
//            mockEnigmaticApi.makeRequest(any())
//        } throws(mockException())
//
//        enigmaticRepository = EnigmaticRepository(mockEnigmaticApi)
//        assertFailsWith<ApiException> {
//            enigmaticRepository.callRequest("email", "password")
//        }
//    }
}