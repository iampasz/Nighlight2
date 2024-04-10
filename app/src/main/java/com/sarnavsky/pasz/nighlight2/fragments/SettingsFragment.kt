package com.sarnavsky.pasz.nighlight2.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.sarnavsky.pasz.nighlight2.MainActivity
import com.sarnavsky.pasz.nighlight2.R
import com.sarnavsky.pasz.nighlight2.databinding.SettingsFragmentBinding
import com.sarnavsky.pasz.nighlight2.util.BG_COLOR_BUTTON

class SettingsFragment : Fragment() {

    private lateinit var binding: SettingsFragmentBinding
    private var adsCounter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adsCounter = (activity as MainActivity?)!!.getSettings()
        binding.adsText.text = resources.getString(R.string.disable_ads_ses) + " " + adsCounter
        if (adsCounter < 1) {
            binding.adsText.setTextColor(Color.RED)
        } else {
            binding.adsText.setTextColor(Color.parseColor("#2C8005"))
        }
        binding.nlBg.setOnClickListener {

            val fragment = ColorPickerFragment().apply {
                arguments = Bundle().apply {
                    putInt("type", BG_COLOR_BUTTON) // Ваше int значення
                }
            }

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .commit()
        }
        binding.bgColor.setOnClickListener {
            val fragment = ColorPickerFragment().apply {
                arguments = Bundle().apply {
                    putInt("type", BG_COLOR_BUTTON) // Ваше int значення
                }
            }

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .commit()
        }
        binding.showAdd.setOnClickListener {
//            val status = (activity as MainActivity?)!!.showAds { shown ->
//                if (shown) {
//
//                    //MainFragment.mAdView.setVisibility(View.GONE);
//
//                    //MainFragmentOld.mAdView.setVisibility(View.GONE);
//                    adsCounter = (activity as MainActivity?)!!.getSettings()
//                    binding.adsText.text = "No ADS less: $adsCounter"
//                    binding.adsText.setTextColor(Color.parseColor("#2C8005"))
//                }
//            }
//            if (!status) {
//                binding.adsText.text = resources.getString(R.string.no_ads_message)
//                binding.adsText.setTextColor(Color.RED)
//            }
        }
        val close2 = view.findViewById<Button>(R.id.close2)
        close2.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this@SettingsFragment).commit()
        }
       binding.linearDownload.setOnClickListener {
           val builder = AlertDialog.Builder(context, R.style.MyDialogTheme)
           val inflater = layoutInflater
           val dialogLayout: View = inflater.inflate(R.layout.custom_dialog_layout, null)
           builder.setView(dialogLayout)
           val positiveButton = dialogLayout.findViewById<Button>(R.id.positive_button)
           val negativeButton = dialogLayout.findViewById<Button>(R.id.negative_button)

           val dialog = builder.create()
           positiveButton.setOnClickListener {
               val browserIntent = Intent(
                   Intent.ACTION_VIEW,
                   Uri.parse("https://play.google.com/store/apps/details?id=com.appsforkids.pasz.nightlightpromax")
               )
               startActivity(browserIntent)
               dialog.dismiss()
           }
           negativeButton.setOnClickListener { dialog.dismiss() }


           //dialog.getWindow().setBackgroundDrawableResource(R.drawable.shape_menu4c);
           dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
           dialog.show()
       }
    }

}