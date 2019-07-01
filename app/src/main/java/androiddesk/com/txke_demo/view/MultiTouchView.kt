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
 * @date: 2019-06-27
 */
class MultiTouchView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defTypeAttr: Int = 0
) : View(context, attributeSet, defTypeAttr) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val IMAGE_WIDTH = UiUtils.dip2px(200f)

    private var bitmap = UiUtils.getAvatar(resources, IMAGE_WIDTH.toInt())

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)
    }
}