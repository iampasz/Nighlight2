package com.sarnavsky.pasz.nighlight2.Adapters;//package com.appsforkids.pasz.lullabies;

import android.util.Log;
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

    public ListMusicAdapter(PlayMyMusic playMyMusic, ArrayList<MyAudio> arrayList){
        this.playMyMusic = playMyMusic;
        this.arrayList = arrayList;
    };

    @NonNull
    @Override
    public ListMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ListMusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListMusicHolder holder, int position) {

        //holder.play_item.setTag(UUID.randomUUID().toString());
        holder.music_name.setText(arrayList.get(holder.getAdapterPosition()).getName());
        holder.music_author.setText(arrayList.get(holder.getAdapterPosition()).getAuth());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.i("Play", position +" it is i, and  listMusicHolder.getAdapterPosition() " +holder.getAdapterPosition());
                currentMusicPosition=-1;

                if(pressedPosition==holder.getAdapterPosition()){
                    //holder.play_item.setImageResource(R.drawable.bt_play);
                    Log.i("Play", pressedPosition+ " тут должен плей поставить " +  holder.getAdapterPosition());
                    playMyMusic.pressPosition(holder.getAdapterPosition(), false);
                    pressedPosition=-1;

                }else{
                    playMyMusic.pressPosition(holder.getAdapterPosition(), true);
                    //holder.play_item.setImageResource(R.drawable.bt_pause);
                    Log.i("Play", pressedPosition+ " тут должен стоп поставить " +  holder.getAdapterPosition());
                    pressedPosition =  holder.getAdapterPosition();
                }
                notifyDataSetChanged();
            }
        });
        //Log.i("Play", pressedPosition +" presed and new " +holder.getAdapterPosition());
        if(holder.getAdapterPosition()==pressedPosition){
            holder.play_item.setImageResource(R.drawable.pausa);

        }else{
            holder.play_item.setImageResource(R.drawable.play);
        }

        Log.i("ListMusicAdapter", currentMusicPosition+" currentMusicPosition and holder.getAdapterPosition() "+ holder.getAdapterPosition());

        if(holder.getAdapterPosition()==currentMusicPosition && currentMusicPosition!=-1){
            holder.play_item.setImageResource(R.drawable.pausa);
            Log.i("ListMusicAdapter", currentMusicPosition+" we are here");

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ListMusicHolder extends RecyclerView.ViewHolder {

        private ImageView play_item;
        private TextView music_name;
        private TextView music_author;

        public ListMusicHolder(@NonNull View itemView) {
            super(itemView);

            play_item = itemView.findViewById(R.id.image);
            music_name = itemView.findViewById(R.id.name);
            music_author = itemView.findViewById(R.id.author);

        }
    }

}
