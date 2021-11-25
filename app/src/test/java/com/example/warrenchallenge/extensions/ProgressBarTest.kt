package com.example.warrenchallenge.extensions

import org.junit.Test
import com.example.warrenchallenge.extensions.calculateProgressBar

class ProgressBarTest {
    val progressBarStatus = 0.261147621
    val progressBarStatus1 = 1.080404265

    @Test
    fun progressTest1() {
         assert(calculateProgressBar(6.0, 12.0f) == 50.0)
    }
}