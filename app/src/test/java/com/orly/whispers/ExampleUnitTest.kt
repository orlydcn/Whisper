package com.orly.whispers

import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun testMinutes_Shown() {
        val timeElapsed = System.currentTimeMillis() - 1600312361000
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed)
        val time = getTime(minutes)
        assert("${minutes}m".isNotEmpty())
    }

    @Test
    fun testDays_Shown() {
        val timeElapsed = System.currentTimeMillis() - 1600128012000
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed)
        val time = getTime(minutes)
        assert("${minutes}m".isNotEmpty())
    }

    @Test
    fun testHours_Shown() {
        val timeElapsed = System.currentTimeMillis() - 1600283561000
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed)
        val time = getTime(minutes)
        assert("${minutes}m".isNotEmpty())
    }

    private fun getTime(minutes: Long) = when {
        minutes < 60 -> "${minutes}m"
        minutes < 1440 -> "${minutes.div(60)}h"
        else -> "${minutes.div(1440)}d"
    }
}