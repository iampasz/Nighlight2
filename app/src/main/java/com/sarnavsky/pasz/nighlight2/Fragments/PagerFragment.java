package com.sarnavsky.pasz.nighlight2.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.sarnavsky.pasz.nighlight2.Objects.Nightlighter;
import com.sarnavsky.pasz.nighlight2.R;

import java.util.ArrayList;
import java.util.Random;


public class PagerFragment extends Fragment {

    Nightlighter myNightlighter;
    Context ctx;

    public static PagerFragment newInt(Nightlighter nightlighter) {

        PagerFragment pageFragment = new PagerFragment();
        Bundle arg = new Bundle();
        arg.putSerializable("nightlight", nightlighter);
        pageFragment.setArguments(arg);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myNightlighter = (Nightlighter) getArguments().getSerializable("nightlight");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ctx = view.getContext();

        final ImageView underImg = (ImageView) view.findViewById(R.id.underImg);
        final ImageView upImg = (ImageView) view.findViewById(R.id.upImg);

        underImg.setImageResource(myNightlighter.getDownImg());
        upImg.setImageResource(myNightlighter.getUpImg());

        final ArrayList<Techniques> techniques = new ArrayList<>();
        techniques.add(Techniques.Bounce);
        techniques.add(Techniques.BounceIn);
        techniques.add(Techniques.FadeIn);
        techniques.add(Techniques.DropOut);
        techniques.add(Techniques.Shake);
        techniques.add(Techniques.Flash);
        techniques.add(Techniques.SlideInLeft);
        techniques.add(Techniques.Swing);
        techniques.add(Techniques.FlipInY);

        upImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int i = random.nextInt(techniques.size());

                YoYo.with(techniques.get(i))
                        .duration(700)
                        .playOn(upImg);

                YoYo.with(techniques.get(i))
                        .duration(700)
                        .playOn(underImg);
            }
        });
    }
}
