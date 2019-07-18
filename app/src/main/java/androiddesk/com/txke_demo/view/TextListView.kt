package androiddesk.com.txke_demo.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.R
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

/**
 *@Description:
 * @author: lll
 * @date: 2019-07-18
 */
class TextListView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attributeSet, defStyleAttr) {

    private var listText = arrayListOf<String>()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var textSize = dip2px(14f)

    private var pointTextMargin = dip2px(5f)

    private var lineSpacingExtra = dip2px(2f)

    private var heightSpace = dip2px(5f)

    private var textColor = Color.parseColor("#231f20")

    private val metrics = Paint.FontMetrics()

    private val measuredWidth = floatArrayOf()

    private var offest = 0f

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TextListView)
        textSize = typedArray.getDimension(R.styleable.TextListView_text_size, dip2px(14f))
        pointTextMargin = typedArray.getDimension(R.styleable.TextListView_point_text_margin, dip2px(5f))
        lineSpacingExtra = typedArray.getDimension(R.styleable.TextListView_line_spacing_extra, dip2px(2f))
        heightSpace = typedArray.getDimension(R.styleable.TextListView_height_space, dip2px(5f))
        textColor = typedArray.getColor(R.styleable.TextListView_text_color, Color.parseColor("#231f20"))
        paint.color = textColor
        paint.textSize = textSize
        paint.getFontMetrics(metrics)
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        var heightMode = MeasureSpec.getMode(heightMeasureSpec)
//        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
//
//        var height: Int
//
//        //高度跟宽度处理方式一样
//        height = when (heightMode) {
//            MeasureSpec.EXACTLY -> heightSize
//            MeasureSpec.AT_MOST -> {
//                (offest + paddingBottom).toInt()
//            }
//            else -> getHeight()
//        }
//
//        //保存测量宽度和测量高度
//        setMeasuredDimension(width, height)
//
//    }

    override fun onDraw(canvas: Canvas?) {
        offest = paint.fontSpacing
        listText.forEach {
            var start = 0
            val length = it.length
            var count: Int
            canvas?.drawText("·", 0f + paddingLeft + marginLeft, offest + paddingTop + marginTop, paint)
            while (start < length) {
                val usable = width.toFloat() - pointTextMargin - paddingLeft - paddingRight - marginLeft - marginRight
                count = paint.breakText(it, start, length, true, usable, measuredWidth)
                canvas?.drawText(it, start, start + count, pointTextMargin + paddingLeft + marginLeft, offest + paddingTop + marginTop, paint)
                start += count
                offest += paint.fontSpacing + lineSpacingExtra
            }
            offest += heightSpace
        }
    }

    fun setTextList(list: List<String>) {
        listText.clear()
        listText.addAll(list)
        invalidate()
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return dpValue * scale + 0.5f
    }

}