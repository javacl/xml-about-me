package xml.about.me.features.user.ui

import xml.about.me.R
import xml.about.me.core.util.ui.BaseBottomSheetDialogFragment
import xml.about.me.databinding.SheetAboutMeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutMeSheet : BaseBottomSheetDialogFragment<SheetAboutMeBinding>() {

    override val showKeyboard: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.sheet_about_me
}
