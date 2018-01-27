package pl.ogiba.rolly.scenes

import pl.ogiba.rolly.R

/**
 * Created by robertogiba on 27.01.2018.
 */
class FrontFragment : BaseFragment() {

    override fun provideBackgroundColor(): Int {
        return R.color.frontColor
    }

    override fun onViewTypeRequired(): OnViewActionListener.ViewType {
        return OnViewActionListener.ViewType.FRONT
    }
}