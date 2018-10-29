package com.laatonwalabhoot.dexter

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class PdfAdapter: RecyclerView.Adapter<PdfViewHolder>() {

    private var imageList: List<Bitmap> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PdfViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.scroll_item, p0, false)
        return PdfViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(p0: PdfViewHolder, p1: Int) {
        p0.setImage(imageList[p1])
    }

    fun setList(imageList: List<Bitmap>) {
        this.imageList = imageList
        notifyDataSetChanged()
    }
}