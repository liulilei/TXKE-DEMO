package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.UiUtils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-18
 */
class TestDrawLineView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attributeSet, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val path = Path()

    private val radius = UiUtils.dip2px(100f)

    init {
        paint.color = Color.RED
        paint.strokeWidth = UiUtils.dip2px(2f)
        paint.style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.addCircle(width / 2f, height / 2f, radius, Path.Direction.CCW)
        path.addRect(width /2f - radius, height /2f, width/ 2f + radius, height /2f + radius * 2, Path.Direction.CW)
        path.fillType = Path.FillType.WINDING
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawLine(0f, 0f, 400f, 400f, paint)
//        canvas?.drawCircle(width / 2f, height / 2f, Uiutils.dip2px(100f), paint)
        canvas?.drawPath(path, paint)
    }
}