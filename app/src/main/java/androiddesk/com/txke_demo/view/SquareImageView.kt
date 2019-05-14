package androiddesk.com.txke_demo.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 *@Description:
 * @author: lll
 * @date: 2019-05-14
 */
class SquareImageView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) :
    AppCompatImageView(context, attributeSet) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = Math.max(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

//    override fun layout(l: Int, t: Int, r: Int, b: Int) {
//        val width = Math.max(r, b)
//        super.layout(0, 0, width, width)
//    }

}