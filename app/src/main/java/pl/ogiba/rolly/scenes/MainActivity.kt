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

    private fun setupFirstFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = FrontFragment()
        fragment.actionListener = this
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onViewClicked(viewType: BaseFragment.OnViewActionListener.ViewType) {

        when(viewType) {
            BaseFragment.OnViewActionListener.ViewType.FRONT -> Log.d(TAG, "Front clicked")
            BaseFragment.OnViewActionListener.ViewType.BACK -> Log.d(TAG, "Back clicked")
        }
    }
}
