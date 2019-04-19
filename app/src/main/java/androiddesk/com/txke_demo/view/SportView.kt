package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.Uiutils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-19
 */
class SportView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val radius = Uiutils.dip2px(150f)

    private val rectF: RectF = RectF()

    private val bounds = Rect()

    private val metrics = Paint.FontMetrics()

    init {
        paint.strokeWidth = Uiutils.dip2px(15f)
        paint.style = Paint.Style.STROKE
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = Uiutils.dip2px(100f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(width / 2f - radius, height / 2f - radius, width / 2f + radius, height / 2f + radius)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //画圆
        paint.color = Color.GRAY
        canvas?.drawOval(rectF, paint)

        //画进度条
        paint.color = Color.RED
        canvas?.drawArc(rectF, -90f, 225f, false, paint)

        val text = "abap"
        //话文字
        paint.style = Paint.Style.FILL
        paint.color = Color.GREEN
//        val offset = (bounds.bottom + bounds.top) / 2f
        paint.getFontMetrics(metrics)
        val offset = (metrics.descent + metrics.ascent) / 2f
        canvas?.drawText(text, width / 2f, height / 2f - offset, paint)

        //文字上对齐
        paint.textAlign = Paint.Align.LEFT
        paint.getTextBounds(text, 0, text.length, bounds)
        canvas?.drawText(text, -bounds.left.toFloat(), -bounds.top.toFloat(), paint)

        //左对齐
        paint.textSize = Uiutils.dip2px(30f)
        paint.getTextBounds(text, 0, text.length, bounds)
        canvas?.drawText(text, -bounds.left.toFloat(), -bounds.top + paint.fontSpacing, paint)
    }
}