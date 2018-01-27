package pl.ogiba.rolly.scenes

import pl.ogiba.rolly.R

/**
 * Created by robertogiba on 27.01.2018.
 */
class BackFragment: BaseFragment() {
    override fun provideBackgroundColor(): Int? {
        return R.color.backColor
    }

    override fun onViewTypeRequired(): OnViewActionListener.ViewType {
        return OnViewActionListener.ViewType.BACK
    }
}