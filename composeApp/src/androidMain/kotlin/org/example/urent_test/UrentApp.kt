package org.example.urent_test

import android.app.Application
import org.example.urent_test.di.initKoin
import org.koin.android.ext.koin.androidContext

class UrentApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val config = AppConfig(
            baseUrl = BuildConfig.BASE_URL
        )

        initKoin(appConfig = config) {
            androidContext(this@UrentApp)
        }
    }
}