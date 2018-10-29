package com.laatonwalabhoot.dexter

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.File
import java.io.FileOutputStream
import kotlin.collections.ArrayList

class MainFragment: Fragment() {

    private lateinit var pdfRenderer: PdfRenderer
    private lateinit var parcelFileDescriptor: ParcelFileDescriptor
    private lateinit var pdfAdapter: PdfAdapter
    private lateinit var pinchRecyclerView: PinchRecyclerView

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pinchRecyclerView = view.findViewById(R.id.container_view)
        pinchRecyclerView.layoutManager = LinearLayoutManager(context)
        downloadPdfAndLoad()
    }

    private fun downloadPdfAndLoad() {
        load {
            loadPdf()
        } then {
            pdfAdapter.setList(renderPdf(pdfRenderer.pageCount))
            pinchRecyclerView.adapter = pdfAdapter
        }
    }

    private fun loadPdf() {
        val file = File(context?.cacheDir, "java_tutorial.pdf")
        if (!file.exists()) {
            val asset = context?.assets?.open("java_tutorial.pdf")
            val output = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var size = asset?.read(buffer)
            while (size != -1) {
                output.write(buffer, 0, size!!)
                size = asset?.read(buffer)
            }
            asset?.close()
            output.close()
        }
        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        pdfRenderer = PdfRenderer(parcelFileDescriptor)
        pdfAdapter = PdfAdapter()
    }

    private fun renderPdf(count: Int): List<Bitmap> {
        val list = ArrayList<Bitmap>()
        for (i in 0 until count) {
            list.add(renderPage(pdfRenderer.openPage(i)))
        }
        return list
    }

    private fun renderPage(page: PdfRenderer.Page): Bitmap {
        val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        page.close()
        return bitmap
    }

//    private fun closePdf() {
//        currentPage.close()
//        pdfRenderer.close()
//        parcelFileDescriptor.close()
//    }
}