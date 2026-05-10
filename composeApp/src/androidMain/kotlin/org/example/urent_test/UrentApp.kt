package org.example.urent_test

import android.app.Application
import org.example.urent_test.core.network.di.networkModule
import org.koin.core.context.startKoin

class UrentApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    fun initKoin() {

        startKoin {
            modules(
                networkModule,
            )
        }
    }
}