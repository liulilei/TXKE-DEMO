package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.UiUtils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-26
 */
class PointView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var pointF = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.strokeWidth = UiUtils.dip2px(10f)
        paint.strokeCap = Paint.Cap.ROUND
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPoint(pointF.x, pointF.y, paint)
    }
}