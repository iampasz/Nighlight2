package com.sarnavsky.pasz.nighlight2.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.sarnavsky.pasz.nighlight2.R;

public class ColorPicker extends Fragment implements ColorPickerDialogListener {


    ConstraintLayout mainBg;
    ImageView underImg;

    public static ColorPicker init(int id) {
        ColorPicker colorPicker = new ColorPicker();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        colorPicker.setArguments(bundle);
        return colorPicker;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.color_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ColorPickerDialog colorPickerDialog = ColorPickerDialog
                .newBuilder()
                .setSelectedButtonText(R.string.selected_button)
                .setDialogTitle(R.string.dialog_title)
                .setCustomButtonText(R.string.custom_button)
                .setPresetsButtonText(R.string.presets_button)
                .setColor(Color.RED)
                .create();
        colorPickerDialog.show(getParentFragmentManager(), "");

        colorPickerDialog.setColorPickerDialogListener(this);


        MainFragment main_fragment = (MainFragment) getParentFragmentManager().findFragmentByTag("main_fragment");
        if(main_fragment!=null){
            mainBg =  main_fragment.mainBg;
            underImg =  main_fragment.underImg;
        }

    }


    @Override
    public void onColorSelected(int dialogId, int color) {
        Log.d("TAG", "onColorSelected() called with: dialogId = [" + dialogId + "], color = [" + color + "]");
        switch (dialogId) {
            case 0:
                // We got result from the dialog that is shown when clicking on the icon in the action bar.

                if (getArguments().getInt("id") == 0) {
                    underImg.setColorFilter(color);
                } else {
                    mainBg.setBackgroundColor(color);
                }

                break;
        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {
        Log.d("TAG", "onDialogDismissed() called with: dialogId = [" + dialogId + "]");
    }
}
