package pl.ogiba.rolly

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {

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
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
