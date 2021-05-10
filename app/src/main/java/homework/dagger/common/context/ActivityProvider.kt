package homework.dagger.common.context

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference

class ActivityProvider private constructor(app: Application) : IActivityProvider {

    companion object {
        private var instance: ActivityProvider? = null
        fun get(): ActivityProvider = instance!!

        fun init(app: Application) {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = ActivityProvider(app)
                    }
                }
            }
        }
    }

    override val activity: Activity?
        get() = activityReference?.get()

    private var activityReference: WeakReference<Activity>? = null

    init {
        app.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity) {}

                override fun onActivityStarted(activity: Activity) {
                    this@ActivityProvider.activityReference = WeakReference(activity)
                }

                override fun onActivityDestroyed(activity: Activity) {}

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

                override fun onActivityStopped(activity: Activity) {
                    if (this@ActivityProvider.activity == activity) {
                        this@ActivityProvider.activityReference = null
                    }
                }

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

                override fun onActivityResumed(activity: Activity) {}

            }
        )
    }
}