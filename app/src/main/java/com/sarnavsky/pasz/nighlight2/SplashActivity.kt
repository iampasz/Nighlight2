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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initFullScreenAd()


        binding.imageView.setColorFilter(
            android
                .graphics
                .Color
                .argb(255, 255, 0, 0)
        )
        NightlightHelper.changeColor(binding.imageView)


        // val animationView =  binding.lottieAnimationView

        //animationView.pauseAnimation()
//        animationView.speed = 2f
//        animationView.playAnimation()


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
                loadFullScreenAds()
                Log.i("INTERNET_CONNECTION", "TYPE_WIFI")
            }

            NO_INTERNET_CONNECTION -> {
                Log.i("INTERNET_CONNECTION", "NO_INTERNET_CONNECTION")
                loadLoader(30)
                startLoaderTimer(2000)
            }
        }
    }

    private fun loadFullScreenAds() {

        startLoaderTimer(10000)

        Log.i("ADWASLOADED","after timer ")

        (this@SplashActivity.application as MainApplication).loadAdFirst(this@SplashActivity,

            object : MainApplication.OnShowAdCompleteListener {
                override fun onShowAdComplete(successStatus: Boolean) {

                    Log.i("ADWASLOADED","Change ad was loaded adWasLoaded ${successStatus}")
                    adWasLoaded = true


//                    startMainActivity()
//                    finish()
                }
            })


       // startLoaderTimer(5000)


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

    var adWasLoaded = false

    private fun startLoaderTimer(seconds: Long) {

        Log.i("STARTTIMER", "seconds ${seconds}")
      object : CountDownTimer(seconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val random = Random.nextInt(30)
                loadLoader(random)

                Log.i("LOADERTIMER","onTick ${millisUntilFinished}")
                if(adWasLoaded){
                    this.cancel()
                    this@SplashActivity.finish()
                    startMainActivity()

                    (this@SplashActivity.application as MainApplication).showAdFirst(object : MainApplication.OnShowAdCompleteListener{
                        override fun onShowAdComplete(successStatus: Boolean) {

                            Log.i("CHEKTHISONE","")
                        }

                    })
                    finish()
                }

                Log.i("ADWASLOADED", "Ad status ${adWasLoaded}")
            }

            override fun onFinish() {
                loadLoader(100)
                Log.i("LOADERTIMER","onFinish")

                Log.i("ADWASLOADED", "Ad status finish ${adWasLoaded}")

                startMainActivity()
                finish()

//                (this@SplashActivity.application as MainApplication).showAdFirst(
//
//                    object : MainApplication.OnShowAdCompleteListener {
//                        override fun onShowAdComplete(successStatus: Boolean) {
//
//                            finish()
//                        }
//                    })

//                finish()
//                if (adIsLoaded) {
//                   // (this@SplashActivity.application as MainApplication).showAdFirst()
//                }else{
//                    startMainActivity()
//                }
            }
        }.start()


//        (this@SplashActivity.application as MainApplication).loadAdFirst(this@SplashActivity,
//            object : MainApplication.OnShowAdCompleteListener {
//                override fun onShowAdComplete(successStatus: Boolean) {
//                    binding.progressBar.progress = 100
//                    Log.d("ACTIVITU_STATUS", "something heppens load or not wherever")
//
//                    if(successStatus){
//                        adIsLoaded = true
//                    }
//                }
//            })
    }
}