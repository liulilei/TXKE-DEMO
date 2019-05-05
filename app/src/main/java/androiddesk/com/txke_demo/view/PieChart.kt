package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.UiUtils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-18
 */
class PieChart @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val radius = UiUtils.dip2px(150f)

    private val length = UiUtils.dip2px(20f)

    private val rectF = RectF()

    private val colors = intArrayOf(Color.RED, Color.BLUE, Color.CYAN, Color.GREEN)

    private val angle = floatArrayOf(60f, 100f, 120f, 80f)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(width / 2f - radius, height / 2f - radius, width / 2f + radius, height / 2f + radius)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var currentAngle = 0f
        colors.forEachIndexed { index, i ->
            paint.color = i
            if (index == 2) {
                canvas?.save()
                canvas?.translate((Math.cos(Math.toRadians((currentAngle + angle[index] / 2).toDouble())) * length).toFloat(),
                        (Math.sin(Math.toRadians((currentAngle + angle[index] / 2).toDouble())) * length).toFloat())
            }
            canvas?.drawArc(rectF, currentAngle, angle[index], true, paint)
            if (index == 2) {
                canvas?.restore()
            }
            currentAngle += angle[index]
        }
    }
}