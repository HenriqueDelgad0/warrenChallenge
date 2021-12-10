package com.example.warrenchallenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.warrenchallenge.card.CardViewModel
import com.example.warrenchallenge.card.TokenRepository
import com.example.warrenchallenge.cardAPI.Background
import com.example.warrenchallenge.cardAPI.CardRepository
import com.example.warrenchallenge.cardAPI.Data
import com.example.warrenchallenge.cardAPI.TokenData
import com.example.warrenchallenge.core.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class CardViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var cardRepository: CardRepository
    private lateinit var tokenRepository: TokenRepository
    private lateinit var viewModel: CardViewModel

    private lateinit var mockedApiResponse: TokenData

    @Before
    fun before() {
        val background = Background("google.com", "google.com", "google.com",
            "google.com", "google.com")
        val data = Data("1", "Disney", background,
            100.0f, 1000.0, "10/10/21")
        val list = mutableListOf<Data>(data, data, data)

        mockedApiResponse = TokenData(list)
        cardRepository = mockk()
        tokenRepository = mockk()

        coEvery {
            cardRepository.callRequest(any())
        } returns mockedApiResponse

        coEvery {
            tokenRepository.getApiToken()
        } returns "any"
    }

    @Test
    fun `test success emission`() = mainCoroutineRule.testDispatcher.runBlockingTest {
        viewModel = CardViewModel(cardRepository, tokenRepository, mainCoroutineRule.testDispatcher)
        assertTrue(viewModel.getCardResponse().value is Resource.Success)
    }


}