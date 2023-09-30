package com.sarnavsky.pasz.nighlight2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;
import com.sarnavsky.pasz.nighlight2.Fragments.MainFragment;
import com.sarnavsky.pasz.nighlight2.Interfaces.MyCallback;

import java.io.IOException;



public class MainActivity extends AppCompatActivity {

    private static final String MY_SETTINGS = "my_settings";
    private ConsentInformation consentInformation;

    //MyADD
    private InterstitialAd mInterstitialAd;
    private RewardedAd rewardedAd;

    public int oldLinkId = 0;

    FragmentManager fm;
    AdRequest adRequest;
    MediaPlayer mediaPlayer;
    ConsentForm consentForm;

   // public static boolean subscribleStatus;

//    MediationManager manager;
//    AdCallback callback;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //SetFullScrean
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Log.i("ADDCOUNTER", getSettings() + " Реклама отключена");

        //My ADMOB
        adRequest = new AdRequest.Builder().build();
        loadADS();
        loadAdRequest();

        isFirstOpen();
        saveSettings(-1);

        //loadMainFragment
        fm = getSupportFragmentManager();
        MainFragment mainFragment = new MainFragment();
        fm.beginTransaction().replace(R.id.container, mainFragment, "main_fragment").commit();


        //showGDPR
        showGDPR();
    }


    private void showGDPR() {
        ConsentDebugSettings debugSettings = new ConsentDebugSettings.Builder(this)
                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                //.addTestDeviceHashedId("E0935214FE561DA3BC3339B2BC22329A")
                //.addTestDeviceHashedId("DCCF5D3E5E3AFB3F9195DAAFD5EDEEC6")
                .build();
        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                .setConsentDebugSettings(debugSettings)
                .setTagForUnderAgeOfConsent(false)
                .build();
        ConsentInformation.OnConsentInfoUpdateSuccessListener inform = new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
            @Override
            public void onConsentInfoUpdateSuccess() {
                loadForm();
            }
        };
        ConsentInformation.OnConsentInfoUpdateFailureListener infoFaild = new ConsentInformation.OnConsentInfoUpdateFailureListener() {
            @Override
            public void onConsentInfoUpdateFailure(FormError formError) {
                //  Log.i("TESTID", formError.getMessage() + "onConsentInfoUpdateFailure");
            }
        };

        consentInformation = UserMessagingPlatform.getConsentInformation(this);
        consentInformation.requestConsentInfoUpdate(MainActivity.this, params, inform, infoFaild);

        // consentInformation.reset();
    }

    public void loadForm() {
        // Loads a consent form. Must be called on the main thread.

        UserMessagingPlatform.loadConsentForm(
                this,
                new UserMessagingPlatform.OnConsentFormLoadSuccessListener() {
                    @Override
                    public void onConsentFormLoadSuccess(ConsentForm consentForm) {
                        MainActivity.this.consentForm = consentForm;
                        if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.REQUIRED) {
                            consentForm.show(
                                    MainActivity.this,
                                    new ConsentForm.OnConsentFormDismissedListener() {
                                        @Override
                                        public void onConsentFormDismissed(@Nullable FormError formError) {
                                            if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.OBTAINED) {
                                                // App can start requesting ads.
                                            }

                                            // Handle dismissal by reloading form.
                                            loadForm();
                                        }
                                    });
                        }
                    }
                },
                new UserMessagingPlatform.OnConsentFormLoadFailureListener() {
                    @Override
                    public void onConsentFormLoadFailure(FormError formError) {
                        // Handle Error.
                        //  Log.i("TESTID", formError.getMessage() + "loadForm efef");
                    }
                }
        );
    }

    private void isFirstOpen() {

        SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        // проверяем, первый ли раз открывается программа
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        if (!hasVisited) {
            // выводим нужную активность
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit(); // не забудьте подтвердить изменения

        }
    }

    public void playSound(int linkId) {

        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.reset();
            mediaPlayer = null;
        }

        if (oldLinkId == linkId) {
            oldLinkId = 0;
        } else {
            mediaPlayer = MediaPlayer.create(this,
                    linkId);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            oldLinkId = linkId;
        }
    }

    public void playInternetSound(String link) {
        mediaPlayer = new MediaPlayer();
        try {
            // mediaPlayer.setDataSource(String.valueOf(myUri));
            mediaPlayer.setDataSource(MainActivity.this, Uri.parse(link));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    public void stopSound() {
        oldLinkId = 0;
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.reset();
            mediaPlayer = null;
        }
    }

    public void finishMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.reset();
            mediaPlayer = null;
        }
    }

    public void saveSettings(int adCounter) {

        int currentCount = getSettings();
        if (currentCount < 1 && adCounter == -1) {

        } else {
            SharedPreferences sharedPref = this.getSharedPreferences(MY_SETTINGS, 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("NO_ADS_COUNTER", currentCount + adCounter);
            editor.apply();
        }

        Log.i("ADDCOUNTER", currentCount + " Реклама отключена");
    }

    public int getSettings() {
        SharedPreferences sharedPref = this.getSharedPreferences(MY_SETTINGS, 0);
        int current_item = sharedPref.getInt("NO_ADS_COUNTER", 0);
        return current_item;
    }

    //My ADD ADMOB

    private void loadADS() {
        InterstitialAd.load(this, "ca-app-pub-1237459888817948/7124868163", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("TAG", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("TAG", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }

    //
    public void showAlertADS() {
        if (getSettings() > 0) {
        } else {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(this);
                loadADS();
                pauseMediaPlayer();

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();

                        resumeMediaPlayer();
                        SharedPreferences newShPref = getSharedPreferences(MY_SETTINGS, 0);
//                        boolean answer = newShPref.getBoolean("NOADS", false);
//
//                        if (answer) {
//
//                        } else {
//                            getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, new ToastFragment(), "TOAST_FRAGMENT").commit();
//                        }
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                    }
                });
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }
        }
    }

    private void loadAdRequest() {
        RewardedAd.load(this,
                "ca-app-pub-1237459888817948/3091783062",
                adRequest,
                new RewardedAdLoadCallback() {

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("TAG", loadAdError.toString());
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        super.onAdLoaded(rewardedAd);
                    }
                });
    }

    public boolean showAds(MyCallback myCallback) {

        boolean status = false;

        if (rewardedAd != null) {
            status = true;
            Activity activityContext = this;
            rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d("REWARDED", "The user earned the reward.");
                    saveSettings(2);
                    myCallback.isShown(true);
                    pauseMediaPlayer();
                }
            });


            rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d("TAG", "Ad was clicked.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    Log.d("REWARDED", "Ad dismissed fullscreen content.");
                    rewardedAd = null;
                    resumeMediaPlayer();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when ad fails to show.
                    Log.e("REWARDED", "Ad failed to show fullscreen content.");
                    rewardedAd = null;
                }

                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    Log.d("REWARDED", "Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    Log.d("REWARDED", "Ad showed fullscreen content.");
                    pauseMediaPlayer();
                }
            });


        } else {
            Log.d("REWARDED", "The rewarded ad wasn't ready yet.");
        }

        loadAdRequest();

        return status;
    }

    public void pauseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }

    }

    public void resumeMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.stop();
            mediaPlayer = null;
        }

        System.exit(0);

    }


}

