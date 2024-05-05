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
import org.checkerframework.checker.units.qual.A
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.Date

private const val AD_UNIT_ID = "ca-app-pub-1237459888817948/3817999426"


@Suppress("DEPRECATION")
class MainApplication : Application(), LifecycleObserver, Application.ActivityLifecycleCallbacks {

    private lateinit var appOpenAdManager: AppOpenAdManager
    private var currentActivity: Activity? = null

    private var loadTime: Long = 0

    var addIsLoaded = false

    override fun onCreate() {
        super.onCreate()

        Log.i("MAIN_APPLICATION_TEST", "onCreate()")

        startKoin {
            androidContext(this@MainApplication)
            modules(dbModule)
            modules(viewModelModule)
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        appOpenAdManager = AppOpenAdManager()

        registerActivityLifecycleCallbacks(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        currentActivity?.let { appOpenAdManager.showAd(it) }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }


    override fun onActivityStarted(activity: Activity) {
        // Updating the currentActivity only when an ad is not showing.
        if (!appOpenAdManager.isShowingAd) {
            currentActivity = activity
        }
        Log.i("GETACITVII", "currentActivity ${currentActivity}")
    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    interface OnShowAdCompleteListener {
        fun onShowAdComplete(successStatus: Boolean)
    }

    private inner class AppOpenAdManager {

        var appOpenAd: AppOpenAd? = null
        var isShowingAd = false



        @SuppressLint("SuspiciousIndentation")
        fun loadAd(activity: Activity) {

            Log.i("LOADAD","LOADAD")

            val request = AdRequest.Builder().build()


            if (addIsLoaded || isAdAvailable()) {
                return
            }

            addIsLoaded = true
            Log.i("JFNJNVJRNVRn", "currentActivity ${currentActivity}")

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
            Log.i("JFNJNVJRNVRn", "showAd")

            if (isShowingAd) {
                return
            }

            if (!isAdAvailable()) {
                    loadAd(activity)
                return
            }

            Log.i("JFNJNVJRNVRn", "appOpenAd ${appOpenAd}")

            appOpenAd?.fullScreenContentCallback =
                object : FullScreenContentCallback() {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()

                        isShowingAd = false
                        addIsLoaded = false
                            loadAd(activity)


                        Log.i(
                            "ACTIVITU_STATUS", "я повернувсся в додаток, реклама відобразилась," +
                                    " я її знову завантажую на майбутнє"
                        )


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


        Log.i("BIDNINWINDOWNDWN","here")
            isShowingAd = true

            currentActivity?.let {
                appOpenAd?.show(it)
            }

        }



        private fun isAdAvailable(): Boolean {
            return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
        }

        private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {

            Log.i("MAIN_APPLICATION_TEST", "wasLoadTimeLessThanNHoursAgo()")

            val dateDifference: Long = Date().time - loadTime
            val numMilliSecondsPerHour: Long = 3600000
            return dateDifference < numMilliSecondsPerHour * numHours
        }

    }

    fun showAdFirst(onShowAdCompleteListener: OnShowAdCompleteListener) {
        Log.i("MAIN_APPLICATION_TEST", "showAdFirst() OS")
        //appOpenAdManager.showAdFirst(onShowAdCompleteListener)
    }


    fun loadAd(activity: Activity) {

        appOpenAdManager.loadAd(activity)
    }

    fun getAdd(): Boolean {
        return appOpenAdManager.appOpenAd != null
    }

    fun showAd(activity: Activity) {
        //currentActivity = activity
        appOpenAdManager.showAd(activity)
    }
}




