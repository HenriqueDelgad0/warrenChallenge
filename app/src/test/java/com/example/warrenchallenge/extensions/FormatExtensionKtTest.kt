package com.example.warrenchallenge.extensions

import org.junit.Assert.*
import org.junit.Test

class FormatExtensionKtTest {
    val goalAmoutTest = 200000.00
    val secondGoalAmountTest = 1000000.00
    val thirdGoalAmountTest = 5000.00

    @Test
    fun formatTest(): Boolean {
        return goalAmoutTest.formatMoney() == "200,000.00"
    }

    @Test
    fun secondFormatTest(): Boolean {
        return secondGoalAmountTest.formatMoney() == "1,000,000.00"
    }

    @Test
    fun thirdFormatTest(): Boolean {
        return thirdGoalAmountTest.formatMoney() == "5,000.00"
    }


}