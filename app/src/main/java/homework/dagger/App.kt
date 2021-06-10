package homework.dagger

import android.content.Context
import androidx.multidex.MultiDexApplication
import homework.dagger.DI.AppComponent
import homework.dagger.DI.DaggerAppComponent
import homework.dagger.common.context.ActivityProvider

class App : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var appContext: Context
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        app = this
        ActivityProvider.init(this)
        initDaggerComponents()
    }


    fun initDaggerComponents() {
        appComponent = DaggerAppComponent.builder()
            .build()
    }
}