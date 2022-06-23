package xml.about.me.core.util

import android.content.Context
import xml.about.me.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object AppGlideExtensions {

    fun default(): RequestOptions {
        return RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    }

    fun mediumRadius(context: Context): RequestOptions {
        return RequestOptions()
            .transform(
                CenterCrop(),
                RoundedCorners(
                    context.resources.getDimensionPixelSize(R.dimen.radius_medium)
                )
            )
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    }

    fun circle(): RequestOptions {
        return RequestOptions()
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    }
}
