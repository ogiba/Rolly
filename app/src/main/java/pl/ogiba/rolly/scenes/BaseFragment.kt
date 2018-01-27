package pl.ogiba.rolly.scenes

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.ogiba.rolly.R

/**
 * Created by robertogiba on 27.01.2018.
 */
open abstract class BaseFragment : Fragment() {
    companion object {
        const val TAG = "BaseFragment"
    }

    open var actionListener: OnViewActionListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_front, container, false)
        setupBackgroundColor(view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view?.setOnClickListener {
            Log.d(TAG, "View clicked")
            actionListener?.onViewClicked()
        }
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

    public interface OnViewActionListener {
        fun onViewClicked()
    }
}