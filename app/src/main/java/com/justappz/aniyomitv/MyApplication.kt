package com.justappz.aniyomitv

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.justappz.aniyomitv.constants.LogKeys
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() , Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        // Register this class as the lifecycle callback
        registerActivityLifecycleCallbacks(this)
    }

    //region Activity Lifecycle Callbacks
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.d(LogKeys.ACTIVITY_LIFECYCLE, "${activity.localClassName} created")
        registerFragmentCallbacksIfPossible(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        Log.d(LogKeys.ACTIVITY_LIFECYCLE, "${activity.localClassName} started")
    }

    override fun onActivityResumed(activity: Activity) {
        Log.d(LogKeys.ACTIVITY_LIFECYCLE, "${activity.localClassName} resumed")
    }

    override fun onActivityPaused(activity: Activity) {
        Log.d(LogKeys.ACTIVITY_LIFECYCLE, "${activity.localClassName} paused")
    }

    override fun onActivityStopped(activity: Activity) {
        Log.d(LogKeys.ACTIVITY_LIFECYCLE, "${activity.localClassName} stopped")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.d(LogKeys.ACTIVITY_LIFECYCLE, "${activity.localClassName} saveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Log.d(LogKeys.ACTIVITY_LIFECYCLE, "${activity.localClassName} destroyed")
    }
    //endregion

    //region Fragment Lifecycle Callbacks
    private fun registerFragmentCallbacksIfPossible(activity: Activity) {
        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        savedInstanceState: Bundle?
                    ) {
                        Log.d(
                            LogKeys.ACTIVITY_LIFECYCLE,
                            "${f::class.java.simpleName} fragment created in ${activity.localClassName}"
                        )
                    }

                    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                        Log.d(
                            LogKeys.ACTIVITY_LIFECYCLE,
                            "${f::class.java.simpleName} fragment resumed in ${activity.localClassName}"
                        )
                    }

                    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                        Log.d(
                            LogKeys.ACTIVITY_LIFECYCLE,
                            "${f::class.java.simpleName} fragment paused in ${activity.localClassName}"
                        )
                    }

                    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                        Log.d(
                            LogKeys.ACTIVITY_LIFECYCLE,
                            "${f::class.java.simpleName} fragment destroyed in ${activity.localClassName}"
                        )
                    }
                }, true
            )
        }
    }
    //endregion

}