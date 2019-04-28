package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.Uiutils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-26
 */
class ProvinceView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var province = "北京市"
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.textSize = Uiutils.dip2px(30f)
        paint.textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawText(province, width / 2f, height / 2f, paint)
    }

}