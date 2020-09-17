package com.orly.whispers.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepliesWhispers(
    val me2: Int,
    val nickname: String,
    val replies: Int,
    val text: String,
    val url: String,
    val ts: Long,
    val wid: String
) : Parcelable