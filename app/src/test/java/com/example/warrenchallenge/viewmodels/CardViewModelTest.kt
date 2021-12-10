package com.example.warrenchallenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.snapshots.SnapshotApplyResult
import com.example.warrenchallenge.card.CardViewModel
import com.example.warrenchallenge.card.TokenRepository
import com.example.warrenchallenge.cardAPI.*
import com.example.warrenchallenge.core.Resource
import com.example.warrenchallenge.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
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
            tokenRepository.saveTokenData(any())
        } returns Unit

        viewModel = CardViewModel(cardRepository, tokenRepository, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun `test success emission`() = mainCoroutineRule.testDispatcher.runBlockingTest {
        viewModel.loadGoals()
        assertTrue(viewModel.getCardResponse().value is Resource.Success)
    }

    fun `test success emission in CardApi`() = mainCoroutineRule.testDispatcher.runBlockingTest {

    }

    @Test
    fun `test error emission`() = mainCoroutineRule.testDispatcher.runBlockingTest {
        cardRepository = mockk()
        tokenRepository = mockk()
        coEvery {
            cardRepository.callRequest(any())
        } throws Exception("fake error")
        viewModel = CardViewModel(cardRepository, tokenRepository, mainCoroutineRule.testDispatcher)

        viewModel.loadGoals()
        assertTrue(viewModel.getCardResponse().value is Resource.Error)
    }


}