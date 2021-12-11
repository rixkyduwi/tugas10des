package com.rizkydwisaputra.facemaskdetection

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.media.Image
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicYuvToRGB
import java.lang.reflect.Type
import java.nio.ByteBuffer

class YuyToRgbConverter (context:Context) {
    private val rs = RenderScript.create(context)
    private val scriptYuvToRgb = ScriptIntrinsicYuvToRGB.create(rs, Element.U8_4(rs))
    private lateinit var yuvBuffer: ByteBuffer
    private lateinit var inputAllocation: Allocation
    private lateinit var outputAllocation: Allocation
    private var pixelCount: Int = -1

    @Synchronized
    fun yuvToRgb(image: Image, output: Bitmap) {
        if (!::yuvBuffer.isInitialized) {
            pixelCount = image.cropRect.width() * image.cropRect.height()
            val pixelSizeBits = ImageFormat.getBitsPerPixel(ImageFormat.YUV_420_888)
            yuvBuffer = ByteBuffer.allocateDirect(pixelCount * pixelSizeBits / 8)
        }
        yuvBuffer.rewind()
        imageToByteBuffer(image.yuvBuffer.array())

        if (!::inputAllocation.isInitialized) {
            val elemType = Type.Builder(rs.Element.YUV(rs)).setYuvFormat(ImageFormat.NV21).create()
            inputAllocation = Allocation.createSized(rs,elemType.element,yuvBuffer.array().size)
        }
        if (!::outputAllocation.isInitialized){
            outputAllocation=Allocation.createFromBitmap(rs.output)
        }
        inputAllocation.copyFrom(yuvBuffer.array())
        scriptYuvToRgb.setInput(inputAllocation)
        scriptYuvToRgb.forEach(outputAllocation)
        outputAllocation.copyTo(output)
    }

    private fun imageToByteBuffer(image: Image,outputBuffer:ByteArray) {
        if (BuildConfig.DEBUG && image.format.Yuv_420_888){
            error("Assertion Failure")
        }
        val imageCrop=image.cropRect
        val imagePlanes=image.planes
        imagePlanes.forEachIndexed{planeIndex,plane->
            val outputStride:Int
            val outputOffset:Int
            when(planeIndex){
                0->{
                    outputStride=1
                    outputOffset=0
                }
                1->{
                    outputStride=2
                    outputOffset=pixelCount+1
                }
                2->{
                    outputStride=2
                    outputOffset=pixelCount
                }
                else->{
                    return@forEachIndexed
                }
            }
        }

    }

}