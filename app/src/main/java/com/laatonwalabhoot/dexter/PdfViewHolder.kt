package com.laatonwalabhoot.dexter

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView

class PdfViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var imageView: ImageView = itemView.findViewById(R.id.image_container)

    fun setImage(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }
}