package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-28
 */
class DrawableView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val drawable = GridDrawable()

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawable.setBounds(0, 0, width, height)
        if (canvas != null) {
            drawable.draw(canvas)
        }

    }

}