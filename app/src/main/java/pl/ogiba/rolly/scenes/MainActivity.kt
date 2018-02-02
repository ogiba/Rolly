package pl.ogiba.rolly.scenes

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
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
        const val DEFAULT_DISTANCE = 8000
    }

    private var fragmentContainer: FrameLayout? = null
    private var frontView: View? = null
    private var backView: View? = null
    private var flipRightBtn: View? = null
    private var flipLeftBtn: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
//        setupViews()
        setupFirstFragment()
    }

    override fun onViewClicked(viewType: BaseFragment.OnViewActionListener.ViewType) {

        when (viewType) {
            BaseFragment.OnViewActionListener.ViewType.FRONT -> frontItemAction()
            BaseFragment.OnViewActionListener.ViewType.BACK -> backItemAction()
        }
    }

    private fun bindViews() {
        fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container) as FrameLayout
        frontView = findViewById(R.id.front_view)
        backView = findViewById(R.id.back_view)
        flipLeftBtn = findViewById(R.id.btn_flip_left)
        flipRightBtn = findViewById(R.id.btn_flip_right)

        flipLeftBtn?.setOnClickListener {
            frontItemAction()
        }

        flipRightBtn?.setOnClickListener {
            backItemAction()
        }
    }

    private fun setupFirstFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = FrontFragment()
        fragment.actionListener = this
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun setupViews() {
        frontView?.setOnClickListener(this)
        backView?.setOnClickListener(this)

        val density = resources.displayMetrics.density

        frontView?.cameraDistance = density * DEFAULT_DISTANCE
        backView?.cameraDistance = density * DEFAULT_DISTANCE
    }

    private fun frontItemAction() {
        Log.d(TAG, "Front clicked")
//
        val fragment = BackFragment()
        fragment.actionListener = this
        replaceFragment(fragment)
//
//        val animator = AnimatorSet()
//
//        val cardFlipIn = AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_in) as AnimatorSet
//        val cardFlipOut = AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_out) as AnimatorSet
//
//        cardFlipIn.setTarget(backView!!)
//
//        animator.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationStart(animation: Animator?) {
//                super.onAnimationStart(animation)
//
//                backView?.visibility = View.VISIBLE
//            }
//
//            override fun onAnimationEnd(animation: Animator?) {
//                super.onAnimationEnd(animation)
//
//                frontView?.visibility = View.INVISIBLE
//            }
//        })
//
//        cardFlipOut.setTarget(frontView!!)
//        animator.playTogether(cardFlipIn, cardFlipOut)
//        animator.start()
    }

    private fun replaceFragment(fragment: BaseFragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction?.setCustomAnimations(R.animator.card_flip_left_in, R.animator.card_flip_left_out)
        fragmentTransaction.replace(R.id.fragment_container, fragment, "TEST")
//                .addToBackStack("TEST")
        fragmentTransaction.commit()
    }

    private fun backItemAction() {
//        Log.d(TAG, "Back clicked")
//
//        val animator = AnimatorSet()
//
//        val cardFlipIn = AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_in) as AnimatorSet
//        val cardFlipOut = AnimatorInflater.loadAnimator(this, R.animator.card_flip_left_out) as AnimatorSet
//
//        cardFlipIn.setTarget(frontView!!)
//
//        animator.addListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationStart(animation: Animator?) {
//                super.onAnimationStart(animation)
//
//                frontView?.visibility = View.VISIBLE
//            }
//
//            override fun onAnimationEnd(animation: Animator?) {
//                super.onAnimationEnd(animation)
//
//                backView?.visibility = View.INVISIBLE
//            }
//        })
//
//        cardFlipOut.setTarget(backView!!)
//        animator.playTogether(cardFlipIn, cardFlipOut)
//        animator.start()

        val fragment = FrontFragment()
        fragment.actionListener = this
        replaceFragment(fragment)
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
