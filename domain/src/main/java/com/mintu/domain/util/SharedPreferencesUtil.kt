package com.mintu.domain.util

import android.content.SharedPreferences

const val LAST_FETCH_TIME = "FETCH_TIME"

private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
    val editMe = edit()
    operation(editMe)
    editMe.apply()
}

var SharedPreferences.lastFetchTime
    get() = getLong(LAST_FETCH_TIME, 0)
    set(value) {
        editMe {
            it.putLong(LAST_FETCH_TIME, value)
        }
    }
