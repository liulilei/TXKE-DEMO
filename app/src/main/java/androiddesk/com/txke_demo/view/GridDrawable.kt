package androiddesk.com.txke_demo.view

import android.graphics.*
import android.graphics.drawable.Drawable
import androiddesk.com.txke_demo.util.UiUtils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-30
 */
class GridDrawable : Drawable() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val widthLenght = 4

    private val heightCount = 5

    init {
        paint.apply {
            strokeWidth = UiUtils.dip2px(3f)
            color = Color.RED
        }
    }

    override fun draw(canvas: Canvas) {
        val width = (bounds.right - (widthLenght) * UiUtils.dip2px(3f)) / widthLenght
        val height = (bounds.bottom - (heightCount) * UiUtils.dip2px(3f)) / heightCount
        for (i in 0..widthLenght) {
            canvas.drawLine(i * width + i * UiUtils.dip2px(3f), bounds.top.toFloat(), i * width + i * UiUtils.dip2px(3f), bounds.bottom.toFloat(), paint)
        }
        for (i in 0..heightCount) {
            canvas.drawLine(bounds.left.toFloat(), i * height + i * UiUtils.dip2px(3f), bounds.right.toFloat(), i * height + i * UiUtils.dip2px(3f), paint)
        }
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getAlpha(): Int {
        return paint.alpha
    }

    //设置不透明度  透明度
    override fun getOpacity(): Int {
        return when (paint.alpha) {
            0xff -> PixelFormat.OPAQUE //不透明
            0 -> PixelFormat.TRANSPARENT//全透明
            else -> PixelFormat.TRANSLUCENT//半透明
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }
}