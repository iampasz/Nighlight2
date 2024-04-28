@file:Suppress("DEPRECATION")

package com.sarnavsky.pasz.nighlight2

import android.app.Activity
import android.app.Application
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
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.Date

private const val AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921"

@Suppress("DEPRECATION")
class MainApplication : Application(), LifecycleObserver {

    private lateinit var appOpenAdManager: AppOpenAdManager
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
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        currentActivity?.let { appOpenAdManager.showAd() }
    }

    interface OnShowAdCompleteListener {
        fun onShowAdComplete(successStatus:Boolean)
    }

    private inner class AppOpenAdManager {

        var appOpenAd: AppOpenAd? = null

        fun loadAdFirst(onShowAdCompleteListener: OnShowAdCompleteListener) {


            if(addIsLoaded || isAdAvailable()){
                return
            }

            Log.i("ACTIVITU_STATUS", "loadAdFirst має спрацвати тільки один раз")

            val request = AdRequest.Builder().build()


            addIsLoaded = true
                currentActivity?.let { it ->
                    AppOpenAd.load(
                        it,
                        AD_UNIT_ID,
                        request,
                        object : AppOpenAd.AppOpenAdLoadCallback() {
                            override fun onAdLoaded(ad: AppOpenAd) {

                                addIsLoaded = false
                                appOpenAd = ad
                                loadTime = Date().time

                                //showAdFirst(onShowAdCompleteListener)

                                // showAdIfAvailable()
                                Log.i("ACTIVITU_STATUS", "Реклама завантажена")

                                onShowAdCompleteListener.onShowAdComplete(true)


                            }

                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                addIsLoaded = false
                                Log.i("ACTIVITU_STATUS", "Реклама НЕ завантажена ${loadAdError}")
                                onShowAdCompleteListener.onShowAdComplete(false)
                            }
                        }
                    )

                }




        }

        fun loadAd() {
            Log.i("ACTIVITU_STATUS", "Реклама завантажується loadAd")
            val request = AdRequest.Builder().build()

            Log.i("ADD_WAS_LOADED", "$addIsLoaded")



            if(addIsLoaded || isAdAvailable()){
                return
            }
            addIsLoaded = true
                currentActivity?.let {
                    AppOpenAd.load(
                        it,
                        AD_UNIT_ID,
                        request,
                        object : AppOpenAd.AppOpenAdLoadCallback() {
                            override fun onAdLoaded(ad: AppOpenAd) {
                                appOpenAd = ad
                                Log.i("ACTIVITU_STATUS", "Реклама завантажилась додав в appOpenAd")
                                addIsLoaded = false
                                loadTime = Date().time
                                Log.i("ADD_WAS_LOADED", "$addIsLoaded")
                            }

                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                addIsLoaded = false
                                Log.i("ACTIVITU_STATUS", "помилка не завантажилась loadAd")
                            }
                        }
                    )
                }




        }

        fun showAd() {
            Log.i("ACTIVITU_STATUS", "якщо реклама є я ї покажу при повернені в додаток")
            appOpenAd?.fullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()

                        addIsLoaded = false

                        loadAd()
                        Log.i(
                            "ACTIVITU_STATUS", "я повернувсся в додаток, реклама відобразилась," +
                                    " я її знову завантажую на майбутнє"
                        )
                    }
                }

            currentActivity?.let {
                appOpenAd?.show(it)
            }
        }

        fun showAdFirst(onShowAdCompleteListener : OnShowAdCompleteListener) {

            appOpenAd?.fullScreenContentCallback =
                object : FullScreenContentCallback() {

                    override fun onAdClicked() {
                        super.onAdClicked()
                        Log.i("FULL_SCREEEN","onAdClicked")
                        onShowAdCompleteListener.onShowAdComplete(true)

                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        Log.i("FULL_SCREEEN","onAdDismissedFullScreenContent")
                        onShowAdCompleteListener.onShowAdComplete(true)
                    }


                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        super.onAdFailedToShowFullScreenContent(p0)
                        Log.i("FULL_SCREEEN","onAdFailedToShowFullScreenContent")
                        onShowAdCompleteListener.onShowAdComplete(true)
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                        //onShowAdCompleteListener.onShowAdComplete(true)
                        addIsLoaded = false
                        loadAd()
                        Log.i("FULL_SCREEEN","onAdShowedFullScreenContent")
                    }
                }
            currentActivity?.let {
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

    fun showAdFirst( onShowAdCompleteListener : OnShowAdCompleteListener) {

        appOpenAdManager.showAdFirst(onShowAdCompleteListener)
    }

    fun loadAdFirst(activity: Activity, onShowAdCompleteListener: OnShowAdCompleteListener) {
        currentActivity = activity
        appOpenAdManager.loadAdFirst(onShowAdCompleteListener)
    }
}




