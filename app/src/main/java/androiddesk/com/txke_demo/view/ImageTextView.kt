package androiddesk.com.txke_demo.view

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androiddesk.com.txke_demo.R
import androiddesk.com.txke_demo.util.UiUtils

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-19
 */
class ImageTextView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defTypeAttr: Int = 0)
    : View(context, attributeSet, defTypeAttr) {

    private val text = "很多年之后，我有个绰号叫西毒，任何人都可以变得狠毒，只要你尝试过什么叫做嫉妒。我不介意其他人怎么看我，我只不过不想别人比我更开心。我以为有一些人永远都不会嫉妒，因为他太骄傲。在我出道的时候，我认识了一个人，因为他喜欢在东边出没，所以很多年后，他有个绰号叫东邪。 知不知道饮酒和饮水有什么区别？酒越饮越暖，水越喝越寒。 你越想忘记一个人时，其实你越会记得他。 人的烦恼就是记性太好，如果可以把所有事都忘掉，以后每一日都是个新开始，你说多好。 每个人都会经过这个阶段，见到一座山，就想知道山后面是什么。我很想告诉他，可能翻过山后面，你会发现没什么特别。回望之下，可能会觉得这一边更好。 每个人都会坚持自己的信念，在别人看来，是浪费时间，她却觉得很重要。"

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    private val width = UiUtils.dip2px(200f)
    private val padding = UiUtils.dip2px(80f)

    private var bitmap: Bitmap? = null

    private val measuredWidth = floatArrayOf()

    private val rect = Rect()

    private val metrics = Paint.FontMetrics()

    init {
        paint.textSize = UiUtils.dip2px(20f)
        textPaint.textSize = UiUtils.dip2px(20f)
        bitmap = getAvatar(width.toInt())
        paint.getFontMetrics(metrics)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, getWidth() - width, padding, paint)
//        StaticLayout(text, textPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false).draw(canvas)
        var start = 0
        val length = text.length
        var count: Int
        var offest = paint.fontSpacing
        while (start < length) {
            val usable = if (metrics.top + offest > padding && metrics.top + offest < padding + width
                    || metrics.bottom + offest > padding && metrics.bottom + offest < padding + width) {
                getWidth() - width
            } else {
                getWidth().toFloat()
            }
            count = paint.breakText(text, start, length, true, usable, measuredWidth)
            canvas?.drawText(text, start, start + count, 0f, offest, paint)
            start += count
            offest += paint.fontSpacing
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