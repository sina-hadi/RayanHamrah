package com.codinginflow.rayanhamrah.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.codinginflow.rayanhamrah.R

class CustomProgressBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val greenColor = resources.getColor(R.color.green)
    private val redColor = resources.getColor(R.color.red)
    private val lightGrayColor = resources.getColor(R.color.lightGray)
    private val grayColor = resources.getColor(R.color.gray)

    var minPrice = 0
    var maxPrice = 0
    var maxRange = 0
    var minRange = 0
    var firstPrice = 0
    var lastPrice = 0

    private val circleRadius = 10f
    private val margin = 5f
    private val lineWidth = 7
    private val xTriangle = 20f
    private val yTriangle = 15f

    private val paint = Paint()
    private val path = Path()

//    init {
//        setupAttributes(attrs)
//    }
//
//    private fun setupAttributes(attrs: AttributeSet?) {
//        val typeArray = context.theme.obtainStyledAttributes(
//            attrs, R.styleable.CustomProgressBar, 0, 0
//        )
//
//        minPrice = typeArray.getInt(R.styleable.CustomProgressBar_minPrice, 300)
//        maxPrice = typeArray.getInt(R.styleable.CustomProgressBar_maxPrice, 780)
//        maxRange = typeArray.getInt(R.styleable.CustomProgressBar_maxRange, 800)
//        minRange = typeArray.getInt(R.styleable.CustomProgressBar_minRange, 200)
//        firstPrice = typeArray.getInt(R.styleable.CustomProgressBar_firstPrice, 0)
//        lastPrice = typeArray.getInt(R.styleable.CustomProgressBar_lastPrice, 500)
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(widthMeasureSpec, 70)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (minRange != 0 && maxRange != 0) {
            drawDefaultLine(canvas)
            drawPriceLine(canvas)
            bottomTriangle(canvas)
            upperTriangle(canvas)
            drawCircles(canvas)
        }
    }

    private fun drawCircles(canvas: Canvas) {
        paint.color = lightGrayColor
        paint.style = Paint.Style.FILL

        canvas.drawCircle(circleRadius, (height / 2).toFloat(), circleRadius, paint)
        canvas.drawCircle(width - circleRadius, (height / 2).toFloat(), circleRadius, paint)
    }

    private fun drawDefaultLine(canvas: Canvas) {
        paint.color = lightGrayColor
        val defaultLine =
            Rect(
                margin.toInt(),
                (height - lineWidth) / 2,
                width - margin.toInt(),
                (height + lineWidth) / 2
            )
        canvas.drawRect(defaultLine, paint)
    }

    private fun drawPriceLine(canvas: Canvas) {
        val startPosition =
            ((minPrice - minRange).toFloat() / (maxRange - minRange).toFloat() * width).toInt()
        val endPosition =
            ((maxPrice - minRange).toFloat() / (maxRange - minRange).toFloat() * width).toInt()
        val priceLine =
            Rect(startPosition, (height - lineWidth) / 2, endPosition, (height + lineWidth) / 2)

        paint.color = if (lastPrice - firstPrice >= 0) greenColor else redColor
        canvas.drawRect(priceLine, paint)
    }

    private fun upperTriangle(canvas: Canvas) {
        var firstPosition =
            (firstPrice - minRange).toFloat() / (maxRange - minRange).toFloat() * width
        val yUpper = (height - lineWidth) / 2f - 10

        if (width - firstPosition < 10) {
            firstPosition = width - 10f
        } else if (firstPosition < 10f) {
            firstPosition = 10f
        }

        path.moveTo(firstPosition, yUpper)
        path.lineTo(firstPosition + xTriangle / 2, yUpper - yTriangle)
        path.lineTo(firstPosition - xTriangle / 2, yUpper - yTriangle)
        path.lineTo(firstPosition, yUpper)

        paint.color = grayColor
        paint.style = Paint.Style.FILL
        canvas.drawPath(path, paint)
    }

    private fun bottomTriangle(canvas: Canvas) {
        var lastPosition =
            (lastPrice - minRange).toFloat() / (maxRange - minRange).toFloat() * width
        val yBottom = (height + lineWidth) / 2f + 10

        if (width - lastPosition < 10) {
            lastPosition = width - 10f
        } else if (lastPosition < 10f) {
            lastPosition = 10f
        }

        path.moveTo(lastPosition, yBottom)
        path.lineTo(lastPosition + xTriangle / 2, yBottom + yTriangle)
        path.lineTo(lastPosition - xTriangle / 2, yBottom + yTriangle)
        path.lineTo(lastPosition, yBottom)

        paint.color = grayColor
        paint.style = Paint.Style.FILL
        canvas.drawPath(path, paint)
    }

}