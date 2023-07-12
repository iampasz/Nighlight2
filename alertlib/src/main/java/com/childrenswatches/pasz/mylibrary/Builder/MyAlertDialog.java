package com.childrenswatches.pasz.mylibrary.Builder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.childrenswatches.pasz.mylibrary.Fragments.AlertFragment;
import com.childrenswatches.pasz.mylibrary.Objects.Alert;

public class MyAlertDialog {

    private String name;
    private String message;
    private Context ctx;
    private Boolean positiveButton=false;
    private String textPositiveButton;
    private int positiveButtonImage;
    private View.OnClickListener positiveOnClick;
    private Boolean negativeButton=false;
    private String textNegativeButton;
    private int negativeButtonImage;
    private View.OnClickListener negativeOnClick;
    private Boolean backButton;
    private int backButtonImage;
    private int topImage;
    private int bottomImage;
    int id;

 public MyAlertDialog(Context ctx,
                      String name,
                      String message,
                      Boolean positiveButton,
                      String textPositiveButton,
                      int positiveButtonImage,
                      View.OnClickListener positiveOnClick,
                      Boolean negativeButton,
                      String textNegativeButton,
                      int negativeButtonImage,
                      View.OnClickListener negativeOnClick,
                      Boolean backButton,
                      int backButtonImage,
                      int topImage,
                      int bottomImage) {
        this.name = name;
        this.message = message;
        this.ctx = ctx;
        this.positiveButton = positiveButton;
        this.textPositiveButton = textPositiveButton;
        this.positiveButtonImage = positiveButtonImage;
        this.positiveOnClick = positiveOnClick;
        this.negativeButton = negativeButton;
        this.textNegativeButton = textNegativeButton;
        this.negativeButtonImage = negativeButtonImage;
        this.negativeOnClick = negativeOnClick;
        this.backButton = backButton;
        this.backButtonImage = backButtonImage;
        this.topImage = topImage;
        this.bottomImage = bottomImage;


    }

    public Builder show(){

     Alert alert = new Alert();
     alert.setText(name);
     alert.setMessage(message);
     alert.setPositiveButton(positiveButton, textPositiveButton, positiveButtonImage, positiveOnClick);
     alert.setNegativeButton(negativeButton, textNegativeButton, negativeButtonImage, negativeOnClick);

     alert.setBackButton(backButton, backButtonImage);
     alert.setTopImage(topImage);
     alert.setBottomImage(bottomImage);


        FrameLayout frameLayout = new FrameLayout(ctx);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);





        //getFragmentManager().beginTransaction().add(R.id.container, AlertFragment.init(alert)).commit();
        id =  getActivity().getWindow().getDecorView().findViewById(android.R.id.content).getId();

     getActivity().getFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out).addToBackStack("").add(id, AlertFragment.init(alert)).commit();
      //  getActivity().getFragmentManager().beginTransaction().add(frameLayout.getId(), AlertFragment.init(alert)).commit();




        return null;
    }


    public Activity getActivity(){

        try{
            final Activity activity = (Activity) ctx;

            // Return the fragment manager


            return activity;

            // If using the Support lib.
            // return activity.getSupportFragmentManager();

        } catch (ClassCastException e) {
           // Log.d(TAG, "Can't get the fragment manager with this");
        }
        return null;
    }



    public static Builder newBuilder(Context context){

        return new Builder(context);
    }

    public static    class Builder {
      String title;
      String message;
      Context ctx;
      Boolean positiveButton;
      int positiveButtonImage;
      View.OnClickListener positiveOnClick;
      Boolean negativeButton;
      int negativeButtonImage;
      String textNegativeButton;
      View.OnClickListener negativeOnClick;
      Boolean backButton;
      int backButtonImage;
      String textPositive;
      int topImage;
      int bottomImage;

        public Builder(Context ctx) {
            this.ctx = ctx;
        }

        public  Builder title(String title) {
            this.title = title;

            return this;
        }

        public  Builder message(String message) {
            this.message = message;

            return this;
        }



        public Builder setPositiveButton( View.OnClickListener positiveOnClick){
            this.positiveButton = true;
            this.positiveOnClick = positiveOnClick;

            return this;
        }

        public Builder setPositiveButton(String textPositive, View.OnClickListener positiveOnClick){
            this.positiveButton = true;
            this.textPositive = textPositive;
            this.positiveOnClick = positiveOnClick;

            return this;
        }

        public Builder setPositiveButton( String textPositive, int positiveButtonImage, View.OnClickListener positiveOnClick){
            this.positiveButton = true;
            this.textPositive = textPositive;
            this.positiveButtonImage = positiveButtonImage;
            this.positiveOnClick = positiveOnClick;
            return this;
        }

        public Builder setNegativeButton(View.OnClickListener negativeOnClick){
            this.positiveButton = true;
            this.negativeOnClick = negativeOnClick;

            return this;
        }

        public Builder setNegativeButton(String text, View.OnClickListener negativeOnClick){
            this.positiveButton = true;
            this.textNegativeButton = textNegativeButton;
            this.negativeOnClick = negativeOnClick;

            return this;
        }

        public Builder setBackButton(Boolean backButton){
            this.backButton = backButton;
            return this;
        }

        public Builder setBackButton(Boolean backButton, int backButtonImage){
            this.backButton = backButton;
            this.backButtonImage = backButtonImage;
            return this;
        }

        public Builder setTopImage(int topImage){
            this.topImage = topImage;
            return this;
        }

        public Builder setBottomImage(int bottomImage){
            this.bottomImage = bottomImage;
            return this;
        }



        public MyAlertDialog build(){

            return new MyAlertDialog(
                    ctx,
                    title,
                    message,
                    positiveButton,
                    textPositive,
                    positiveButtonImage,
                    positiveOnClick,
                    negativeButton,
                    textNegativeButton,
                    negativeButtonImage,
                    negativeOnClick,
                    backButton,
                    backButtonImage,
                    topImage,
                    bottomImage);
        }

    }
}
