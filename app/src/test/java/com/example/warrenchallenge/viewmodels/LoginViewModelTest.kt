package com.example.warrenchallenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.warrenchallenge.card.TokenRepository
import com.example.warrenchallenge.core.Resource
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.login.LoginViewModel
import com.example.warrenchallenge.model.Token
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.test.assertTrue

class LoginViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var enigmaticRepository: EnigmaticRepository
    private lateinit var tokenRepository: TokenRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun before() {
        enigmaticRepository = mockk()
        tokenRepository = mockk()
        coEvery {
            enigmaticRepository.callRequest(any(), any())
        } returns Token("any token", "any refresh token")

        coEvery {
            tokenRepository.saveTokenData(any())
        } returns Unit
        viewModel = LoginViewModel(enigmaticRepository, tokenRepository, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun `test success emission`() = mainCoroutineRule.testDispatcher.runBlockingTest {
        viewModel.loginRequest("email", "senha")
        assertTrue(viewModel.getTokenResponse().value is Resource.Success)
    }

    @Test
    fun `test error emission`() = mainCoroutineRule.testDispatcher.runBlockingTest {
        enigmaticRepository = mockk()
        tokenRepository = mockk()
        coEvery {
            enigmaticRepository.callRequest(any(), any())
        } throws Exception("fake error")
        viewModel = LoginViewModel(enigmaticRepository, tokenRepository, mainCoroutineRule.testDispatcher)


        viewModel.loginRequest("email", "senha")
        assertTrue(viewModel.getTokenResponse().value is Resource.Error)
    }

}