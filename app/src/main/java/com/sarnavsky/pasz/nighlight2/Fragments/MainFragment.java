package com.sarnavsky.pasz.nighlight2.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.sarnavsky.pasz.nighlight2.Adapters.Nightlighters;
import com.sarnavsky.pasz.nighlight2.Adapters.RecyclerViewAdapter;
import com.sarnavsky.pasz.nighlight2.Fabrica.MyObjects;
import com.sarnavsky.pasz.nighlight2.Interfaces.ChangeColors;
import com.sarnavsky.pasz.nighlight2.Interfaces.OpenColorFragment;
import com.sarnavsky.pasz.nighlight2.MainActivity;
import com.sarnavsky.pasz.nighlight2.Objects.MenuButton;
import com.sarnavsky.pasz.nighlight2.Objects.Nightlighter;
import com.sarnavsky.pasz.nighlight2.R;

import java.util.ArrayList;
import java.util.Map;


public class MainFragment extends Fragment {

    //MyADD
    public static AdView mAdView;
    AdRequest adRequest;

//CAS
    // public static CASBannerView bannerView;

    FrameLayout lock_frame;
    FragmentManager fm;
    ViewPager2 pager;
    CountDownTimer cdt;
    CountDownTimer globalTimer;
    ConstraintLayout mainBg;
    RecyclerViewAdapter adapter;
    RecyclerView rv;
    Context ctx;
    MyObjects menuItems;
    public TextView bottom_text;

    boolean showed;
    boolean chekAnim = false;
    boolean show = true;
    boolean timerStatus = false;
    boolean chekMenu = true;

    int currentBgColor = 0;
    int currentBgImage = 0;
    int currentNLColor = 0;
    int brights = 0;

    String[] colors;
    String[] bgColors;
    String[] bgNlColors;
    String[] menuColors;

    ImageView underImg;
    ImageView lock_button;
    ImageView settings_button;
    ImageView animateBg;

    // View objects to display banners:
    RelativeLayout topBannerView;
    RelativeLayout bottomBannerView;
    // Buttons to show the banners:
    Button showTopBannerButton;
    Button showBottomBannerButton;
    // Buttons to destroy the banners:
    Button hideTopBannerButton;
    Button hideBottomBannerButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ctx = view.getContext();
        FragmentActivity main = getActivity();
        fm = getParentFragmentManager();



        //chekSubscription
//        Log.i("TESTSUB", " chekSubscription");
//        PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
//            @Override
//            public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
//            }
//        };
//        BillingClient bc = BillingClient.newBuilder(getContext()).enablePendingPurchases().setListener(purchasesUpdatedListener).build();
//        bc.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingServiceDisconnected() {
//            }
//
//            @Override
//            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
//
//                Log.i("TESTSUB", billingResult + " billingResult");
//
//                QueryPurchasesParams queryPurchasesParams = QueryPurchasesParams
//                        .newBuilder()
//                        .setProductType(BillingClient.ProductType.SUBS)
//                        .build();
//
//                bc.queryPurchasesAsync(queryPurchasesParams, new PurchasesResponseListener() {
//                    @Override
//                    public void onQueryPurchasesResponse(@NonNull BillingResult billingResult, @NonNull List<Purchase> list) {
//
//
//                        Log.i("TESTSUB", list.size() + " TestMy");
//
//                        getActivity().runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//
//                                if (billingResult.getResponseCode() == 0) {
//                                    if (list.size() > 0) {
//                                        mAdView.setVisibility(View.GONE);
//                                    } else {
//                                        mAdView.loadAd(adRequest);
//                                        getParentFragmentManager().beginTransaction().add(R.id.container, new Subscription(), "subscription").commit();
//                                    }
//                                }
//
//                            }
//                        });
//
//
//                    }
//                });
//            }
 //       });


        //end chekSubscription

        //Start Global Itmer. Make all invisible exept nightlighter.
        startGlobalTimer();

        //UnityAds.SetC("privacy.consent", true);

        //MyADMOB ADD
        //findAdsView
        mAdView = view.findViewById(R.id.adView);



        //MyADMOB ADD
        //Use ADDS
        setAdsSetting();
        //MyADMOB ADD
        adRequest = new AdRequest.Builder().build();


        int addCounter = ((MainActivity) getActivity()).getSettings();
        if (addCounter > 0) {
            //MyADMOB ADD
            mAdView.setVisibility(View.GONE);

        } else {
            //MyADMOB ADD
            mAdView.loadAd(adRequest);
        }


