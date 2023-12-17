package com.fa.junittesting

import com.fa.junittesting.utilities.Fibonachi
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test


class FibonachiTest {

    @Test
    fun fib_test() {
        val realResult = Fibonachi.fib(6)
        val expResult = 8L
        //assertEquals(expResult, realResult)
        assertThat(realResult).isEqualTo(expResult)
    }

}