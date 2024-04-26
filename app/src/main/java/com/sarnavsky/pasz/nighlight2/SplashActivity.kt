package com.sarnavsky.pasz.nighlight2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
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

    var myStatus = true

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
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun initFullScreenAd() {
        when (NightlightHelper.checkInternet(this@SplashActivity)) {
            TYPE_WIFI -> {
                loadFullScreenAds()
                Log.i("INTERNET_CONNECTION", "TYPE_WIFI")
            }

            TYPE_MOBILE -> {
                loadFullScreenAds()
                Log.i("INTERNET_CONNECTION", "TYPE_MOBILE")
            }

            INTERNET_CONNECTION -> {
                loadFullScreenAds()
                Log.i("INTERNET_CONNECTION", "INTERNET_CONNECTION")
            }

            NO_INTERNET_CONNECTION -> {
                Log.i("INTERNET_CONNECTION", "NO_INTERNET_CONNECTION")
                loadLoader(30)
                startLoaderTimer(2000)
            }
        }
    }

    private fun loadFullScreenAds() {

        startLoaderTimer(5000)

        (this@SplashActivity.application as MainApplication).loadAdFirst(this@SplashActivity,
            object : MainApplication.OnShowAdCompleteListener {
                override fun onShowAdComplete() {
                    binding.progressBar.progress = 100
                    Log.d("ACTIVITU_STATUS", "something heppens load or not wherever")

                    if(myStatus){
                        startMainActivity()
                    }


                }
            })
    }

    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun loadLoader(progress: Int) {
        val currentProgress = binding.progressBar.progress
        if (currentProgress != 90) {
            val newProgress = binding.progressBar.progress + progress
            if (newProgress > 90) {
                binding.progressBar.progress = 90
            } else {
                binding.progressBar.progress = newProgress
            }
        }
    }

    private fun startLoaderTimer(seconds: Long) {
        Log.i("STARTTIMER","seconds ${seconds}")
        object : CountDownTimer(seconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val random = Random.nextInt(30)
                loadLoader(random)
            }

            override fun onFinish() {
                loadLoader(100)
                myStatus = false
                startMainActivity()
                finish()
            }
        }.start()
    }
}