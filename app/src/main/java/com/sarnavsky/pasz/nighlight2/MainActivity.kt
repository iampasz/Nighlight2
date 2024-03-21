package com.sarnavsky.pasz.nighlight2

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentForm
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentInformation.OnConsentInfoUpdateFailureListener
import com.google.android.ump.ConsentInformation.OnConsentInfoUpdateSuccessListener
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import com.sarnavsky.pasz.nighlight2.Fragments.MainFragment
import com.sarnavsky.pasz.nighlight2.Interfaces.MyCallback
import com.sarnavsky.pasz.nighlight2.databinding.MainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainBinding
    private var consentForm: ConsentForm? = null

    var mInterstitialAd: InterstitialAd? = null
    private var adRequest: AdRequest? = null
    private var rewardedAd: RewardedAd? = null

    private var oldLinkId = 0
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        //My ADMOB
        adRequest = AdRequest.Builder().build()
        loadADS()
        loadAdRequest()

        isFirstOpen()
        saveSettings(-1)

        //loadMainFragment
        val fm = supportFragmentManager
        val mainFragment = MainFragment()
        fm.beginTransaction().replace(R.id.container, mainFragment, "main_fragment").commit()

        //showGDPR
        showGDPR()

    }

    private var consentInformation: ConsentInformation? = null

    private fun showGDPR() {
        val debugSettings = ConsentDebugSettings.Builder(this)
            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
            //.addTestDeviceHashedId(StringTypes.GDPR_TEST_DEVICE_1.type)
            //.addTestDeviceHashedId(StringTypes.GDPR_TEST_DEVICE_2.type)
            .build()
        val params = ConsentRequestParameters.Builder()
            .setConsentDebugSettings(debugSettings)
            .setTagForUnderAgeOfConsent(false)
            .build()
        val inform = OnConsentInfoUpdateSuccessListener { loadForm() }
        val infoField = OnConsentInfoUpdateFailureListener {
        }
        consentInformation = UserMessagingPlatform.getConsentInformation(this)
        consentInformation?.requestConsentInfoUpdate(this@MainActivity, params, inform, infoField)

        // consentInformation.reset();
    }

    private fun loadForm() {
        // Loads a consent form. Must be called on the main thread.
        UserMessagingPlatform.loadConsentForm(
            this,
            { consentForm ->
                this@MainActivity.consentForm = consentForm
                if (consentInformation?.consentStatus == ConsentInformation.ConsentStatus.REQUIRED) {
                    consentForm.show(
                        this@MainActivity
                    ) {

                        // Handle dismissal by reloading form.
                        loadForm()
                    }
                }
            }
        ) {
        }
    }

    private fun loadADS() {

        adRequest?.let {
            InterstitialAd.load(this,
                StringTypes.ADS_INTERSTITIAL.type,
                it,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd
                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        mInterstitialAd = null
                    }
                })
        }
    }

    private fun loadAdRequest() {
        adRequest?.let {
            RewardedAd.load(this,
                StringTypes.ADS_REWARDED.type,
                it,
                object : RewardedAdLoadCallback() {
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        rewardedAd = null
                    }

                    override fun onAdLoaded(ad: RewardedAd) {
                        rewardedAd = ad
                        super.onAdLoaded(ad)
                    }
                })
        }
    }

    private fun isFirstOpen() {
        val sp = getSharedPreferences(
            MY_SETTINGS,
            MODE_PRIVATE
        )

        val hasVisited = sp.getBoolean("hasVisited", false)
        if (!hasVisited) {
            val e = sp.edit()
            e.putBoolean("hasVisited", true)
            e.apply()
        }
    }

    private fun saveSettings(adCounter: Int) {
        val currentCount: Int = getSettings()
        if (currentCount > 0 && adCounter > -1) {
            getSharedPreferences(MY_SETTINGS, 0).edit().apply {
                putInt("NO_ADS_COUNTER", currentCount + adCounter)
                apply()
            }
        }
    }

    fun getSettings(): Int {
        val sharedPref =
            getSharedPreferences(MY_SETTINGS, 0)
        return sharedPref.getInt("NO_ADS_COUNTER", 0)
    }

    fun playSound(linkId: Int) {
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
            mediaPlayer?.reset()
            mediaPlayer = null
        }
        if (oldLinkId == linkId) {
            oldLinkId = 0
        } else {
            mediaPlayer = MediaPlayer.create(
                this,
                linkId
            )
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
            oldLinkId = linkId
        }
    }

    fun showAds(myCallback: MyCallback): Boolean {
        var status = false
        if (rewardedAd != null) {
            status = true
            val activityContext: Activity = this
            rewardedAd?.show(activityContext) {
                saveSettings(2)
                myCallback.isShown(true)
                pauseMediaPlayer()
            }
            rewardedAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                }

                override fun onAdDismissedFullScreenContent() {
                    rewardedAd = null
                    resumeMediaPlayer()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    rewardedAd = null
                }

                override fun onAdImpression() {
                }

                override fun onAdShowedFullScreenContent() {
                    pauseMediaPlayer()
                }
            }
        }
        loadAdRequest()
        return status
    }

    fun pauseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
        }
    }

    fun resumeMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer?.start()
        }
    }

    fun finishMedia() {
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
            mediaPlayer?.reset()
            mediaPlayer = null
        }
    }

    fun stopSound() {
        oldLinkId = 0
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
            mediaPlayer?.reset()
            mediaPlayer = null
        }
    }
}