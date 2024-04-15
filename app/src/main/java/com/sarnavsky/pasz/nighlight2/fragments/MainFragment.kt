package com.sarnavsky.pasz.nighlight2.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import com.sarnavsky.pasz.nighlight2.MainActivity
import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.SettingsViewModel
import com.sarnavsky.pasz.nighlight2.adapters.MainMenuAdapter
import com.sarnavsky.pasz.nighlight2.adapters.NightlightersAdapter
import com.sarnavsky.pasz.nighlight2.data.db.entity.Settings
import com.sarnavsky.pasz.nighlight2.data.db.entity.Timer
import com.sarnavsky.pasz.nighlight2.databinding.MainFragmentBinding
import com.sarnavsky.pasz.nighlight2.util.ANIMATION_BUTTON
import com.sarnavsky.pasz.nighlight2.util.ANIMATION_TYPE_BUTTON
import com.sarnavsky.pasz.nighlight2.util.BG_COLOR_BUTTON
import com.sarnavsky.pasz.nighlight2.util.BRIGHTS_BUTTON
import com.sarnavsky.pasz.nighlight2.util.NL_COLOR_BUTTON
import com.sarnavsky.pasz.nighlight2.util.NightlightHelper
import com.sarnavsky.pasz.nighlight2.util.SOUNDS_BUTTON
import com.sarnavsky.pasz.nighlight2.util.TIMER_BUTTON
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.util.Random

class MainFragment : Fragment() {



    lateinit var binding: MainFragmentBinding
    private val viewModel: SettingsViewModel by activityViewModel()

    private lateinit var mySetting: Settings
    private lateinit var myTimer: Timer

    var cdt: CountDownTimer? = null

    private lateinit var bgColors: Array<String>
    private lateinit var bgNlColors: Array<String>

    private var currentBgColor = 0
    private var currentBgImage = 0
    private var currentNLColor = 0
    private var brights = 0
    private var timerIsLoaded = false
    private var checkMenu = true
    private var show = true
    private var checkAnim = false
    private var timerStatus = false

    private val mainMenuAdapter = MainMenuAdapter { menuItem, longClick ->
        if (longClick) {
            when (menuItem.button) {
                BG_COLOR_BUTTON -> showColorPicker(BG_COLOR_BUTTON)
                NL_COLOR_BUTTON -> showColorPicker(NL_COLOR_BUTTON)
            }
        } else {
            when (menuItem.button) {
                SOUNDS_BUTTON -> (requireActivity() as MainActivity).openFragment(MusicListFragment())
                BG_COLOR_BUTTON -> changeBackgroundColor()
                ANIMATION_BUTTON -> startAnimation()
                TIMER_BUTTON -> {
                    closeApp(0)
                    (requireActivity() as MainActivity).openFragment(TimerFragment())
                }

                NL_COLOR_BUTTON -> {
                    currentNLColor++
                    if (currentNLColor >= bgNlColors.size) {
                        currentNLColor = 0
                    }

                    val color = Color.parseColor(bgNlColors[currentNLColor])
                    changeNLColor(color)
                }

                ANIMATION_TYPE_BUTTON -> changeAnimationType()
                BRIGHTS_BUTTON -> changeBrightest()
            }
        }
    }
    private val nightlightersAdapter = NightlightersAdapter {

        val techniques = NightlightHelper.getTechniquesArray()
        val random = Random()
        val i = random.nextInt(techniques.size)

        YoYo.with(techniques[i])
            .duration(700)
            .playOn(binding.pager)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArrays()
        initAdapter()
        initListener()
        observer()
        initView()

        initAds()


    }

    private fun initAdapter() {
        binding.rv.adapter = mainMenuAdapter
        val listMenu = NightlightHelper.getMenuButtons(resources)
        mainMenuAdapter.list.submitList(listMenu)

        binding.pager.adapter = nightlightersAdapter
        val listNightlighters = NightlightHelper.getNightlighters()
        nightlightersAdapter.list.submitList(listNightlighters)

    }

