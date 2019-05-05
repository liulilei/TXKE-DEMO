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
 * @date: 2019-04-24
 */
class CameraAnimView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val WIDTH = UiUtils.dip2px(200f)

    private val padding = UiUtils.dip2px(100f)

    private val camera = Camera()

    private var bitmap: Bitmap? = null

    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }

    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }

    var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        bitmap = UiUtils.getAvatar(resources, WIDTH.toInt())
        camera.setLocation(0f, 0f, UiUtils.getZLocation())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        
        canvas?.apply {
            save()
            translate(padding + WIDTH / 2, padding + WIDTH / 2)
            rotate(-flipRotation)
            camera.save()
            camera.rotateX(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            clipRect(-WIDTH, -WIDTH, WIDTH, 0f)
            rotate(flipRotation)
            translate(-(padding + WIDTH / 2), -(padding + WIDTH / 2))
            drawBitmap(bitmap, padding, padding, paint)
            restore()

            save()
            translate(padding + WIDTH / 2, padding + WIDTH / 2)
            rotate(-flipRotation)
            camera.save()
            camera.rotateX(bottomFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            clipRect(-WIDTH, 0f, WIDTH, WIDTH)
            rotate(flipRotation)
            translate(-(padding + WIDTH / 2), -(padding + WIDTH / 2))
            drawBitmap(bitmap, padding, padding, paint)
            restore()
        }
       
    }
}