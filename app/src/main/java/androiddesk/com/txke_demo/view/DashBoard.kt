package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.Uiutils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-18
 */
class DashBoard @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attributeSet, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val radius = Uiutils.dip2px(150f)

    private var rectF: RectF? = null

    private var dash = Path()

    private var pathDashPathEffect: PathDashPathEffect? = null

    private val angle = 120f

    private val path = Path()

    private var pathMeasure: PathMeasure? = null

    private val length = Uiutils.dip2px(100f)

    init {
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.strokeWidth = Uiutils.dip2px(2f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF = RectF(width / 2f - radius, height / 2f - radius, width / 2f + radius, height / 2f + radius)
        path.addArc(rectF, 90 + angle / 2, 360 - angle)
        pathMeasure = PathMeasure(path, false)
        //设置paint的虚线特殊效果画圆  刻度
        if (pathDashPathEffect == null) {
            dash.addRect(0f, 0f, Uiutils.dip2px(2f), Uiutils.dip2px(10f), Path.Direction.CW)
            pathDashPathEffect = PathDashPathEffect(dash, (pathMeasure?.length!! - Uiutils.dip2px(2f)) / 20, 0f, PathDashPathEffect.Style.ROTATE)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //画椭圆
        canvas?.drawPath(path, paint)

        //画仪表盘刻度
        paint.pathEffect = pathDashPathEffect
        canvas?.drawPath(path, paint)
        paint.pathEffect = null

        canvas?.drawLine(width / 2f, height / 2f,
                (width / 2f + Math.cos(Math.toRadians(getAngleForMark(5))) * length).toFloat(),
                (height / 2f + Math.sin(Math.toRadians(getAngleForMark(5))) * length).toFloat(),
                paint)
    }

    private fun getAngleForMark(mark: Int): Double {
        return (90 + angle / 2 + (360 - angle) / 20 * mark).toDouble()
    }
}