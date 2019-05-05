package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.util.UiUtils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-23
 */
class CameraView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val padding = UiUtils.dip2px(100f)

    private val width = UiUtils.dip2px(200f)

    private val camera = Camera()

    private var avatar: Bitmap? = null

    init {
        avatar = UiUtils.getAvatar(resources, width.toInt())
        camera.rotateX(45f)
        camera.setLocation(0f, 0f, UiUtils.getZLocation())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.clipRect(0f, 0f, padding + width / 2, padding + width / 2)
//        canvas?.rotate(45f, padding + width /  2, padding + width / 2)
//        canvas?.translate(padding, padding)

        canvas?.save()
        canvas?.translate(padding + width / 2, padding + width / 2)
        canvas?.rotate(-30f)
        canvas?.clipRect(-width, -width, width, 0f)
        canvas?.rotate(30f)
        canvas?.translate(-(padding + width / 2), -(padding + width / 2))
        canvas?.drawBitmap(avatar, padding, padding, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(padding + width / 2, padding + width / 2)
        canvas?.rotate(-30f)
        camera.applyToCanvas(canvas)
        canvas?.clipRect(-width, 0f, width, width)
        canvas?.rotate(30f)
        canvas?.translate(-(padding + width / 2), -(padding + width / 2))
        canvas?.drawBitmap(avatar, padding, padding, paint)
        canvas?.restore()
    }
}