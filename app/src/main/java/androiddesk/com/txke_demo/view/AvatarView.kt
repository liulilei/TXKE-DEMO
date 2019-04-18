package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.R
import androiddesk.com.txke_demo.util.Uiutils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-18
 */
class AvatarView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val padding = Uiutils.dip2px(40f)

    private val length = Uiutils.dip2px(10f)

    private val width = Uiutils.dip2px(200f)

    private val rectF = RectF()

    private val big = RectF()

    private var avatar: Bitmap? = null

    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    init {
        avatar = getAvatar(width.toInt())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(padding, padding, padding + width, padding + width)
        big.set(padding - length, padding - length, padding + width + length, padding + width + length)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawOval(big, paint)
        val saveLayer = canvas?.saveLayer(rectF, paint)
        canvas?.drawOval(rectF, paint)
        paint.xfermode = xfermode
        canvas?.drawBitmap(avatar, padding, padding, paint)
        paint.xfermode = null
        if (saveLayer != null) {
            canvas?.restoreToCount(saveLayer)
        }
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.mipmap.abc, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.mipmap.abc, options)
    }
}