//package com.sarnavsky.pasz.nighlight2
//
//import android.content.Context
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.LoadAdError
//import com.google.android.gms.ads.interstitial.InterstitialAd
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
//import com.google.android.gms.ads.rewarded.RewardedAd
//import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
//
//class AdHelper {
//
//    private fun loadInterstitialAd(context: Context, adRequest: AdRequest) {
//        InterstitialAd.load(context,
//            StringTypes.ADS_INTERSTITIAL.type,
//            adRequest,
//            object : InterstitialAdLoadCallback() {
//                override fun onAdLoaded(interstitialAd: InterstitialAd) {
//
//                }
//
//                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
//
//                }
//            })
//    }
//
//    private fun loadRewardAd(context: Context, adRequest: AdRequest) {
//        RewardedAd.load(context,
//            StringTypes.ADS_REWARDED.type,
//            adRequest,
//            object : RewardedAdLoadCallback() {
//                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
//
//                }
//
//                override fun onAdLoaded(ad: RewardedAd) {
//                    super.onAdLoaded(ad)
//
//                }
//            })
//
//    }
//
//}