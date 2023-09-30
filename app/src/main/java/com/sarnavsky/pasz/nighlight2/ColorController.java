//package com.sarnavsky.pasz.nighlight2;
//
//import android.graphics.Color;
//import android.os.CountDownTimer;
//import android.util.Log;
//import android.widget.ImageView;
//
//public class ColorController {
//
//    int red = 255;
//    int grean = 195;
//    int blue = 0;
//    ImageView imageView;
//
//    public ColorController(ImageView imageView){
//        this.imageView = imageView;
//    }
//
//    CountDownTimer countDownTimer = new CountDownTimer(1,1) {
//        @Override
//        public void onTick(long millisUntilFinished) {
//            changeColor();
//        }
//
//        @Override
//        public void onFinish() {
//            start();
//        }
//    };
//
//    public void changeColor() {
//        if (red < 255 && grean == 0 && blue == 0) {
//            red++;
//        }
//        if (red == 255 && grean < 255 && blue < 255) {
//            grean++;
//        }
//        if (red > 0 && grean == 255 && blue < 255) {
//            red--;
//        }
//        if (red == 0 && grean == 255 && blue < 255) {
//            blue++;
//        }
//        if (red == 0 && grean > 0 && blue == 255) {
//            grean--;
//        }
//        if (red == 0 && grean == 0 && blue > 0) {
//            blue--;
//        } else {
//            Log.d("AAAA", red+"    red" +grean+"   "+blue+"");
//        }
//
//        int myColor = Color.rgb(red, grean, blue);
//        imageView.setColorFilter(myColor);
//
//    }
//
//    public void startChangeColor(){
//            countDownTimer.start();
//    }
//
//    public void stopChangeColor(){
//        countDownTimer.cancel();
//    }
//}
