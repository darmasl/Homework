package homework.dagger

import android.content.Context
import androidx.multidex.MultiDexApplication
import homework.dagger.common.context.ActivityProvider

class App : MultiDexApplication() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        ActivityProvider.init(this)
    }
}