package pl.ogiba.rolly

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by robertogiba on 27.01.2018.
 */
open abstract class BaseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_front, container, false)
        setupBackgroundColor(view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupBackgroundColor(view: View?) {
        if (view == null) {
            return
        }

        val newColor = provideBackgroundColor()

        if (newColor != null) {
            val color = this.resources.getColor(newColor)
            view.setBackgroundColor(color)
        }
    }

    @ColorRes
    protected abstract fun provideBackgroundColor(): Int?
}