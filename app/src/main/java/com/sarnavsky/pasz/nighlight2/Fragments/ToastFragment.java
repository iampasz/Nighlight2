package com.sarnavsky.pasz.nighlight2.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.sarnavsky.pasz.nighlight2.Interfaces.MyCallback;
import com.sarnavsky.pasz.nighlight2.MainActivity;
import com.sarnavsky.pasz.nighlight2.R;

public class ToastFragment extends Fragment {

    private static final String MY_SETTINGS = "my_settings";
    Boolean dontShow = false;

    public static Fragment init(int image, String text){
        ToastFragment  toastFragment = new ToastFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        bundle.putInt("image", image);
        toastFragment.setArguments(bundle);
        return toastFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.toast_fragment,container,  false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button positive_button = view.findViewById(R.id.positive_button);
        Button negative_button = view.findViewById(R.id.negative_button);
        SwitchCompat switch1 = view.findViewById(R.id.switch1);

        positive_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showAds(new MyCallback() {
                    @Override
                    public void isShown(boolean shown) {
                        getParentFragmentManager().beginTransaction().replace(R.id.mainContainer, new SettingsFragment()).commit();
                        MainFragment.mAdView.setVisibility(View.GONE);
                    }
                });


            }
        });

        negative_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().remove(ToastFragment.this).commit();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                dontShow = b;
            }
        });

    }

    public void saveSettings() {

            SharedPreferences sharedPref = getActivity().getSharedPreferences(MY_SETTINGS, 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("NOADS", dontShow);
            editor.apply();

    }


    @Override
    public void onStop() {
        super.onStop();
        saveSettings();
    }
}
