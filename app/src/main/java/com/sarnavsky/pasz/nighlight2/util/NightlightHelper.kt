package com.sarnavsky.pasz.nighlight2.util

import android.content.res.Resources
import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.objects.MenuItem
import com.sarnavsky.pasz.nighlight2.objects.Nightlighter


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
                    NIGHTLIGHT_BUTTON
                )
            )
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.blue),
                    R.drawable.ic_anim,
                    res.getString(R.string.animation),
                    4
                )
            )
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.darkBlue),
                    R.drawable.ic_time,
                    res.getString(R.string.timer),

                    5
                )
            )
            menuButtons.add(
                MenuItem(
                    res.getColor(R.color.violet),
                    R.drawable.ic_light,
                    res.getString(R.string.brightness),
                    7
                )
            )
            // menuButtons.add(new MenuButton(Color.parseColor(colors[0]), R.drawable.politic_button, R.string.politica, 8));
            return menuButtons
        }

//        fun getSoundsButtons(): ArrayList<MenuButton> {
//            val soundsButtons = ArrayList<MenuButton>()
//            soundsButtons.add(
//                MenuButton(
//                    Color.YELLOW,
//                    R.drawable.none,
//                    R.string.brightness,
//                    EMPTY_SOUND
//                )
//            )
//            soundsButtons.add(
//                MenuButton(
//                    Color.GREEN,
//                    R.drawable.sounds,
//                    R.string.brightness,
//                    FIRST_SOUND
//                )
//            )
//            soundsButtons.add(
//                MenuButton(
//                    Color.BLUE,
//                    R.drawable.sounds,
//                    R.string.brightness,
//                    SECOND_SOUND
//                )
//            )
//            return soundsButtons
//        }
//
//        fun getBgColorsButtons(colors: Array<String?>): ArrayList<MenuButton> {
//            val bgColors = ArrayList<MenuButton>()
//            bgColors.add(MenuButton(Color.parseColor(colors[0]), 0, R.string.color_nl, RED_COLOR))
//            bgColors.add(
//                MenuButton(
//                    Color.parseColor(colors[1]),
//                    0,
//                    R.string.color_nl,
//                    ORANGE_COLOR
//                )
//            )
//            bgColors.add(
//                MenuButton(
//                    Color.parseColor(colors[2]),
//                    0,
//                    R.string.color_nl,
//                    YELLOW_COLOR
//                )
//            )
//            bgColors.add(MenuButton(Color.parseColor(colors[3]), 0, R.string.color_nl, GREEN_COLOR))
//            bgColors.add(
//                MenuButton(
//                    Color.parseColor(colors[4]),
//                    0,
//                    R.string.color_nl,
//                    CANYAN_COLOR
//                )
//            )
//            bgColors.add(MenuButton(Color.parseColor(colors[5]), 0, R.string.color_nl, BLUE_COLOR))
//            bgColors.add(MenuButton(Color.parseColor(colors[6]), 0, R.string.color_nl, MOOD_COLOR))
//            bgColors.add(MenuButton(Color.parseColor(colors[7]), 0, R.string.color_nl, BLACK_COLOR))
//            bgColors.add(MenuButton(Color.parseColor(colors[8]), 0, R.string.color_nl, BLACK_COLOR))
//            bgColors.add(MenuButton(Color.parseColor(colors[9]), 0, R.string.color_nl, BLACK_COLOR))
//            bgColors.add(
//                MenuButton(
//                    Color.parseColor(colors[10]),
//                    0,
//                    R.string.color_nl,
//                    BLACK_COLOR
//                )
//            )
//            // bgColors.add(new MenuButton(Color.parseColor(colors[11]), 0, R.string.ng_color, BLACK_COLOR));
//            return bgColors
//        }

//        fun getBgArray(): IntArray {
//            return intArrayOf(
//                R.drawable.light_blue,
//                R.drawable.light_canian,
//                R.drawable.light_gray,
//                R.drawable.light_green,
//                R.drawable.light_orange,
//                R.drawable.light_violet,
//                R.drawable.stars_grey,
//                R.drawable.bg_animal,
//                R.drawable.bg_flowers,
//                R.drawable.bg_stars3,
//                0
//            )
//        }
    }
}
