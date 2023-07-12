package com.childrenswatches.pasz.usefulalertdialog.Objects;

import android.view.View;

import java.io.Serializable;

public class Alert implements Serializable {
    private String text;
    private String message;
    private Boolean positiveButton;
    private String textPositiveButton;
    private int positiveButtonImage;

    private View.OnClickListener positiveOnClick;
    private View.OnClickListener negativeButton;
    private Boolean backButton;
    private int backButtonImage;
    private int topImage;
    private int bottomImage;





    public int getBottomImage() {
        return bottomImage;
    }

    public void setBottomImage(int bottomImage) {
        this.bottomImage = bottomImage;
    }

    public void setTopImage(int topImage) {
        this.topImage = topImage;
    }

    public int getTopImage() {
        return topImage;
    }




    public void setBackButton(Boolean backButton,
                              int backButtonImage){

        this.backButton = backButton;
        this.backButtonImage = backButtonImage;

    }

    public int getBackButtonImage() {
        return backButtonImage;
    }

    public Boolean getBackButton() {
        return backButton;
    }


    public void setPositiveButton(Boolean positiveButton, String textPositiveButton, int positiveButtonImage, View.OnClickListener positiveOnClick) {
        this.positiveButton = positiveButton;
        this.textPositiveButton = textPositiveButton;
        this.positiveButtonImage = positiveButtonImage;
        this.positiveOnClick = positiveOnClick;
    }


    public View.OnClickListener getPositiveOnClick() {
        return positiveOnClick;
    }

    public Boolean getPositiveButton() {
        return positiveButton;
    }

    public int getPositiveButtonImage() {
        return positiveButtonImage;
    }

    public String getTextPositiveButton() {
        return textPositiveButton;
    }



    public void setNegativeButton(View.OnClickListener negativeButton) {
        this.negativeButton = negativeButton;
    }

    public View.OnClickListener getNegativeButton() {
        return negativeButton;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
