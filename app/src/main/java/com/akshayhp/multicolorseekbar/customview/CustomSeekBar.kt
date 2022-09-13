package com.akshayhp.multicolorseekbar.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar


class CustomSeekBar(context: Context, attr:AttributeSet): AppCompatSeekBar(context, attr) {
    class ProgressItem {
        var color = 0
        var progressItemPercentage = 0f
    }




    private var mProgressItemsList = listOf<ProgressItem>()

    private var mGapWidth = 0.01F

    fun setGapWidth(width:Float){
        mGapWidth = width
        invalidate()
    }

    private var mIndicatorPos = 0.01F
    private var mIndicatorWidth = 0.01F
    private var mIndicatorColor = 0

    fun setIndicator(pos:Float, width: Float, color:Int){
        mIndicatorPos = pos
        mIndicatorWidth = width
        mIndicatorColor = color
        invalidate()
    }

    fun setProgressList(progressItems:ArrayList<ProgressItem>){
        mProgressItemsList = progressItems
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        var lastProgressX = 0
        var progressItemWidth:Int
        var  progressItemRight:Int
        for ( item in mProgressItemsList) {

             var progressPaint = Paint()
            progressPaint.color = resources.getColor(item.color, context.theme)

            progressItemWidth =  (item.progressItemPercentage
                    * calculatedWidth() / 100).toInt()

            progressItemRight = lastProgressX + progressItemWidth

            var progressRect = Rect()
            progressRect.set(lastProgressX, 0, progressItemRight,
                height )
            canvas?.drawRect(progressRect, progressPaint)
            lastProgressX = progressItemRight + ( width * mGapWidth).toInt()

        }
        super.onDraw(canvas)
    }

    private fun calculatedWidth(): Int {
        return width.minus(mGapWidth.times(width).times(mProgressItemsList.size.minus(1).coerceAtLeast(0))).toInt()
    }
}