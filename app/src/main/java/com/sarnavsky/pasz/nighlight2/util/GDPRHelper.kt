package com.sarnavsky.pasz.nighlight2.util

import android.app.Activity
import android.util.Log
import com.google.android.ump.ConsentForm
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import java.util.concurrent.atomic.AtomicBoolean

class GDPRHelper(private val activity:Activity) {

    private lateinit var consentInformation: ConsentInformation

    private var isMobileAdsInitializeCalled = AtomicBoolean(false)

    var consentForm: ConsentForm? = null

    fun checkGDPR(onShowAdRequest: OnShowAdRequest) {

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

                        if (consentInformation.canRequestAds()) {
                            initializeMobileAdsSdk(onShowAdRequest)

                            onShowAdRequest.showAdPossible(true)
                        } else {
                            onShowAdRequest.showAdPossible(false)
                        }
                    },
                    {
                        Log.i(
                            "KJKJKJKJJKjKJ",
                            "fail"
                        )
                    })

            },
            {

            })
        if (consentInformation.canRequestAds()) {
            initializeMobileAdsSdk(onShowAdRequest)
        }

        //consentInformation.reset()
    }

    fun showGDPR(onShowAdRequest: OnShowAdRequest){
        consentForm?.show(activity) {

            onShowAdRequest.showAdPossible(true)

        }
    }

    private fun initializeMobileAdsSdk(onShowAdRequest: OnShowAdRequest) {

        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return
        }


    }

    interface OnShowAdRequest {
        fun showAdPossible(success: Boolean)
    }


}