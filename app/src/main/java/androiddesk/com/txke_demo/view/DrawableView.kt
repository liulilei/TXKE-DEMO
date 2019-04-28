package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.Uiutils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-28
 */
class DrawableView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val drawable = ColorDrawable(Color.RED)

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawable.setBounds(Uiutils.dip2px(100f).toInt(), Uiutils.dip2px(150f).toInt(), width, height)
        drawable.draw(canvas)

    }

}