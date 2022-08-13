package com.example.chatappfirst

import android.app.Application

import dagger.hilt.android.HiltAndroidApp

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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