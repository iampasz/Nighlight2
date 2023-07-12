package com.sarnavsky.pasz.nighlight2.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarnavsky.pasz.nighlight2.Interfaces.ChangeColors;
import com.sarnavsky.pasz.nighlight2.Interfaces.OpenColorFragment;
import com.sarnavsky.pasz.nighlight2.Objects.MenuButton;
import com.sarnavsky.pasz.nighlight2.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    ArrayList<MenuButton> menuButtons;

    ChangeColors changeColors;
    String[] colors;
    int i = 0;
    OpenColorFragment openColorFragment;

    public void MyOnclick(ChangeColors changeColors) {
        this.changeColors = changeColors;
    }

    public void MyOnLongclick(OpenColorFragment openColorFragment) {
        this.openColorFragment = openColorFragment;
    }

    public RecyclerViewAdapter(ArrayList menuButtons, String[] colors) {

        this.menuButtons = menuButtons;
        this.colors = colors;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.colorShape.setColorFilter(menuButtons.get(position).getColor());
        holder.iconImage.setImageResource(menuButtons.get(position).getIconImege());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColors.onclick(menuButtons.get(position).getButton());

                holder.colorShape.setColorFilter(Color.parseColor(colors[i]));
                i++;
                if (i >= colors.length) {
                    i = 0;
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                openColorFragment.onclick(menuButtons.get(position).getButton());

                return true;
            }
        });

        holder.textMenu.setText(menuButtons.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return menuButtons.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView colorShape;
        ImageView iconImage;
        TextView textMenu;

        public MyViewHolder(View itemView) {
            super(itemView);

            colorShape = (ImageView) itemView.findViewById(R.id.colorShape);
            iconImage = (ImageView) itemView.findViewById(R.id.iconImage);
            textMenu = (TextView) itemView.findViewById(R.id.textMenu);

        }
    }

}
