package com.example.chatappfirst

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

//    override fun onCreate() {
//        super.onCreate()
//        startKoin {
//            androidContext(this@MyApplication)
//            modules(appModule)
//        }
//    }
}