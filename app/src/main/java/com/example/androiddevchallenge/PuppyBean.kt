package com.example.androiddevchallenge

import androidx.annotation.DrawableRes
import java.io.Serializable

data class PuppyBean(
    val id: Int,
    val name: String,
    @DrawableRes
    val resId: Int,
    val detail: String,
    val subtitle:String
):Serializable