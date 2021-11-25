package com.example.warrenchallenge.extensions

import org.junit.Test
import com.example.warrenchallenge.extensions.calculateProgressBar

class ProgressBarTest {
    @Test
    fun `check if the given percentage has been calculated right`() {
         assert(calculateProgressBar(6.0, 12.0f) == 50.0)
    }
}