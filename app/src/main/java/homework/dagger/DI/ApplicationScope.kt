package homework.dagger.DI

import java.lang.annotation.Documented
import javax.inject.Scope

@Documented
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope