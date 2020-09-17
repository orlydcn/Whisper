package com.orly.whispers.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepliesFeed(
    val replies: List<RepliesWhispers>
) : Parcelable