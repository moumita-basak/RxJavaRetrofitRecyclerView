package com.infosys.retrofitrxjava.modelnew

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName


data class ItemRow(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageHref")
    val imageHref: String
//    val imageHref: Int

    )

@BindingAdapter("imageHref")
fun loadImage(view: ImageView, imageHref: Int?) {
    Glide.with(view.getContext())
        .load(imageHref)
        .into(view)
}
