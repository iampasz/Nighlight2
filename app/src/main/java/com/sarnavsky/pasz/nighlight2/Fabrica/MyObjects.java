package com.sarnavsky.pasz.nighlight2.Fabrica;

import android.graphics.Color;

import com.sarnavsky.pasz.nighlight2.Objects.MenuButton;
import com.sarnavsky.pasz.nighlight2.Objects.Nightlighter;
import com.sarnavsky.pasz.nighlight2.R;

import java.util.ArrayList;

public class MyObjects {

    private static final int SOUNDS = 1;
    private static final int BGCOLOR = 2;
    private static final int NLCOLOR = 3;
    private static final int NIGHTLIGHT = 4;
    private static final int TIMER = 5;
    private static final int BRIGHTS = 6;

    private static final int EMPTY_SOUND = 7;
    private static final int FIRST_SOUND = 8;
    private static final int SECOND_SOUND = 9;

    private static final int RED_COLOR = 10;
    private static final int WHITE_COLOR = 11;
    private static final int BLACK_COLOR = 12;
    private static final int ORANGE_COLOR = 13;
    private static final int YELLOW_COLOR = 14;
    private static final int GREEN_COLOR = 15;
    private static final int CANYAN_COLOR = 16;
    private static final int BLUE_COLOR = 17;
    private static final int MOOD_COLOR = 18;


    public ArrayList<Nightlighter> getNightlighters() {
        ArrayList<Nightlighter> list = new ArrayList<>();
        list.add(new Nightlighter(R.drawable.bear1, R.drawable.bear2, R.string.bear));
        list.add(new Nightlighter(R.drawable.mouse1, R.drawable.mouse2, R.string.mouse));
        list.add(new Nightlighter(R.drawable.rebbit1, R.drawable.rebbit2, R.string.bunny));
        list.add(new Nightlighter(R.drawable.cat1, R.drawable.cat2, R.string.cat));
        list.add(new Nightlighter(R.drawable.frog1, R.drawable.frog2, R.string.frog));
        list.add(new Nightlighter(R.drawable.pig1, R.drawable.pig2, R.string.piggy));
        list.add(new Nightlighter(R.drawable.dog1, R.drawable.dog2, R.string.dog));
        list.add(new Nightlighter(R.drawable.begemot1, R.drawable.begemot2, R.string.hippo));
        return list;
    }

    public ArrayList<MenuButton> getMenuButtons(String[] colors) {
        final ArrayList<MenuButton> menuButtons = new ArrayList<>();
        menuButtons.add(new MenuButton(Color.parseColor(colors[0]), R.drawable.ic_sound, R.string.melodies, 1));
        menuButtons.add(new MenuButton(Color.parseColor(colors[1]), R.drawable.ic_paint, R.string.color_background, 2));
        menuButtons.add(new MenuButton(Color.parseColor(colors[2]), R.drawable.ic_bear, R.string.color_nl, 3));
        menuButtons.add(new MenuButton(Color.parseColor(colors[3]), R.drawable.ic_stsrs, R.string.background, 6));
        menuButtons.add(new MenuButton(Color.parseColor(colors[4]), R.drawable.ic_anim, R.string.animation, 4));
        menuButtons.add(new MenuButton(Color.parseColor(colors[5]), R.drawable.ic_time, R.string.timer, 5));
        menuButtons.add(new MenuButton(Color.parseColor(colors[6]), R.drawable.ic_light, R.string.brightness, 7));
        // menuButtons.add(new MenuButton(Color.parseColor(colors[0]), R.drawable.politic_button, R.string.politica, 8));
        return menuButtons;
    }

    public ArrayList<MenuButton> getSoundsButtons() {
        final ArrayList<MenuButton> soundsButtons = new ArrayList<>();

        soundsButtons.add(new MenuButton(Color.YELLOW, R.drawable.none, R.string.brightness, EMPTY_SOUND));
        soundsButtons.add(new MenuButton(Color.GREEN, R.drawable.sounds, R.string.brightness, FIRST_SOUND));
        soundsButtons.add(new MenuButton(Color.BLUE, R.drawable.sounds, R.string.brightness, SECOND_SOUND));
        return soundsButtons;
    }


    public ArrayList<MenuButton> getBgColorsButtons(String[] colors) {
        final ArrayList<MenuButton> bgColors = new ArrayList<>();
        bgColors.add(new MenuButton(Color.parseColor(colors[0]), 0, R.string.color_nl, RED_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[1]), 0, R.string.color_nl, ORANGE_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[2]), 0, R.string.color_nl, YELLOW_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[3]), 0, R.string.color_nl, GREEN_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[4]), 0, R.string.color_nl, CANYAN_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[5]), 0, R.string.color_nl, BLUE_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[6]), 0, R.string.color_nl, MOOD_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[7]), 0, R.string.color_nl, BLACK_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[8]), 0, R.string.color_nl, BLACK_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[9]), 0, R.string.color_nl, BLACK_COLOR));
        bgColors.add(new MenuButton(Color.parseColor(colors[10]), 0, R.string.color_nl, BLACK_COLOR));
        // bgColors.add(new MenuButton(Color.parseColor(colors[11]), 0, R.string.ng_color, BLACK_COLOR));
        return bgColors;
    }

    public int[] getBgArray() {

        int[] bgArray = new int[]{
                R.drawable.light_blue,
                R.drawable.light_canian,
                R.drawable.light_gray,
                R.drawable.light_green,
                R.drawable.light_orange,
                R.drawable.light_violet,
                R.drawable.stars_grey,
                R.drawable.bg_animal,
                R.drawable.bg_flowers,
                R.drawable.bg_stars3
        };

        return bgArray;
    }
}
