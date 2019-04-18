package androiddesk.com.txke_demo.util

import android.content.res.Resources

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-18
 */
object Uiutils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return dpValue * scale + 0.5f
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return pxValue / scale + 0.5f
    }

    fun sp2px(spValue: Float): Float {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return spValue * fontScale + 0.5f
    }
}