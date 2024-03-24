package com.sarnavsky.pasz.nighlight2

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sarnavsky.pasz.nighlight2.databinding.MainBinding
import com.sarnavsky.pasz.nighlight2.fragments.MainFragment
import com.sarnavsky.pasz.nighlight2.util.MY_SETTINGS

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        initView()
        isFirstOpen()
        saveSettings(-1)

        openFragment(MainFragment())

        //TODO SET RIGHT GDPR
        //GDPRHelper.showGDPR(this)

    }


    private fun isFirstOpen() {
        val sp = getSharedPreferences(
            MY_SETTINGS,
            MODE_PRIVATE
        )

        val hasVisited = sp.getBoolean("hasVisited", false)
        if (!hasVisited) {
            val e = sp.edit()
            e.putBoolean("hasVisited", true)
            e.apply()
        }
    }

    private fun saveSettings(adCounter: Int) {
        val currentCount: Int = getSettings()
        if (currentCount > 0 && adCounter > -1) {
            getSharedPreferences(MY_SETTINGS, 0).edit().apply {
                putInt("NO_ADS_COUNTER", currentCount + adCounter)
                apply()
            }
        }
    }

    fun getSettings(): Int {
        val sharedPref =
            getSharedPreferences(MY_SETTINGS, 0)
        return sharedPref.getInt("NO_ADS_COUNTER", 0)
    }

    fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment)
            .commit()
    }

    private fun initView(){
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}