package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.UiUtils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-24
 */
class CircleView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var radius = UiUtils.dip2px(50f)
        set(value) {
            field = value
            invalidate()
        }

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(width / 2f, height / 2f, radius, paint)
    }

}