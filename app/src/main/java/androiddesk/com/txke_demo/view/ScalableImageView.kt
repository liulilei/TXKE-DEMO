package androiddesk.com.txke_demo.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androiddesk.com.txke_demo.util.UiUtils
import androidx.core.view.GestureDetectorCompat
import kotlin.math.max
import kotlin.math.min

/**
 *@Description:
 * @author: lll
 * @date: 2019-06-25
 */
class ScalableImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defTypeAttr: Int = 0
) : View(context, attributeSet, defTypeAttr), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener,
    Runnable {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var IMAGE_WIDTH = UiUtils.dip2px(300f)

    private var bitMap = UiUtils.getAvatar(resources, IMAGE_WIDTH.toInt())

    private var initOfferX = 0f

    private var initOfferY = 0f

    private var smallScale = 0f

    private var bigScale = 0f

    private val scaleParam = 1.5f

    private var gestureDetectorCompat: GestureDetectorCompat? = null

    private var overScroller: OverScroller? = null

    private var isBig = false

    private var offerX = 0f

    private var offerY = 0f

    var scaleFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var animator: ObjectAnimator? = null

    init {
        gestureDetectorCompat = GestureDetectorCompat(context, this)
        overScroller = OverScroller(context)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initOfferX = (width - bitMap.width) / 2f
        initOfferY = (height - bitMap.height) / 2f

        if (bitMap.width * 1.0f / bitMap.height > width * 1.0f / height) {
            smallScale = width * 1.0f / bitMap.width
            bigScale = (height * 1.0f / bitMap.height) * scaleParam
        } else {
            bigScale = (width * 1.0f / bitMap.width) * scaleParam
            smallScale = height * 1.0f / bitMap.height
        }
    }

    private fun getAnimator(): ObjectAnimator? {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(this, "scaleFraction", 0f, 1f)
        }
        return animator
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(offerX * scaleFraction, offerY * scaleFraction)
        canvas?.scale(
            smallScale + (bigScale - smallScale) * scaleFraction,
            smallScale + (bigScale - smallScale) * scaleFraction,
            (width / 2).toFloat(),
            (height / 2).toFloat()
        )
        canvas?.drawBitmap(bitMap, initOfferX, initOfferY, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetectorCompat?.onTouchEvent(event)!!
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        isBig = !isBig
        if (isBig) {
            offerX = (e?.x!! - width / 2f) * (1f - bigScale / smallScale)
            offerY = (e?.y!! - height / 2f) * (1f - bigScale / smallScale)
            mathOffer()
            getAnimator()?.start()
        } else {
            getAnimator()?.reverse()
        }
        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        if (isBig) {
            overScroller?.fling(
                offerX.toInt(), offerY.toInt(), velocityX.toInt(), velocityY.toInt(),
                (-((bitMap.width * bigScale) - width) / 2f).toInt(),
                (((bitMap.width * bigScale) - width) / 2f).toInt(),
                (-((bitMap.height * bigScale) - height) / 2f).toInt(),
                (((bitMap.height * bigScale) - height) / 2f).toInt()
            )
            postOnAnimation(this)
        }
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        if (isBig) {
            offerX -= distanceX
            offerY -= distanceY
            mathOffer()
            invalidate()
        }
        return false
    }

    private fun mathOffer() {
        offerX = min(offerX, ((bitMap.width * bigScale) - width) / 2f)
        offerX = max(offerX, -((bitMap.width * bigScale) - width) / 2f)
        offerY = min(offerY, ((bitMap.height * bigScale) - height) / 2f)
        offerY = max(offerY, -((bitMap.height * bigScale) - height) / 2f)
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun run() {
        if (overScroller?.computeScrollOffset()!!) {
            offerX = overScroller?.currX?.toFloat()!!
            offerY = overScroller?.currY?.toFloat()!!
            invalidate()
            postOnAnimation(this)
        }
    }
}