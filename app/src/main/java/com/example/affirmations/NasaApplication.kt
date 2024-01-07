package com.example.affirmations

import android.app.Application
import com.example.affirmations.data.AppContainer
import com.example.affirmations.data.DefaultAppContainer

/**
 * Global container for dependency injection.
 *
 * This property is initialized in the [NasaApplication.onCreate] and then can be used across the app.
 */
lateinit var container: AppContainer

/**
 * [NasaApplication] serves as the base class for maintaining global application state.
 *
 * It initializes necessary components for the application such as [AppContainer] which provides
 * dependencies required throughout the application. The [AppContainer] is set up in [onCreate] and
 * is later used for dependency injection.
 */
class NasaApplication : Application() {

    /**
     * The container for dependencies that require a context. This is a late-initialized property
     * because it needs the context provided by the Application which is not available until
     * [onCreate] is called.
     */
    lateinit var container: AppContainer

    /**
     * Called when the application is starting, before any other application objects have been created.
     * Overriding this method is totally optional!
     *
     * Here we are initializing our [AppContainer] with the application context.
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
