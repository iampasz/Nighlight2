package com.sarnavsky.pasz.nighlight2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.transition.TransitionInflater
import android.util.Log
import com.sarnavsky.pasz.nighlight2.databinding.SplashActivityBinding
import com.sarnavsky.pasz.nighlight2.util.GDPRHelper
import com.sarnavsky.pasz.nighlight2.util.INTERNET_CONNECTION
import com.sarnavsky.pasz.nighlight2.util.NO_INTERNET_CONNECTION
import com.sarnavsky.pasz.nighlight2.util.NightlightHelper
import com.sarnavsky.pasz.nighlight2.util.TYPE_MOBILE
import com.sarnavsky.pasz.nighlight2.util.TYPE_WIFI
import kotlin.random.Random

@SuppressLint("CustomSplashScreen")
class SplashActivity : Activity() {

    private lateinit var binding: SplashActivityBinding
    var adWasLoaded = false
    var GDPRWasLoaded = false

    private var cdt:CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
       // initFullScreenAd()

        (application as MainApplication).loadAd(this)

        startLoaderTimer(10000)


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
            }

            NO_INTERNET_CONNECTION -> {
                Log.i("INTERNET_CONNECTION", "NO_INTERNET_CONNECTION")
                loadLoader(30)
                startLoaderTimer(2000)
            }
        }
    }

    private fun loadFullScreenAds() {

        startLoaderTimer(7000)

        Log.i("ADWASLOADED", "after timer ")

        val gdpr = GDPRHelper(this@SplashActivity)

        gdpr.checkGDPR( object : GDPRHelper.OnShowAdRequest {
            override fun showAdPossible(success: Boolean) {

                Log.i("ADWASLOADED", "callback ")
                if (success) {

                    Log.i("ADWASLOADED", "true ")
                }else{
                    Log.i("ADWASLOADED", "false ")
                    cdt?.cancel()
                  gdpr.showGDPR(object : GDPRHelper.OnShowAdRequest{
                      override fun showAdPossible(success: Boolean) {
                          finish()
                          startMainActivity()
                      }

                  })
                }
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



    private fun startLoaderTimer(seconds: Long) {

        cdt = object : CountDownTimer(seconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val random = Random.nextInt(30)
                loadLoader(random)


                Log.i("LOADERTIMERW", " ${ (this@SplashActivity.application as MainApplication).getAdd()}")
                if ((this@SplashActivity.application as MainApplication).getAdd()) {
                    this.cancel()
                    this@SplashActivity.finish()
                    startMainActivity()

//                    (this@SplashActivity.application as MainApplication).showAdFirst(object :
//                        MainApplication.OnShowAdCompleteListener {
//                        override fun onShowAdComplete(successStatus: Boolean) {
//
//                            Log.i("CHEKTHISONE", "showAdFirst")
//
//                        }
//
//                    })
//                    finish()
                }
//
//                if (GDPRWasLoaded) {
//                    this.cancel()
//                    this@SplashActivity.finish()
//                    startMainActivity()
//
//                    (this@SplashActivity.application as MainApplication).showAdFirst(object :
//                        MainApplication.OnShowAdCompleteListener {
//                        override fun onShowAdComplete(successStatus: Boolean) {
//                            Log.i("CHEKTHISONE", "showAdFirst")
//                        }
//                    })
//                    finish()
//                }


            }

            override fun onFinish() {
                loadLoader(100)
                Log.i("LOADERTIMER", "onFinish")


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