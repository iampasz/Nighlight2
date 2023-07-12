package com.childrenswatches.pasz.mylibrary.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.childrenswatches.pasz.mylibrary.Objects.Alert;
import com.childrenswatches.pasz.mylibrary.R;


public class AlertFragment extends Fragment {


    public static AlertFragment init (Alert alert){
        Bundle bundle = new Bundle();
        bundle.putSerializable("alert", alert);

        AlertFragment alertFragment = new AlertFragment();

        alertFragment.setArguments(bundle);
        return alertFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alert_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       final Alert alert = (Alert) getArguments().getSerializable("alert");

        RelativeLayout relative = (RelativeLayout) view.findViewById(R.id.relative);

        relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAlert();
            }
        });

       TextView title = (TextView) view.findViewById(R.id.title);
       TextView text_massage = (TextView) view.findViewById(R.id.text_massage);
       final Button positive_button = (Button) view.findViewById(R.id.positive_button);
       final Button negative_button = (Button) view.findViewById(R.id.negative_button);
       Button closeButton = (Button) view.findViewById(R.id.close_button);

        ImageView topImage = (ImageView) view.findViewById(R.id.topImage);
        ImageView bottomImage = (ImageView) view.findViewById(R.id.bottomImage);

        if(alert.getBottomImage()!=0 ){
            bottomImage.setImageResource(alert.getBottomImage());
        }else{
            bottomImage.setVisibility(View.GONE);
        }

       if(alert.getTopImage()!=0) {
           topImage.setImageResource(alert.getTopImage());
       }else{
           topImage.setVisibility(View.GONE);
       }

       if(alert.getBackButton()!=null && alert.getBackButton()==true){

           if(alert.getBackButtonImage()!=0){
               closeButton.setText("");
               closeButton.setBackgroundResource(alert.getBackButtonImage());
           }

       }else{
           closeButton.setVisibility(View.INVISIBLE);
       }

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAlert();
            }
        });

        if(alert.getPositiveButton()!=null && alert.getPositiveButton()){
            if(alert.getTextPositiveButton()!=null){
                positive_button.setText(alert.getTextPositiveButton());
            }

            if(alert.getPositiveButtonImage()!=0){
                positive_button.setBackgroundResource(alert.getPositiveButtonImage());
            }

        }else{
            positive_button.setVisibility(View.GONE);
        }

        positive_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alert.getPositiveOnClick().onClick(positive_button);
                closeAlert();
            }


        });


        negative_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alert.getNegativeOnClick().onClick(negative_button);
                closeAlert();

            }
        });


       title.setText(alert.getText());
        text_massage.setText(alert.getMessage());


    }

    public void closeAlert(){
        getActivity().getFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out).remove(AlertFragment.this).commit();
    }
}
