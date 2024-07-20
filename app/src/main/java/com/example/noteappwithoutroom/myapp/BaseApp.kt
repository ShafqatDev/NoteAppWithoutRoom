package com.example.noteappwithoutroom.myapp

import android.app.Application
import com.example.noteappwithoutroom.di.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(applicationContext)
            modules(moduleList)
        }
    }
}