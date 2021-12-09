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
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class CardRepositoryTest {
    lateinit var mockCardApi: CardAPI
    lateinit var cardRepository: CardRepository

    private lateinit var mockedApiResponse: TokenData

    @Before
    fun before() {
        val background = Background("google.com", "google.com", "google.com",
            "google.com", "google.com")
        val data = Data("1", "Disney", background,
            100.0f, 1000.0, "10/10/21")
        val list = mutableListOf<Data>(data, data, data)
        mockedApiResponse = TokenData(list)

        mockCardApi = mockk()
        coEvery {
            mockCardApi.makeRequest(any())
        } returns mockedApiResponse
        cardRepository = CardRepository(mockCardApi)
    }

    @Test
    fun `check the return type of data`() = runTest {
        val response = cardRepository.callRequest("teste")
        coVerify {
            mockCardApi.makeRequest("teste")
        }
        assertEquals(mockedApiResponse, response)
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

}