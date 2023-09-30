package com.sarnavsky.pasz.nighlight2.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.sarnavsky.pasz.nighlight2.Interfaces.MyCallback;
import com.sarnavsky.pasz.nighlight2.MainActivity;
import com.sarnavsky.pasz.nighlight2.R;

public class SettingsFragment extends Fragment {


    CardView showAdd;
    CardView nl_bg;
    CardView bg_color;

    private final String TAG = "ITSMYADD";

    TextView ads_text;

    int adsCounter;

    LinearLayout linear_download;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showAdd = view.findViewById(R.id.showAdd);
        nl_bg = view.findViewById(R.id.nl_bg);
        bg_color = view.findViewById(R.id.bg_color);
        ads_text = view.findViewById(R.id.ads_text);

        linear_download = view.findViewById(R.id.linear_download);


        adsCounter = ((MainActivity) getActivity()).getSettings();

        ads_text.setText(getResources().getString(R.string.disable_ads_ses) + " " + adsCounter);

        if (adsCounter < 1) {
            ads_text.setTextColor(Color.RED);
        } else {
            ads_text.setTextColor(Color.parseColor("#2C8005"));
        }


        nl_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainContainer, ColorPicker.init(0), "ColorPicker")
                        .commit();
            }
        });

        bg_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainContainer, ColorPicker.init(1), "ColorPicker")
                        .commit();
            }
        });


        showAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean status = ((MainActivity) getActivity()).showAds(new MyCallback() {
                    @Override
                    public void isShown(boolean shown) {

                        if (shown) {

                            //MainFragment.mAdView.setVisibility(View.GONE);

                            MainFragment.mAdView.setVisibility(View.GONE);

                            adsCounter = ((MainActivity) getActivity()).getSettings();
                            ads_text.setText("No ADS less: " + adsCounter);
                            ads_text.setTextColor(Color.parseColor("#2C8005"));
                        }
                    }
                });

                if (status) {

                } else {
                    ads_text.setText(getResources().getString(R.string.no_ads_message));
                    ads_text.setTextColor(Color.RED);
                }
            }
        });


        Button close2 = view.findViewById(R.id.close2);
        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().remove(SettingsFragment.this).commit();

            }
        });

        linear_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.custom_dialog_layout, null);
                builder.setView(dialogLayout);

                Button positive_button = dialogLayout.findViewById(R.id.positive_button);
                Button negative_button = dialogLayout.findViewById(R.id.negative_button);

                // Створіть діалог і покажіть його
                AlertDialog dialog = builder.create();

                positive_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.appsforkids.pasz.nightlightpromax"));
                        startActivity(browserIntent);

                        dialog.dismiss();

                    }
                });

                negative_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                //dialog.getWindow().setBackgroundDrawableResource(R.drawable.shape_menu4c);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();


            }
        });
    }
}
