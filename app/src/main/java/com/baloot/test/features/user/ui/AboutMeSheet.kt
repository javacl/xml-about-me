package com.baloot.test.features.user.ui

import com.baloot.test.R
import com.baloot.test.core.util.ui.BaseBottomSheetDialogFragment
import com.baloot.test.databinding.SheetAboutMeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutMeSheet : BaseBottomSheetDialogFragment<SheetAboutMeBinding>() {

    override val showKeyboard: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.sheet_about_me
}
