package com.weynard02.finalfantasyapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val title: String,
    val description: String,
    val photo: Int,
    val character: String,
    val score: Int,
    val trailer: String
) : Parcelable
