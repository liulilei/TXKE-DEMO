package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup

/**
 *@Description:
 * @author: lll
 * @date: 2019-05-15
 */
class TagLayout @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0) :
    ViewGroup(context, attributeSet, defTypeAttr) {

    private val rectList = arrayListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var widthLine = 0
        var heightLine = 0
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if (widthMode != MeasureSpec.UNSPECIFIED && (widthLine + childView.measuredWidth) > widthSize) {
                widthLine = 0
                heightLine = heightUsed
            }
            var rect: Rect
            if (i >= rectList.size) {
                rect = Rect()
                rectList.add(rect)
            } else {
                rect = rectList[i]
            }
            rect.set(widthLine, heightLine, widthLine + childView.measuredWidth, heightLine + childView.measuredHeight)
            widthLine += childView.measuredWidth
            widthUsed = Math.max(widthLine, widthUsed)
            heightUsed = Math.max(heightLine + childView.measuredHeight, heightUsed)
        }
        setMeasuredDimension(widthUsed, heightUsed)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val rect = rectList[i]
            child.layout(rect.left, rect.top, rect.right, rect.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}