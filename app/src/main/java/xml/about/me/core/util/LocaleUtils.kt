package xml.about.me.core.util

import android.content.Context
import java.util.*

fun localizedContext(baseContext: Context, locale: Locale = Locale("en")): Context {
    Locale.setDefault(locale)
    val configuration = baseContext.resources.configuration
    configuration.setLocale(locale)
    configuration.setLayoutDirection(locale)
    return baseContext.createConfigurationContext(configuration)
}
