package androiddesk.com.txke_demo

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
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

        val topFlip = ObjectAnimator.ofFloat(view, "topFlip", -30f)
        topFlip.duration = 1000
        topFlip.startDelay = 200

        val bottomFlip = ObjectAnimator.ofFloat(view, "bottomFlip", 30f)
        bottomFlip.duration = 1000
        bottomFlip.startDelay = 200

        val flipRotation = ObjectAnimator.ofFloat(view, "flipRotation", 270f)
        flipRotation.duration = 1500
        flipRotation.startDelay = 200

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(bottomFlip, flipRotation, topFlip)
        animatorSet.startDelay = 500
        animatorSet.start()

    }

}