@file:Suppress("DEPRECATION")

package com.sarnavsky.pasz.nighlight2

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.sarnavsky.pasz.nighlight2.di.dbModule
import com.sarnavsky.pasz.nighlight2.di.viewModelModule
import com.sarnavsky.pasz.nighlight2.util.GDPRHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.Date

private const val AD_UNIT_ID = "ca-app-pub-1237459888817948/3817999426"


@Suppress("DEPRECATION")
class MainApplication : Application(), LifecycleObserver, Application.ActivityLifecycleCallbacks {

    private var appOpenAdManager: AppOpenAdManager? = null
    private var currentActivity: Activity? = null
    private var loadTime: Long = 0
    var addIsLoaded = false


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(dbModule)
            modules(viewModelModule)
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        appOpenAdManager = AppOpenAdManager()

        registerActivityLifecycleCallbacks(this)


    }

    fun checkGDPR(activity: Activity) {
        val gdpr = GDPRHelper(activity)
        gdpr.checkGDPR(object : GDPRHelper.OnShowAdRequest {
            override fun showAdPossible(success: Boolean) {

                if (success) {
                    Log.i("GDPRTESST", "We got answer, you can show the ads")
                } else {
                    Log.i(
                        "GDPRTESST", "We got answer, you can't show the ads." +
                                " But you acsepted menu is loaded, you can show it"
                    )

                    gdpr.showGDPR(object : GDPRHelper.OnShowAdRequest {
                        override fun showAdPossible(success: Boolean) {

                        }
                    })
                }

            }

        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        Log.i("FJRNVJNRNVRNVR", "onMoveToForeground")
        currentActivity?.let { appOpenAdManager?.showAd(it) }

    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}


    override fun onActivityStarted(activity: Activity) {
        // Updating the currentActivity only when an ad is not showing.
        appOpenAdManager?.let {
            if (!it.isShowingAd) {
                currentActivity = activity
            }
        }

    }

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}

    private inner class AppOpenAdManager {

        var appOpenAd: AppOpenAd? = null
        var isShowingAd = false


        @SuppressLint("SuspiciousIndentation")
        fun loadAd(activity: Activity) {

            val request = AdRequest.Builder().build()
            if (addIsLoaded || isAdAvailable()) {
                return
            }

            addIsLoaded = true

            AppOpenAd.load(
                activity,
                AD_UNIT_ID,
                request,
                object : AppOpenAd.AppOpenAdLoadCallback() {
                    override fun onAdLoaded(ad: AppOpenAd) {
                        appOpenAd = ad
                        addIsLoaded = false
                        loadTime = Date().time
                        Log.i("JFNJNVJRNVRn", "Реклама завантажена")

                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        addIsLoaded = false
                        Log.i("JFNJNVJRNVRn", "Реклама НЕ завантажена ${loadAdError}")

                    }
                }
            )


        }

        fun showAd(activity: Activity) {
            if (isShowingAd) {
                return
            }

            if (!isAdAvailable()) {
                loadAd(activity)
                return
            }


            appOpenAd?.fullScreenContentCallback =
                object : FullScreenContentCallback() {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()

                        // isShowingAd = false
                        addIsLoaded = false
                        loadAd(activity)
                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        appOpenAd = null
                        isShowingAd = false
                        currentActivity?.let {
                            loadAd(activity)
                        }
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        super.onAdFailedToShowFullScreenContent(p0)
                        appOpenAd = null
                        isShowingAd = false
                        currentActivity?.let {
                            loadAd(activity)
                        }
                    }
                }

            isShowingAd = true

            currentActivity?.let {
                Log.i("FJRNVJNRNVRNVR", "ПОКАЗУЮ ЯКЩО Є")
                appOpenAd?.show(it)
            }

        }

        private fun isAdAvailable(): Boolean {
            return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
        }

        private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {

            val dateDifference: Long = Date().time - loadTime
            val numMilliSecondsPerHour: Long = 3600000
            return dateDifference < numMilliSecondsPerHour * numHours
        }

    }

    fun loadAd(activity: Activity) {
        appOpenAdManager?.loadAd(activity)
    }

    fun getAdd(): Boolean {
        return appOpenAdManager?.appOpenAd != null
    }

    fun showAd(activity: Activity) {
        appOpenAdManager?.showAd(activity)
    }
}




