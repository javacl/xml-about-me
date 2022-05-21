package com.about.me.features.user.ui

import com.about.me.R
import com.about.me.core.util.ui.BaseBottomSheetDialogFragment
import com.about.me.databinding.SheetAboutMeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutMeSheet : BaseBottomSheetDialogFragment<SheetAboutMeBinding>() {

    override val showKeyboard: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.sheet_about_me
}
