package com.example.warrenchallenge.extensions

import org.junit.Test
import com.example.warrenchallenge.extensions.calculateProgressBar

class ProgressBarTest {
    @Test
    fun `check if the given percentage has been calculated right`() {
         assert(calculateProgressBar(6.0f, 12.0) == 50.0)
    }

    @Test
    fun `check return if goalAmount is null`() {
        assert(calculateProgressBar(1328.0f, null) == 0.0)
    }

    @Test
    fun `check the minimum result`() {
        assert(calculateProgressBar(522.295f, 200000.00) == 1.0)
    }
}