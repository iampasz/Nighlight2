package com.sarnavsky.pasz.nighlight2.Adapters;//package com.appsforkids.pasz.lullabies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarnavsky.pasz.nighlight2.Interfaces.PlayMyMusic;
import com.sarnavsky.pasz.nighlight2.Objects.MyAudio;
import com.sarnavsky.pasz.nighlight2.R;

import java.util.ArrayList;

public class ListMusicAdapter extends RecyclerView.Adapter<ListMusicAdapter.ListMusicHolder> {

    PlayMyMusic playMyMusic;
    int pressedPosition = -1;
    int currentMusicPosition = -1;

    ArrayList<MyAudio> arrayList;

    public ListMusicAdapter(PlayMyMusic playMyMusic, ArrayList<MyAudio> arrayList) {
        this.playMyMusic = playMyMusic;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ListMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ListMusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListMusicHolder holder, int position) {

        int pos = position;

        holder.music_name.setText(arrayList.get(pos).getName());
        holder.music_author.setText(arrayList.get(pos).getAuth());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMusicPosition = -1;

                if (pressedPosition == pos) {
                    playMyMusic.pressPosition(pos, false);
                    pressedPosition = -1;
                } else {
                    playMyMusic.pressPosition(pos, true);
                    pressedPosition = pos;
                }
                notifyDataSetChanged();
            }
        });

        if (pos == pressedPosition) {
            holder.play_item.setImageResource(R.drawable.pausa);

        } else {
            holder.play_item.setImageResource(R.drawable.play);
        }

        if (pos == currentMusicPosition && currentMusicPosition != -1) {
            holder.play_item.setImageResource(R.drawable.pausa);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ListMusicHolder extends RecyclerView.ViewHolder {

        private final ImageView play_item;
        private final TextView music_name;
        private final TextView music_author;

        public ListMusicHolder(@NonNull View itemView) {
            super(itemView);

            play_item = itemView.findViewById(R.id.image);
            music_name = itemView.findViewById(R.id.name);
            music_author = itemView.findViewById(R.id.author);
        }
    }
}
