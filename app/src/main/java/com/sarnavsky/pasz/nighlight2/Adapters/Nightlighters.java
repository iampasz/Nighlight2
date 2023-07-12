package com.sarnavsky.pasz.nighlight2.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.sarnavsky.pasz.nighlight2.Objects.Nightlighter;
import com.sarnavsky.pasz.nighlight2.R;

import java.util.ArrayList;
import java.util.Random;

public class Nightlighters extends RecyclerView.Adapter<Nightlighters.NightlightHolder> {

    ArrayList<Nightlighter> arrayList;
    String[] bgNlColors;
    Random random = new Random();

    public Nightlighters(ArrayList<Nightlighter> arrayList, String[] bgNlColors) {
        this.arrayList = arrayList;
        this.bgNlColors = bgNlColors;
    }

    @NonNull
    @Override
    public NightlightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_fragment, parent, false);
        NightlightHolder nightlightHolder = new NightlightHolder(v);
        return nightlightHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NightlightHolder holder, int position) {

        holder.upImg.setImageResource(arrayList.get(position).getUpImg());
        holder.underImg.setImageResource(arrayList.get(position).getDownImg());

        int color = random.nextInt(bgNlColors.length);
        holder.underImg.setColorFilter(Color.parseColor(bgNlColors[color]));


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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int i = random.nextInt(techniques.size());

                YoYo.with(techniques.get(i))
                        .duration(700)
                        .playOn(holder.itemView);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class NightlightHolder extends RecyclerView.ViewHolder {

        ImageView upImg;
        ImageView underImg;

        public NightlightHolder(View itemView) {
            super(itemView);

            upImg = (ImageView) itemView.findViewById(R.id.upImg);
            underImg = (ImageView) itemView.findViewById(R.id.underImg);

        }
    }
}
