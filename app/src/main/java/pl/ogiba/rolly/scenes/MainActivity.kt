package pl.ogiba.rolly.scenes

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import pl.ogiba.rolly.R

class MainActivity : AppCompatActivity(), BaseFragment.OnViewActionListener, View.OnClickListener {
    companion object {
        const val TAG = "MainActivity"
    }

    private var fragmentContainer: FrameLayout? = null
    private var frontView: View? = null
    private var backView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container) as FrameLayout
        frontView = findViewById(R.id.front_view)
        backView = findViewById(R.id.back_view)


        frontView?.setOnClickListener(this)
        backView?.setOnClickListener(this)
//        setupFirstFragment()
    }

    override fun onViewClicked(viewType: BaseFragment.OnViewActionListener.ViewType) {

        when (viewType) {
            BaseFragment.OnViewActionListener.ViewType.FRONT -> frontItemAction()
            BaseFragment.OnViewActionListener.ViewType.BACK -> backItemAction()
        }
    }

    private fun setupFirstFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = FrontFragment()
        fragment.actionListener = this
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun frontItemAction() {
        Log.d(TAG, "Front clicked")
//
//        val fragment = BackFragment()
//        fragment.actionListener = this
//        replaceFragment(fragment)
//
        val animator = AnimatorSet()

        val cardFlipIn = AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_in) as AnimatorSet
        val cardFlipOut = AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_out) as AnimatorSet

        cardFlipIn.setTarget(backView!!)

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {

            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationStart(p0: Animator?) {
                backView?.visibility = View.VISIBLE
            }
        })

        cardFlipOut.setTarget(frontView!!)
        animator.playTogether(cardFlipIn, cardFlipOut)
        animator.start()
    }

    private fun replaceFragment(fragment: BaseFragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .addToBackStack("TEST")
        fragmentTransaction.commit()
    }

    private fun backItemAction() {
        Log.d(TAG, "Back clicked")

        val animator = AnimatorSet()

        val cardFlipIn = AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_in) as AnimatorSet
        val cardFlipOut = AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_out) as AnimatorSet

        cardFlipIn.setTarget(frontView!!)

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                backView?.visibility = View.GONE
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationStart(p0: Animator?) {

            }
        })

        cardFlipOut.setTarget(backView!!)
        animator.playTogether(cardFlipIn, cardFlipOut)
        animator.start()
    }

    override fun onClick(view: View?) {
        if (view == null)
            return

        when (view.id) {
            R.id.front_view -> frontItemAction()
            R.id.back_view -> backItemAction()
        }
    }
}
