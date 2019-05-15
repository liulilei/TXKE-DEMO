package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.UiUtils

/**
 *@Description:
 * @author: lll
 * @date: 2019-05-15
 */
class CircleMeasureView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0
) : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val radius = UiUtils.dip2px(80f)

    private val padding = UiUtils.dip2px(20f)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = ((padding + radius) * 2).toInt()
        setMeasuredDimension(resolveSize(size, widthMeasureSpec), resolveSize(size, heightMeasureSpec))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = Color.RED
        canvas?.drawCircle(radius + padding, radius + padding, radius, paint)

    }

}