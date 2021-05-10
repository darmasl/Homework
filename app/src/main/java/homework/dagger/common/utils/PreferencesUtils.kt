package homework.dagger.common.utils

import android.content.Context
import homework.dagger.BuildConfig
import java.util.*

class PreferencesUtils(private val context: Context) {
    companion object {
        private const val PREFERENCES_KEY = BuildConfig.APPLICATION_ID
    }

    fun setDate(key: String, date: Date) =
        with(context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE).edit()) {
            putLong(key, date.time)
            commit()
        }

    fun getDate(key: String): Date? =
        with(context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)) {
            val time = getLong(key, -1)
            return@with if (time < 0) {
                null
            } else {
                Date(time)
            }
        }
}