package com.hamric.achievement

fun UInt.formatWithSeparatorPattern(separatorEveryDigit:UByte = 3u, separator: Char = '.'): String {
    return toString().reversed().chunked(separatorEveryDigit.toInt()).joinToString(separator.toString()).reversed()
}