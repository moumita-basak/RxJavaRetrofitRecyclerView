package com.infosys.retrofitrxjava.modelnew

import com.google.gson.annotations.SerializedName

data class Items(
    val rows: MutableList<ItemRow>,
    @SerializedName("title")
    val title: String
)