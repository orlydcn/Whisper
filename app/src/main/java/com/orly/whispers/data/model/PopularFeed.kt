package com.orly.whispers.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularFeed(
    val popular: List<PopularWhispers>,
    val scroll_id: String?
) : Parcelable