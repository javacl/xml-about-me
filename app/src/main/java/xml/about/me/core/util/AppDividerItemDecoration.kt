package xml.about.me.core.util

import android.content.Context
import androidx.core.content.ContextCompat
import xml.about.me.R
import com.fondesa.recyclerviewdivider.DividerBuilder
import com.fondesa.recyclerviewdivider.dividerBuilder

object AppDividerItemDecoration {

    fun verticalDivider(context: Context): DividerBuilder {
        val builder = context.dividerBuilder()
        ContextCompat.getDrawable(context, R.drawable.divider_vertical)?.let {
            builder.drawable(it)
        }
        return builder
    }

    fun verticalDividerSpacing4x(context: Context): DividerBuilder {
        val builder = context.dividerBuilder()
        ContextCompat.getDrawable(context, R.drawable.divider_vertical_spacing_4x)?.let {
            builder.drawable(it)
        }
        return builder
    }
}
