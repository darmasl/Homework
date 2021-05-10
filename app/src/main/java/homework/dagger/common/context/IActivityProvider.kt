package homework.dagger.common.context

import android.app.Activity

interface IActivityProvider {
    val activity: Activity?
}