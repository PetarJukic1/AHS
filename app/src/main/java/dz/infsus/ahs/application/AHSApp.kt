package dz.infsus.ahs.application

import android.app.Application
import dz.infsus.appaccess.di.appAccessModule
import dz.infsus.common.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AHSApp: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AHSApp)
            modules(
                commonModule,
                appAccessModule,
            )
        }
    }
}