    private fun initAds() {
        MobileAds.initialize(
            requireContext()
        ) { initializationStatus ->
            val statusMap = initializationStatus.adapterStatusMap
            for (adapterClass in statusMap.keys) {
                val status = statusMap[adapterClass]
                Log.d(
                    "MyApp", String.format(
                        "Adapter name: %s, Description: %s, Latency: %d",
                        adapterClass, status?.description, status?.latency
                    )
                )
            }

        }
        val requestConfiguration = MobileAds.getRequestConfiguration()
            .toBuilder()
            .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
            .build()
        MobileAds.setRequestConfiguration(requestConfiguration)



        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        binding.adView.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.i("ADSMY", "Code to be executed when the user clicks on an ad.")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.i("ADSMY", " Code to be executed when the user is about to return")
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.i("ADSMY", "Code to be executed when an ad request fails.")
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
                Log.i("ADSMY", "Code to be executed when an impression is recorded")
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("ADSMY", "Code to be executed when an ad finishes loading.")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.i("ADSMY", "Code to be executed when an ad opens an overlay that")
            }
        }
    }

    private fun showButtons() {
        if (checkMenu) {
            binding.lockButton.visibility = View.VISIBLE
            binding.bottomText.visibility = View.VISIBLE
            binding.settingsButton.visibility = View.VISIBLE
            binding.rv.visibility = View.VISIBLE
        } else {
            binding.lockButton.visibility = View.VISIBLE
            binding.bottomText.visibility = View.VISIBLE
        }
    }

    private fun lockButton() {
        val showAdd = (activity as MainActivity?)?.getSettings()
        if (checkMenu) {
            binding.lockFrame.isClickable = true
            // openMenu(NightlightHelper.getMenuButtons(colors))
            binding.rv.visibility = View.INVISIBLE
            binding.bottomText.visibility = View.GONE
            binding.settingsButton.visibility = View.GONE
            val frCount = childFragmentManager.fragments.size
            //Toast.makeText(ctx, frCount+" ddd", Toast.LENGTH_SHORT).show();
            if (frCount > 0) {
                for (i in 0 until frCount) {
                    val fragment = childFragmentManager.fragments[i]
                    childFragmentManager.beginTransaction().remove(fragment).commit()
                }
            }
            binding.lockButton.setImageResource(R.drawable.ic_lock)
            if (showAdd != null) {
                if (showAdd < 0) {
                    binding.adView.visibility = View.INVISIBLE
                }
            }
            binding.lockFrame.isClickable = true
            checkMenu = false
            show = false
        } else {
            binding.lockFrame.isClickable = false
            binding.rv.visibility = View.VISIBLE
            binding.bottomText.visibility = View.VISIBLE
            binding.lockButton.setImageResource(R.drawable.ic_unlock)
            if (showAdd != null) {
                if (showAdd < 0) {
                    binding.adView.visibility = View.VISIBLE
                }
            }
            binding.settingsButton.visibility = View.VISIBLE
            binding.lockFrame.isClickable = false
            checkMenu = true
            show = true
        }
    }

    private fun changeBackgroundColor() {
        currentBgColor++
        if (currentBgColor == bgColors.size) {
            currentBgColor = 0
        }
        binding.mainBg.setBackgroundColor(Color.parseColor(bgColors[currentBgColor]))
        mySetting.backgroundColor = Color.parseColor(bgColors[currentBgColor])
        //viewModel.updateSettings(mySetting)

    }

    private fun changeNLColor(color: Int) {
        nightlightersAdapter.updateImageColorsWithColor(color)
        mySetting.nightlightColor = color
    }

    private fun startAnimation() {
        binding.animateBg.scaleType = ImageView.ScaleType.FIT_CENTER
        if (!checkAnim) {
            val rotate = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            ) //8
            rotate.duration = 100000
            rotate.repeatCount = Animation.INFINITE
            rotate.interpolator = LinearInterpolator()
            val set = AnimationSet(false) //10
            set.addAnimation(rotate)
            binding.animateBg.startAnimation(set)
            checkAnim = true

            val outValue = TypedValue()
            resources.getValue(R.dimen.scale, outValue, true)
            val value = outValue.float
            val scale = ScaleAnimation(
                1f, value, 1f, value,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            )
            scale.duration = 1000
            binding.animateBg.startAnimation(scale)
            set.addAnimation(scale)
            binding.animateBg.startAnimation(set)
            mySetting.animationStatus = true
        } else {
            binding.animateBg.clearAnimation()
            binding.animateBg.scaleType = ImageView.ScaleType.CENTER_CROP
            checkAnim = false
            mySetting.animationStatus = false
        }
    }

    private fun changeAnimationType() {
        currentBgColor++
        currentBgImage++
        if (currentBgImage >= NightlightHelper.getBgArray().size) {
            currentBgImage = 0
            currentBgColor = 0
        }
        if (currentBgColor >= bgColors.size) {
            currentBgColor = 0
        }

        binding.animateBg.setImageResource(NightlightHelper.getBgArray()[currentBgImage])
        mySetting.animationType = NightlightHelper.getBgArray()[currentBgImage]
    }

    private fun changeBrightest() {
        val layout = activity?.window?.attributes
        when (brights) {
            0 -> {
                layout?.screenBrightness = 0.1f
                brights++
            }

            1 -> {
                layout?.screenBrightness = 0.5f
                brights++
            }

            2 -> {
                layout?.screenBrightness = 1f
                brights = 0
            }
        }
        activity?.window?.attributes = layout
    }

    private fun closeApp(mySeconds: Int) {
        if (cdt != null) {
            timerStatus = false
            cdt?.cancel()
            cdt = null
        }
        if (mySeconds > 0) {
            timerStatus = true
            cdt = object : CountDownTimer(mySeconds.toLong(), 1000) {
                @SuppressLint("DefaultLocale")
                override fun onTick(l: Long) {
                    binding.bottomText.text = String.format(
                        "%02d:%02d:%02d",
                        l / 1000 / 3600,
                        l / 1000 % 3600 / 60,
                        l / 1000 % 60
                    )
                }

                override fun onFinish() {
                    if (cdt != null) {
                        cdt?.cancel()
                    }
                    Log.i("FINISH", "App is OFF")
                    activity?.finish()
                }

            }
            cdt?.start()

        } else {
            binding.bottomText.text = ""
            binding.bottomText.visibility = View.INVISIBLE
        }
    }

    private fun observer() {

        viewModel.getSettings()
        viewModel.getTimer()

        viewModel.timerLiveData.observe(viewLifecycleOwner){
            if(it == null){
                viewModel.insertTimer()
            }else{
                myTimer = it
                timerIsLoaded = true
                if (it.timerStatus) {
                    binding.bottomText.visibility = View.VISIBLE
                    closeApp(it.timerDuration)
                }
            }

        }

        viewModel.settingsLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                viewModel.insertItem()
            } else {
                mySetting = it


                binding.mainBg.setBackgroundColor(it.backgroundColor)
                changeNLColor(it.nightlightColor)
                binding.pager.currentItem = it.currentNightlight

                binding.animateBg
                    .setImageResource(it.animationType)

                if (it.animationStatus) {
                    startAnimation()
                }

            }
        }
    }

    private fun showColorPicker(type: Int) {
        val colorPickerDialog = ColorPickerDialog
            .newBuilder()
            .setSelectedButtonText(R.string.selected_button)
            .setDialogTitle(R.string.dialog_title)
            .setCustomButtonText(R.string.custom_button)
            .setPresetsButtonText(R.string.presets_button)
            .setColor(Color.RED)
            .create()
        colorPickerDialog.show(parentFragmentManager, "")

        colorPickerDialog.setColorPickerDialogListener(
            object : ColorPickerDialogListener {
                override fun onColorSelected(dialogId: Int, color: Int) {

                    when (type) {
                        BG_COLOR_BUTTON -> {
                            mySetting.backgroundColor = color
                            binding.mainBg.setBackgroundColor(color)
                        }

                        NL_COLOR_BUTTON -> {
                            mySetting.nightlightColor = color
                            changeNLColor(color)
                        }
                    }
                }

                override fun onDialogDismissed(dialogId: Int) {
                }
            })
    }

    override fun onStop() {
        super.onStop()
        mySetting.currentNightlight = binding.pager.currentItem
        myTimer.timerStatus = false
        viewModel.updateSettings(mySetting)
        viewModel.updateTimer(myTimer)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener(){
        binding.settingsButton.setOnClickListener {
            (requireActivity() as MainActivity).openFragment(SettingsFragment())
        }
        binding.lockFrame.setOnTouchListener { _, _ ->
            showButtons()
            false
        }
        binding.lockButton.setOnClickListener { lockButton() }
    }

    private fun initView(){
        val arrayList = NightlightHelper.getNightlighters()
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.bottomText.setText(arrayList[position].name)

                if (timerIsLoaded) {
                    val updatedTimer = myTimer.copy(
                        timerDuration = 9999,
                        timerStatus = false,
                    )
                    viewModel.updateTimer(updatedTimer)
                }
            }
        })

    }

    private fun initArrays(){
        bgColors = resources.getStringArray(R.array.bgColors)
        bgNlColors = resources.getStringArray(R.array.bgNlColors)
    }



}