package androiddesk.com.txke_demo.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import androiddesk.com.txke_demo.R
import androiddesk.com.txke_demo.util.UiUtils
import androidx.appcompat.widget.AppCompatEditText

/**
 *@Description:
 * @author: lll
 * @date: 2019-05-05
 */
class MaterialEditText @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null)
    : AppCompatEditText(context, attributeSet) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val TEXT_SIZE = UiUtils.dip2px(12f)

    private val MARGIN_TOP = UiUtils.dip2px(8f)

    private val VERTICAL_OFFSET = UiUtils.dip2px(38f)

    private val HORIZONTAL_OFFSET = UiUtils.dip2px(5f)

    private val VERTICAL_OFFSET_EXTRA = UiUtils.dip2px(16f)

    private var floatLabelShown = false

    private var animator: ObjectAnimator? = null

    private var useFloatLabel = true

    private val rect = Rect()

    var floatLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {

        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialEditText)
        useFloatLabel = typedArray.getBoolean(R.styleable.MaterialEditText_use_float_label, true)
        typedArray.recycle()

        paint.textSize = TEXT_SIZE
        background.getPadding(rect)
        if (useFloatLabel) {
            setPadding(rect.left, (rect.top + TEXT_SIZE + MARGIN_TOP).toInt(), rect.right, rect.bottom)
        } else {
            setPadding(rect.left, rect.top, rect.right, rect.bottom)
        }
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (useFloatLabel && floatLabelShown && TextUtils.isEmpty(s)) {
                    floatLabelShown = !floatLabelShown
                    getObjectAnimator()?.reverse()
                } else if (useFloatLabel && !floatLabelShown && !TextUtils.isEmpty(s)) {
                    floatLabelShown = !floatLabelShown
                    getObjectAnimator()?.start()
                }
            }

        })
    }

    fun setUseFloatLabel(useFloatLabel: Boolean) {
        if (this.useFloatLabel != useFloatLabel) {
            this.useFloatLabel = useFloatLabel
            if (useFloatLabel) {
                setPadding(rect.left, (rect.top + TEXT_SIZE + MARGIN_TOP).toInt(), rect.right, rect.bottom)
            } else {
                setPadding(rect.left, rect.top, rect.right, rect.bottom)
            }
        }
    }

    private fun getObjectAnimator(): ObjectAnimator? {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(this@MaterialEditText, "floatLabelFraction", 0f, 1f)
        }
        return animator
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.alpha = (0xff * floatLabelFraction).toInt()
        canvas?.drawText(hint.toString(), HORIZONTAL_OFFSET, VERTICAL_OFFSET - VERTICAL_OFFSET_EXTRA * floatLabelFraction, paint)
    }

}