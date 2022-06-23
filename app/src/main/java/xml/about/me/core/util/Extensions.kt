package xml.about.me.core.util

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager2.widget.ViewPager2
import xml.about.me.core.di.GlideApp
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.google.android.material.appbar.AppBarLayout

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatImageView.loadImageWithGlide(url: String?) {
    xml.about.me.core.di.GlideApp.with(this)
        .load(url)
        .transition(withCrossFade())
        .into(this)
}

fun AppCompatImageView.loadImageWithGlideMediumRadius(url: String?) {
    xml.about.me.core.di.GlideApp.with(this)
        .load(url)
        .apply(AppGlideExtensions.mediumRadius(context))
        .transition(withCrossFade())
        .into(this)
}

fun AppCompatImageView.loadIconWithGlideCircle(icon: Int) {
    xml.about.me.core.di.GlideApp.with(this)
        .load(icon)
        .apply(AppGlideExtensions.circle())
        .transition(withCrossFade())
        .into(this)
}

fun Activity.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.showLongToast(resourceId: Int) {
    Toast.makeText(this, resourceId, Toast.LENGTH_LONG).show()
}

fun Activity.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.showShortToast(resourceId: Int) {
    Toast.makeText(this, resourceId, Toast.LENGTH_SHORT).show()
}

fun ViewPager2.reduceDragSensitivity() {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, (touchSlop * 3.5).toInt())  // "8" was obtained experimentally
}

fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun RecyclerView.addVerticalDivider(
    context: Context,
    showFirstDivider: Boolean = false,
    showLastDivider: Boolean = true
) {
    if (itemDecorationCount == 0) {

        val divider = AppDividerItemDecoration.verticalDivider(context)

        if (showFirstDivider)
            divider.showFirstDivider()

        if (showLastDivider)
            divider.showLastDivider()

        divider.build().addTo(this)
    }
}

fun RecyclerView.addVerticalDividerSpacing4x(
    context: Context,
    showFirstDivider: Boolean = false,
    showLastDivider: Boolean = true
) {
    if (itemDecorationCount == 0) {

        val divider = AppDividerItemDecoration.verticalDividerSpacing4x(context)

        if (showFirstDivider)
            divider.showFirstDivider()

        if (showLastDivider)
            divider.showLastDivider()

        divider.build().addTo(this)
    }
}

fun RecyclerView.disableAnimationChanges() {
    (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
}

fun NestedScrollView.scrollToTop() {
    smoothScrollTo(0, 0)
}

fun RecyclerView.scrollToTop() {
    smoothScrollToPosition(0)
}

fun AppBarLayout.expand() {
    setExpanded(true, true)
}

fun AppBarLayout.collapse() {
    setExpanded(false, true)
}
