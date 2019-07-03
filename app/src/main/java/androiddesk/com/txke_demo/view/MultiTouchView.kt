package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
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

    private var startX = 0f

    private var startY = 0f

    private var offsetX = 0f

    private var offsetY = 0f

    private var endOffsetX = 0f

    private var endOffestY = 0f

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val IMAGE_WIDTH = UiUtils.dip2px(200f)

    private var bitmap = UiUtils.getAvatar(resources, IMAGE_WIDTH.toInt())

    private var indexPointerId = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                indexPointerId = event.getPointerId(0)
                startX = event.getX(0)
                startY = event.getY(0)
                endOffsetX = offsetX
                endOffestY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                var currentIndex = event.findPointerIndex(indexPointerId)
                offsetX = event.getX(currentIndex) - startX + endOffsetX
                offsetY = event.getY(currentIndex) - startY + endOffestY
                invalidate()
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                indexPointerId = event.getPointerId(event.actionIndex)
                startX = event.getX(event.actionIndex)
                startY = event.getY(event.actionIndex)
                endOffsetX = offsetX
                endOffestY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                if (event.getPointerId(actionIndex) == indexPointerId) {
                    var index = if (event.pointerCount - 1 == actionIndex) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    indexPointerId = event.getPointerId(index)
                    startX = event.getX(index)
                    startY = event.getY(index)
                    endOffsetX = offsetX
                    endOffestY = offsetY
                }
            }
        }
        return true
    }
}