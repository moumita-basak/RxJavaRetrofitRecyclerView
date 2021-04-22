package com.infosys.retrofitrxjava.modelnew

import com.google.gson.annotations.SerializedName

data class ItemNewRespose(
    @SerializedName("Row")
    val rows: List<Row>,
    @SerializedName("title")
    val title: String
)