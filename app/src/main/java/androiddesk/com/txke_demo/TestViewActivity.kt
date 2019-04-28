package androiddesk.com.txke_demo

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.PointF
import android.os.Bundle
import androiddesk.com.txke_demo.util.ProvinceUtil
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_test_view.*

/**
 *@Description:
 * @author: lll
 * @date: 2019-04-18
 */
class TestViewActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view)

//        view.animate()
//                .translationX(Uiutils.dip2px(150f))
//                .translationY(Uiutils.dip2px(150f))
//                .rotation(90f)
//                .setStartDelay(1000)
//                .start()
//        val animator = ObjectAnimator.ofFloat(view, "radius", Uiutils.dip2px(150f))
//        animator.startDelay = 1000
//        animator.start()

//        val topFlip = ObjectAnimator.ofFloat(view, "topFlip", -30f)
//        topFlip.duration = 1000
//        topFlip.startDelay = 200
//
//        val bottomFlip = ObjectAnimator.ofFloat(view, "bottomFlip", 30f)
//        bottomFlip.duration = 1000
//        bottomFlip.startDelay = 200
//
//        val flipRotation = ObjectAnimator.ofFloat(view, "flipRotation", 270f)
//        flipRotation.duration = 1500
//        flipRotation.startDelay = 200
//
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(bottomFlip, flipRotation, topFlip)
//        animatorSet.startDelay = 500
//        animatorSet.start()

//        val objectAnimator = ObjectAnimator.ofObject(view, "pointF", PointFEvaluator(), PointF(Uiutils.dip2px(200f), Uiutils.dip2px(300f)))
//        objectAnimator.startDelay = 1000
//        objectAnimator.duration = 1000
//        objectAnimator.start()

        val objectAnimator = ObjectAnimator.ofObject(view, "province", ProvinceUtil.ProvinceEvaluator(), "澳门特别行政区")
        objectAnimator.startDelay = 1000
        objectAnimator.duration = 5000
        objectAnimator.start()

    }

    inner class PointEvaluator : TypeEvaluator<PointF> {
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val x = startValue.x + (endValue.x - startValue.x) * fraction
            val y = startValue.y + (endValue.y - startValue.y) * fraction
            return PointF(x, y)
        }
    }

}