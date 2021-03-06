package com.eericxu.cslibrary

import android.content.Context
import android.graphics.Canvas
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log

class CSLayout : ConstraintLayout,CSInterface {
    override fun csHelper(): CSHelper {
        return csHelper
    }

    val csHelper = CSHelper()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        csHelper.initAttr(this,context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        csHelper.initAttr(this,context, attrs)
    }

    var isDrawed = false
    override fun draw(canvas: Canvas?) {
        isDrawed = true
        csHelper.drawBefore(canvas,isInEditMode)
        super.draw(canvas)
        csHelper.drawAfter(canvas,isInEditMode)

    }

    override fun dispatchDraw(canvas: Canvas?) {
        if (isDrawed)
            super.dispatchDraw(canvas)
        else {
            csHelper.drawBefore(canvas,isInEditMode)
            super.dispatchDraw(canvas)
            csHelper.drawAfter(canvas,isInEditMode)
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        csHelper.onSizeChange(this,w, h)
    }
}