package com.example.reusabelpostfeed.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTime(epochMs: Long): String {
    val sdf = SimpleDateFormat("dd MMM • HH:mm", Locale.getDefault())
    return sdf.format(Date(epochMs))
}