package com.orly.whispers.util

import java.util.concurrent.TimeUnit

object DateUtil {
    @JvmStatic
    fun timeElapsed(start: Long): String {
        val timeElapsed = System.currentTimeMillis() - start
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed)
        return when {
            minutes < 60 -> "${minutes}m"
            minutes < 1440 -> "${minutes.div(60)}h"
            else -> "${minutes.div(1440)}d"
        }
    }
}