package xml.about.me.core.util.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import xml.about.me.core.util.localizedContext

abstract class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(localizedContext(context))
    }

    override fun onStart() {
        super.onStart()
        localizedContext(this)
    }
}
