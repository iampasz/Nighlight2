package com.sarnavsky.pasz.nighlight2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.transition.TransitionInflater
import android.util.Log
import com.sarnavsky.pasz.nighlight2.databinding.SplashActivityBinding
import com.sarnavsky.pasz.nighlight2.util.INTERNET_CONNECTION
import com.sarnavsky.pasz.nighlight2.util.NO_INTERNET_CONNECTION
import com.sarnavsky.pasz.nighlight2.util.NightlightHelper
import com.sarnavsky.pasz.nighlight2.util.TYPE_MOBILE
import com.sarnavsky.pasz.nighlight2.util.TYPE_WIFI
import kotlin.random.Random

@SuppressLint("CustomSplashScreen")
class SplashActivity : Activity() {

    private lateinit var binding: SplashActivityBinding
    private var cdt: CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()


        binding.imageView.setColorFilter(
            android
                .graphics
                .Color
                .argb(255, 255, 0, 0)
        )
        NightlightHelper.changeColor(binding.imageView)



initFullScreenAd()
    }

    private fun initView() {
        window.enterTransition = TransitionInflater
            .from(this).inflateTransition(android.R.transition.slide_bottom)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initFullScreenAd() {
        when (NightlightHelper.checkInternet(this@SplashActivity)) {
            TYPE_WIFI,
            TYPE_MOBILE,
            INTERNET_CONNECTION -> {

                (application as MainApplication).loadAd(this)
                startLoaderTimer(5000)
            }

            NO_INTERNET_CONNECTION -> {
                Log.i("INTERNET_CONNECTION", "NO_INTERNET_CONNECTION")
                loadLoader(30)
                startLoaderTimer(2000)
            }
        }
    }



    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun loadLoader(progress: Int) {
        val currentProgress = binding.progressBar.progress
        if (currentProgress != 100) {
            val newProgress = binding.progressBar.progress + progress
            if (newProgress > 100) {
                binding.progressBar.progress = 100
            } else {
                binding.progressBar.progress = newProgress
            }
        }
    }


    private fun startLoaderTimer(seconds: Long) {

        cdt = object : CountDownTimer(seconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val random = Random.nextInt(30)
                loadLoader(random)
                if ((this@SplashActivity.application as MainApplication).getAdd()) {

                   // if (googleMobileAdsConsentManager.canRequestAds) {
                        this.cancel()
                        this@SplashActivity.finish()
                        startMainActivity()
                   // }

                }

              //  secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1
            }

            override fun onFinish() {
                loadLoader(100)
                Log.i("LOADERTIMER", "onFinish")
                //secondsRemaining = 0

                    startMainActivity()
                    finish()


            }
        }.start()

    }




}