        mainBg = (ConstraintLayout) view.findViewById(R.id.mainBg);
        menuItems = new MyObjects();
        lock_button = view.findViewById(R.id.lock_button);
        settings_button = view.findViewById(R.id.settings_button);

        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SettingsFragment settingsFragment = (SettingsFragment) getParentFragmentManager().findFragmentByTag("SETTINGS_FRAGMENT");
                if (settingsFragment == null) {
                    getParentFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.from_left, R.anim.disepire)
                            .replace(R.id.mainContainer, new SettingsFragment(), "SETTINGS_FRAGMENT")
                            .commit();
                }

            }
        });

        lock_button = (ImageView) view.findViewById(R.id.lock_button);
        animateBg = (ImageView) view.findViewById(R.id.animateBg);
        lock_frame = view.findViewById(R.id.lock_frame);

        lock_frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showButtons();
                startGlobalTimer();
                return false;
            }
        });

        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(llm);
        Resources res = getResources();
        colors = res.getStringArray(R.array.myColors);
        bgColors = res.getStringArray(R.array.bgColors);
        bgNlColors = res.getStringArray(R.array.bgNlColors);
        menuColors = res.getStringArray(R.array.menuColors);

        ArrayList<Nightlighter> arrayList = new ArrayList<>();
        MyObjects myMenuItems = new MyObjects();
        arrayList.addAll(myMenuItems.getNightlighters());

        pager = view.findViewById(R.id.pager);
        pager.setAdapter(new Nightlighters(arrayList, bgNlColors));
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottom_text = view.findViewById(R.id.bottom_text);
                bottom_text.setText(arrayList.get(pager.getCurrentItem()).getName());
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                underImg = pager.findViewById(R.id.underImg);
            }
        });

        lock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockButton();
            }
        });

        openMenu(menuItems.getMenuButtons(colors));
    }

    public void openMenu(ArrayList<MenuButton> menuButtons) {

        adapter = new RecyclerViewAdapter(menuButtons, menuColors);
        adapter.MyOnclick(new ChangeColors() {
            @Override
            public void onclick(int button) {
                switch (button) {
                    case 1:
                        ListFragment listFragment = (ListFragment) getChildFragmentManager().findFragmentByTag("LIST_FRAGMENT");

                        if (listFragment == null) {
                            getParentFragmentManager()
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.from_bottom, R.anim.disepire)
                                    .replace(R.id.mainContainer, new ListFragment(), "LIST_FRAGMENT")
                                    .commit();
                        }
                        break;
                    case 2:
                        showBgColorMenu();
                        break;
                    case 3:
                        changeNLColor();
                        break;
                    case 4:
                        startAnimation();

                        break;
                    case 6:
                        changeBgColor();
                        break;
                    case 5:
                        TimerFragment timer_fragment = (TimerFragment) getChildFragmentManager().findFragmentByTag("TIMER_FRAGMENT");

                        if (timer_fragment == null) {
                            getParentFragmentManager()
                                    .beginTransaction()
                                    .setCustomAnimations(R.anim.from_bottom, R.anim.disepire)
                                    .replace(R.id.mainContainer, new TimerFragment(), "TIMER_FRAGMENT")
                                    .commit();
                        }

                        break;
                    case 7:
                        changeBrighest();
                        break;
                }
            }
        });
        adapter.MyOnLongclick(new OpenColorFragment() {
            @Override
            public void onclick(int button) {
                switch (button) {
                    case 3:
                        getParentFragmentManager()
                                .beginTransaction()
                                .replace(R.id.mainContainer, ColorPicker.init(0), "ColorPicker")
                                .commit();
                        break;
                    case 2:
                        getParentFragmentManager()
                                .beginTransaction()
                                .replace(R.id.mainContainer, ColorPicker.init(1), "ColorPicker")
                                .commit();
                        break;
                }
            }
        });
        rv.setAdapter(adapter);
    }

    private void showBgColorMenu() {

        currentBgColor++;
        if (currentBgColor == bgColors.length) {
            currentBgColor = 0;
        }

        mainBg.setBackgroundColor(Color.parseColor(bgColors[currentBgColor]));
    }

    public void changeBrighest() {
        WindowManager.LayoutParams layout = getActivity().getWindow().getAttributes();

        switch (brights) {
            case 0:
                layout.screenBrightness = 0.1F;
                brights++;
                break;
            case 1:
                layout.screenBrightness = 0.5F;
                brights++;
                break;
            case 2:
                layout.screenBrightness = 1F;

                brights = 0;
                break;
        }

        getActivity().getWindow().setAttributes(layout);
    }

    public void startAnimation() {

        animateBg.setScaleType(ImageView.ScaleType.FIT_CENTER);

        if (!chekAnim) {
            RotateAnimation rotate = new RotateAnimation(0f, 360f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f); //8
            rotate.setDuration(100000);
            rotate.setRepeatCount(Animation.INFINITE);
            rotate.setInterpolator(new LinearInterpolator());

            AnimationSet set = new AnimationSet(false); //10
            set.addAnimation(rotate);
            animateBg.startAnimation(set);
            chekAnim = true;

            //float mySacele = (float) getResources().getDimension(R.dimen.scale);
           // Log.i("DIMEN", mySacele+"ddd");

            TypedValue outValue = new TypedValue();
            getResources().getValue(R.dimen.scale, outValue, true);
            float value = outValue.getFloat();
            Log.i("DSSD",value+"  d");

            ScaleAnimation scale = new ScaleAnimation(1f, value, 1f, value,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scale.setDuration(1000);
            animateBg.startAnimation(scale);

            set.addAnimation(scale);
            animateBg.startAnimation(set);


        } else {
            animateBg.clearAnimation();
            animateBg.setScaleType(ImageView.ScaleType.CENTER_CROP);
            chekAnim = false;
        }

    }

    public void lockButton() {

        int showAdd = ((MainActivity) getActivity()).getSettings();
        if (chekMenu) {
            lock_frame.setClickable(true);
            openMenu(menuItems.getMenuButtons(colors));
            rv.setVisibility(View.INVISIBLE);
            bottom_text.setVisibility(View.GONE);
            settings_button.setVisibility(View.GONE);

            int frCount = getChildFragmentManager().getFragments().size();
            //Toast.makeText(ctx, frCount+" ddd", Toast.LENGTH_SHORT).show();
            if (frCount > 0) {
                for (int i = 0; i < frCount; i++) {
                    Fragment fragment = getChildFragmentManager().getFragments().get(i);
                    getChildFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }

            lock_button.setImageResource(R.drawable.ic_lock);

            if (showAdd > 0) {

            } else {
                //MyADMOB ADD
                mAdView.setVisibility(View.INVISIBLE);
            }


            lock_frame.setClickable(true);
            chekMenu = false;
            show = false;
        } else {

            lock_frame.setClickable(false);
            rv.setVisibility(View.VISIBLE);
            bottom_text.setVisibility(View.VISIBLE);
            lock_button.setImageResource(R.drawable.ic_unlock);

            if (showAdd > 0) {

            } else {
                //MyADMOB ADD
                mAdView.setVisibility(View.VISIBLE);
            }


            settings_button.setVisibility(View.VISIBLE);
            lock_frame.setClickable(false);
            chekMenu = true;
            show = true;
        }

    }

    public void startTimer(int hours, int minutes) {
        bottom_text.setVisibility(View.VISIBLE);
        int mySeconds = (((hours * 60 * 60) + (60 * minutes)) * 1000);
        closeApp(mySeconds);
    }

    public void closeApp(int mySeconds) {

        if (cdt != null) {
            timerStatus = false;
            cdt.cancel();
            cdt = null;
        }
        if (mySeconds > 0) {
            timerStatus = true;
            cdt = new CountDownTimer(mySeconds, 1000) {
                @SuppressLint("DefaultLocale")
                @Override
                public void onTick(long l) {
                    bottom_text.setText(String.format("%02d:%02d:%02d", (l / 1000) / 3600, ((l / 1000) % 3600) / 60, (l / 1000) % 60));
                }

                @Override
                public void onFinish() {

                    if (cdt != null) {
                        cdt.cancel();
                    }

                    if (globalTimer != null) {
                        globalTimer.cancel();
                    }

                    ((MainActivity) getActivity()).finishMedia();

                    Log.i("FINISH", "App is OFF");
                    getActivity().finish();
                }
            };
            cdt.start();
        } else {
            bottom_text.setText("");
            bottom_text.setVisibility(View.INVISIBLE);
        }
    }

    private void setAdsSetting() {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                for (String adapterClass : statusMap.keySet()) {
                    AdapterStatus status = statusMap.get(adapterClass);
                    Log.d("MyApp", String.format(
                            "Adapter name: %s, Description: %s, Latency: %d",
                            adapterClass, status.getDescription(), status.getLatency()));
                }

                // Start loading ads here...
            }
        });

        RequestConfiguration requestConfiguration = MobileAds.getRequestConfiguration()
                .toBuilder()
                .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);

    }

    private void changeBgColor() {

        currentBgColor++;
        currentBgImage++;

        if (currentBgImage >= menuItems.getBgArray().length) {
            currentBgImage = 0;
            currentBgColor = 0;
        }

        if (currentBgColor >= bgColors.length) {
            currentBgColor = 0;
        }

        mainBg.setBackgroundColor(Color.parseColor(bgColors[currentBgColor]));
        animateBg.setImageResource(menuItems.getBgArray()[currentBgImage]);
    }

    private void changeNLColor() {

        currentNLColor++;

        if (currentNLColor >= bgNlColors.length) {
            currentNLColor = 0;
        }

        underImg.setColorFilter(Color.parseColor(bgNlColors[currentNLColor]));
    }

    private void startGlobalTimer() {
        if (globalTimer != null) {
            globalTimer.start();
        } else {
            globalTimer = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                    if (lock_button != null) {
                        lock_button.setVisibility(View.INVISIBLE);
                    }
                    if (bottom_text != null) {
                        bottom_text.setVisibility(View.INVISIBLE);
                    }
                    if (settings_button != null) {
                        settings_button.setVisibility(View.INVISIBLE);
                    }
                    if (rv != null) {
                        rv.setVisibility(View.INVISIBLE);
                    }
                }
            }.start();
        }
    }

    private void showButtons() {
        if (chekMenu) {
            lock_button.setVisibility(View.VISIBLE);
            bottom_text.setVisibility(View.VISIBLE);
            settings_button.setVisibility(View.VISIBLE);
            rv.setVisibility(View.VISIBLE);
        } else {
            lock_button.setVisibility(View.VISIBLE);
            bottom_text.setVisibility(View.VISIBLE);
        }
    }
}
