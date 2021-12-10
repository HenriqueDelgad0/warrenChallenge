package com.example.warrenchallenge.cardAPI

import com.example.warrenchallenge.model.FeatureException
import com.example.warrenchallenge.model.toApiException
import retrofit2.HttpException
import javax.inject.Inject

class CardRepository @Inject constructor(private val cardAPI: CardAPI){
    suspend fun callRequest(token: String): TokenData {
        return try {
            if(token.isBlank()) {
                throw FeatureException("Token should not be blank")
            }
            cardAPI.makeRequest(token)
        } catch (e: HttpException) {
            throw e.toApiException()
        }
    }
}
