package com.sarnavsky.pasz.nighlight2.util

import android.app.Activity
import android.util.Log
import com.google.android.ump.ConsentForm
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import java.util.concurrent.atomic.AtomicBoolean

class GDPRHelper(private val activity: Activity) {

    private lateinit var consentInformation: ConsentInformation

    private var isMobileAdsInitializeCalled = AtomicBoolean(false)

    var consentForm: ConsentForm? = null

    fun checkGDPR( onShowAdRequest: OnShowAdRequest) {

        Log.i("GDPRTESST", "sttart")

        // Create a ConsentRequestParameters object.
        val params = ConsentRequestParameters
            .Builder()
            .build()

        consentInformation = UserMessagingPlatform.getConsentInformation(activity)
        consentInformation.requestConsentInfoUpdate(
            activity,
            params,
            {

                UserMessagingPlatform.loadConsentForm(activity,
                    {

                        consentForm = it

                        Log.i("GDPRTESST", "consentForm ${consentForm}")

                        if (consentInformation.canRequestAds()) {
                            initializeMobileAdsSdk()

                            onShowAdRequest.showAdPossible(true)
                        } else {
                            onShowAdRequest.showAdPossible(false)
                        }
                    },
                    {
                        Log.i(
                            "GDPRTESST",
                            "fail"
                        )
                    })

            },
            {
                Log.i(
                    "GDPRTESST",
                    "fail2"
                )
            })
        if (consentInformation.canRequestAds()) {
            initializeMobileAdsSdk()
        }

        //consentInformation.reset()
    }

    fun showGDPR( onShowAdRequest: OnShowAdRequest){

        Log.i("GDPRTESST", "We ${consentForm}")

        consentForm?.show(activity) {

            onShowAdRequest.showAdPossible(true)

        }
    }

    private fun initializeMobileAdsSdk() {

        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return
        }


    }

    interface OnShowAdRequest {
        fun showAdPossible(success: Boolean)
    }


}