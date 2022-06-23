package xml.about.me.core.util

fun convertTimeToMilliSecond(time: Long): Long {
    return time * 1000L
}

fun convertTimeToSecond(time: Long): Long {
    return time / 1000L
}
