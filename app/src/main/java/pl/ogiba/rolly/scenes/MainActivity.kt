package pl.ogiba.rolly.scenes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import pl.ogiba.rolly.R

class MainActivity : AppCompatActivity(), BaseFragment.OnViewActionListener {
    companion object {
        const val TAG = "MainActivity"
    }

    private var fragmentContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container) as FrameLayout

        setupFirstFragment()
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

        val fragment = BackFragment()
        fragment.actionListener = this
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: BaseFragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .addToBackStack("TEST")
        fragmentTransaction.commit()
    }

    private fun backItemAction() {
        Log.d(TAG, "Back clicked")
        supportFragmentManager.popBackStack()
    }
}
