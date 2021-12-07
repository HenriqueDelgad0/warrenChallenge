package com.example.warrenchallenge.repositories

import com.example.warrenchallenge.data.EnigmaticApi
import com.example.warrenchallenge.data.EnigmaticRepository
import com.example.warrenchallenge.data.PostData
import com.example.warrenchallenge.model.ApiException
import com.example.warrenchallenge.model.FeatureException
import com.example.warrenchallenge.model.Token
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class EnigmaticRepositoryTest {

    lateinit var mockEnigmaticApi: EnigmaticApi
    lateinit var enigmaticRepository: EnigmaticRepository

    @Before
    fun before() {
        mockEnigmaticApi = mockk()
        coEvery {
            mockEnigmaticApi.makeRequest(any())
        } returns Token(accessToken = "anyone", refreshToken = "another one")
        enigmaticRepository = EnigmaticRepository(mockEnigmaticApi)
    }

    @Test
    fun `test when there is password then the request should run`() = runTest {
        enigmaticRepository.callRequest("email", "password")
        coVerify {
            mockEnigmaticApi.makeRequest(PostData("email", "password"))
        }
    }

    // FAILURE TESTS REGION
    @Test
    fun `test when there is no password then an error should be thrown`() = runTest {
        assertFailsWith<FeatureException> {
            enigmaticRepository.callRequest("anymail", "       ")
        }
    }

    @Test
    fun `test when the request are failing then the API error should be catched`() = runTest {
        mockEnigmaticApi = mockk()
        coEvery {
            mockEnigmaticApi.makeRequest(any())
        } throws(mockException())

        enigmaticRepository = EnigmaticRepository(mockEnigmaticApi)
        assertFailsWith<ApiException> {
            enigmaticRepository.callRequest("email", "password")
        }
    }
}