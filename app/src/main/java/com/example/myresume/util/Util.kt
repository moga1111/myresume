package com.example.myresume.util

import android.util.Log
import com.example.myresume.BuildConfig

/**
 * Utility class
 */
object Util {
    /**
     * Logger Level pool
     */
    enum class LoggerLevel {
        DEBUG,
        INFO,
        ERROR,
        WARNING
    }

    /**
     * Log function that wraps [Log] functions. It only output logs in Debug mode.
     * This function prints out extra lines to make reading easy.
     * @param loggerLevel debug, info, error, or warning
     * @param tag Log tag for logcat
     * @param message Log message
     */
    fun log(loggerLevel: LoggerLevel, tag: String, message: String) {
        // Cancel if not in debug mode
        if (!BuildConfig.DEBUG) {
            return
        }

        // Generate appropriate line
        val line = getLineWidth(message.length)

        when (loggerLevel) {
            Util.LoggerLevel.INFO -> {
                Log.i(tag, "/ $line \\")
                Log.i(tag, "  $message")
                Log.i(tag, "\\ $line /")
            }
            Util.LoggerLevel.DEBUG -> {
                Log.d(tag, "/ $line \\")
                Log.d(tag, "  $message")
                Log.d(tag, "\\ $line /")
            }
            Util.LoggerLevel.ERROR -> {
                Log.e(tag, "/ $line \\")
                Log.e(tag, "  $message")
                Log.e(tag, "\\ $line /")
            }
            Util.LoggerLevel.WARNING -> {
                Log.w(tag, "/ $line \\")
                Log.w(tag, "  $message")
                Log.w(tag, "\\ $line /")
            }
        }
    }

    /**
     * Create a line of the char '=' based on the length of the message
     * @param messageLength Number of chars to generate
     * @return Full line
     */
    private fun getLineWidth(messageLength: Int): String {
        val stringBuilder = StringBuilder()
        for (i in 0 until messageLength) {
            stringBuilder.append("=")
        }
        return stringBuilder.toString()
    }

}
