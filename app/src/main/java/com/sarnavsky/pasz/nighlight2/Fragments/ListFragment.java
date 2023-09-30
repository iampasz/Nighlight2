package com.sarnavsky.pasz.nighlight2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sarnavsky.pasz.nighlight2.Adapters.ListMusicAdapter;
import com.sarnavsky.pasz.nighlight2.Interfaces.PlayMyMusic;
import com.sarnavsky.pasz.nighlight2.MainActivity;
import com.sarnavsky.pasz.nighlight2.Objects.MyAudio;
import com.sarnavsky.pasz.nighlight2.R;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView rv;
    Button close;
    ListMusicAdapter myListAdapter;
    int currentMusicPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rv);
        close = view.findViewById(R.id.close);

        ArrayList<MyAudio> arrayList = new ArrayList<>();

        MyAudio myAudio0 = new MyAudio();
        MyAudio myAudio1 = new MyAudio();
        MyAudio myAudio2 = new MyAudio();
        MyAudio myAudio3 = new MyAudio();
        MyAudio myAudio4 = new MyAudio();

        myAudio0.setLinkId(R.raw.detskaya);
        myAudio0.setName("Dreamflight");
        myAudio0.setAuth("Lullaby");
        myAudio1.setLinkId(R.raw.eho);
        myAudio1.setName("Cristal Rain");
        myAudio1.setAuth("Xu Qing-Yuan");
        myAudio2.setLinkId(R.raw.nostalgi);
        myAudio2.setName("The Scents of Nostalgia");
        myAudio2.setAuth("Xu Qing-Yuan");
        myAudio3.setLinkId(R.raw.sound);
        myAudio3.setName("Shout To The Lord");
        myAudio3.setAuth("Judson Mancebo");

        arrayList.add(myAudio0);
        arrayList.add(myAudio1);
        arrayList.add(myAudio2);
        arrayList.add(myAudio3);


        ((MainActivity) getActivity()).stopSound();

        myListAdapter = new ListMusicAdapter(new PlayMyMusic() {
            @Override
            public void pressPosition(int position, Boolean play_status) {

                ((MainActivity)getActivity()).playSound(arrayList.get(position).getLinkId());


                if(play_status){
                    currentMusicPosition = position;
                }else{
                    currentMusicPosition = -1;
                }
            }
        }, arrayList);




        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(myListAdapter);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().remove(ListFragment.this).commit();
            }
        });
    }
}
