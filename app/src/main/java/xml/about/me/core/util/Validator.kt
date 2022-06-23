package xml.about.me.core.util

object Validator {
    //check text is mobile number
    fun isMobile(number: String): Boolean {
        return number.isNotEmpty() && number.matches("09\\d{9}".toRegex())
    }

    fun isPhoneNumber(number: String): Boolean {
        return number.matches("\\+?\\d(-|\\d)+".toRegex())
    }

    fun isUrl(url: String): Boolean {
        return android.util.Patterns.WEB_URL.matcher(url).matches();
    }

    //check text is persian
    fun isPersian(string: String): Boolean {
        val pattern =
            "^[\\s\\u0621\\u0622\\u0627\\u0623\\u0628\\u067e\\u062a\\u062b\\u062c\\u0686\\u062d\\u062e\\u062f\\u0630\\u0631\\u0632\\u0698\\u0633-\\u063a\\u0641\\u0642\\u06a9\\u06af\\u0644-\\u0646\\u0648\\u0624\\u0647\\u06cc\\u0626\\u0625\\u0671\\u0643\\u0629\\u064a\\u0649]+"
        return string.matches(pattern.toRegex())
    }
}
