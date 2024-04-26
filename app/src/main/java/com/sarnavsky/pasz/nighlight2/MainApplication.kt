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

private const val AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921"

@Suppress("DEPRECATION")
class MainApplication : Application(), LifecycleObserver {

    private lateinit var appOpenAdManager: AppOpenAdManager
    private var currentActivity: Activity? = null

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
        fun onShowAdComplete()
    }

    private inner class AppOpenAdManager {

        var appOpenAd: AppOpenAd? = null

        fun loadAdFirst(onShowAdCompleteListener: OnShowAdCompleteListener) {

            Log.i("ACTIVITU_STATUS", "loadAdFirst має спрацвати тільки один раз")

            val request = AdRequest.Builder().build()
            currentActivity?.let { it ->
                AppOpenAd.load(
                    it,
                    AD_UNIT_ID,
                    request,
                    object : AppOpenAd.AppOpenAdLoadCallback() {
                        override fun onAdLoaded(ad: AppOpenAd) {

                            // appOpenAd = ad

                            // showAdIfAvailable()
                            Log.i("ACTIVITU_STATUS", "Реклама завантажена")

                            onShowAdCompleteListener.onShowAdComplete()

                            ad.fullScreenContentCallback =
                                object : FullScreenContentCallback() {

                                    override fun onAdClicked() {
                                        super.onAdClicked()
                                        Log.i("FULL_SCREEEN","onAdClicked")
                                        onShowAdCompleteListener.onShowAdComplete()

                                    }

                                    override fun onAdDismissedFullScreenContent() {
                                        super.onAdDismissedFullScreenContent()
                                        Log.i("FULL_SCREEEN","onAdDismissedFullScreenContent")

                                    }


                                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                                        super.onAdFailedToShowFullScreenContent(p0)
                                        Log.i("FULL_SCREEEN","onAdFailedToShowFullScreenContent")
                                    }

                                    override fun onAdShowedFullScreenContent() {
                                        super.onAdShowedFullScreenContent()

                                        loadAd()
                                        Log.i("FULL_SCREEEN","onAdShowedFullScreenContent")
                                    }
                                }
                            ad.show(it)

                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            Log.i("ACTIVITU_STATUS", "Реклама НЕ завантажена ${loadAdError}")
                            onShowAdCompleteListener.onShowAdComplete()
                        }
                    }
                )

            }


        }

        fun loadAd() {
            Log.i("ACTIVITU_STATUS", "Реклама завантажується loadAd")
            val request = AdRequest.Builder().build()
            currentActivity?.let {
                AppOpenAd.load(
                    it,
                    AD_UNIT_ID,
                    request,
                    object : AppOpenAd.AppOpenAdLoadCallback() {
                        override fun onAdLoaded(ad: AppOpenAd) {
                            appOpenAd = ad
                            Log.i("ACTIVITU_STATUS", "Реклама завантажилась додав в appOpenAd")
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
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
    }

    fun loadAdFirst(activity: Activity, onShowAdCompleteListener: OnShowAdCompleteListener) {
        currentActivity = activity
        appOpenAdManager.loadAdFirst(onShowAdCompleteListener)
    }
}




