package com.example.affirmations

import android.app.Application
import com.example.affirmations.data.AppContainer
import com.example.affirmations.data.DefaultAppContainer

lateinit var container: AppContainer
class NasaApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }

}