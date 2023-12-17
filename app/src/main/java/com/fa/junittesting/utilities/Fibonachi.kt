package com.fa.junittesting.utilities

object Fibonachi {

    /* return n-th number of this series
    * like this:
    * f(0)=0
    * f(1)=1
    * f(2)=f(0)+f(1)
    */

    fun fib(n: Int): Long {
        if (n == 0 || n == 1)
            return n.toLong()

        var a = 0L
        var b = 1L
        var c: Long = 0

        for (i in 2..n) {
            c = a + b
            a = b
            b = c
        }
        println("fibo class series = $c")
        return c
    }

}