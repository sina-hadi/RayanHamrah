package com.codinginflow.rayanhamrah.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.codinginflow.rayanhamrah.R
import kotlin.math.min

class CustomEthernet(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var size = 0
    private val image: Drawable = resources.getDrawable(R.drawable.image)
    private val paint = Paint()

    var leftColor = resources.getColor(R.color.green)
    set(state) {
        field = resources.getColor(state)
        invalidate()
    }
    var rightColor = resources.getColor(R.color.red)
        set(state) {
            field = resources.getColor(state)
            invalidate()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        image.setBounds(0, 0, width, height)
        image.draw(canvas!!)

        paint.color = leftColor
        val leftRect = RectF(0.124f * width, 0.667f * width, 0.375f * width, 0.875f * width)
        canvas.drawRect(leftRect, paint)

        paint.color = rightColor
        val rightRect = RectF(0.876f * width, 0.667f * width, 0.625f * width, 0.875f * width)
        canvas.drawRect(rightRect, paint)

    }
}