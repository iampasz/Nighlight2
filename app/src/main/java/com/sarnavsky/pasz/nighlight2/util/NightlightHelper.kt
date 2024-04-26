package com.sarnavsky.pasz.nighlight2.util

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.widget.ImageView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.objects.MenuItem
import com.sarnavsky.pasz.nighlight2.objects.Nightlighter
import kotlin.random.Random

@Suppress("DEPRECATION")
class NightlightHelper {
    companion object {
        fun getNightlighters(): ArrayList<Nightlighter> {
            val list = ArrayList<Nightlighter>()
            list.add(
                Nightlighter(
                    R.drawable.bear1,
                    R.drawable.bear2,
                    R.string.bear
                )
            )
            list.add(
                Nightlighter(
                    R.drawable.mouse1,
                    R.drawable.mouse2,
                    R.string.mouse
                )
            )
            list.add(
                Nightlighter(
                    R.drawable.rebbit1,
                    R.drawable.rebbit2,
                    R.string.bunny
                )
            )
            list.add(
                Nightlighter(
                    R.drawable.cat1,
                    R.drawable.cat2,
                    R.string.cat
                )
            )
            list.add(
                Nightlighter(
                    R.drawable.frog1,
                    R.drawable.frog2,
                    R.string.frog
                )
            )
            list.add(
                Nightlighter(
                    R.drawable.pig1,
                    R.drawable.pig2,
                    R.string.piggy
                )
            )
            list.add(
                Nightlighter(
                    R.drawable.dog1,
                    R.drawable.dog2,
                    R.string.dog
                )
            )
            list.add(
                Nightlighter(
                    R.drawable.begemot1,
                    R.drawable.begemot2,
                    R.string.hippo
                )
            )
            return list
        }

        fun getMenuButtons(res: Resources): ArrayList<MenuItem> {
            val menuButtons = ArrayList<MenuItem>()
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.red),
                    R.drawable.ic_sound,
                    res.getString(R.string.melodies),
                    SOUNDS_BUTTON
                )
            )
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.orange),
                    R.drawable.ic_paint,
                    res.getString(R.string.color_background),
                    BG_COLOR_BUTTON
                )
            )
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.yellow),
                    R.drawable.ic_bear,
                    res.getString(R.string.color_nl),
                    NL_COLOR_BUTTON
                )
            )
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.green),
                    R.drawable.ic_stsrs,
                    res.getString(R.string.background),
                    ANIMATION_TYPE_BUTTON
                )
            )
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.blue),
                    R.drawable.ic_anim,
                    res.getString(R.string.animation),
                    ANIMATION_BUTTON
                )
            )
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.darkBlue),
                    R.drawable.ic_time,
                    res.getString(R.string.timer),

                    TIMER_BUTTON
                )
            )
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.violet),
                    R.drawable.ic_light,
                    res.getString(R.string.brightness),
                    BRIGHTS_BUTTON
                )
            )
            return menuButtons
        }

        fun getBgArray(): IntArray {
            return intArrayOf(
                R.drawable.light_blue,
                R.drawable.light_canian,
                R.drawable.light_gray,
                R.drawable.light_green,
                R.drawable.light_orange,
                R.drawable.light_violet,
                R.drawable.stars_grey,
                R.drawable.bg_animal,
                R.drawable.bg_flowers,
                R.drawable.bg_stars3,
                0
            )
        }

        private fun getTechniquesArray(): ArrayList<Techniques> {
            val techniques: ArrayList<Techniques> = ArrayList()
            techniques.add(Techniques.Bounce)
            techniques.add(Techniques.BounceIn)
            techniques.add(Techniques.FadeIn)
            techniques.add(Techniques.DropOut)
            techniques.add(Techniques.Shake)
            techniques.add(Techniques.Flash)
            techniques.add(Techniques.SlideInLeft)
            techniques.add(Techniques.Swing)
            techniques.add(Techniques.FlipInY)
            return techniques
        }

        fun checkInternet(ctx: Context): Int {
            val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (wifiInfo != null && wifiInfo.isConnected) {
                return ConnectivityManager.TYPE_WIFI
            }

            wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (wifiInfo != null && wifiInfo.isConnected) {
                return ConnectivityManager.TYPE_MOBILE
            }
            wifiInfo = cm.activeNetworkInfo
            if (wifiInfo != null && wifiInfo.isConnected) {
                return INTERNET_CONNECTION
            }
            return NO_INTERNET_CONNECTION
        }

        fun changeColor(view: View) {

            var repeater = true

            val alphaAnimation = AlphaAnimation(0f, 1f)

            alphaAnimation.duration = 2000
            alphaAnimation.repeatCount = -1
            alphaAnimation.repeatMode = Animation.REVERSE
            alphaAnimation.setAnimationListener(object : AnimationListener {
                override fun onAnimationStart(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationRepeat(animation: Animation?) {

                    repeater = !repeater

                    val random = Random.nextInt(255)
                    val random1 = Random.nextInt(255)
                    val random2 = Random.nextInt(255)

                    if (repeater) {
                        (view as ImageView).setColorFilter(
                            android
                                .graphics
                                .Color
                                .argb(255, random, random1, random2)
                        )

                    }
                    Log.i("SEEDSSSDS", "${repeater}")

                }

            })
            view.startAnimation(alphaAnimation)

        }

        fun startYoYoAnimation(view: View) {
            val techniques = getTechniquesArray()
            val random = java.util.Random()
            val i = random.nextInt(techniques.size)

            YoYo.with(techniques[i])
                .duration(700)
                .playOn(view)
        }
    }

}




