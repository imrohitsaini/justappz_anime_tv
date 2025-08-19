package com.justappz.aniyomitv.presentation

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.justappz.aniyomitv.databinding.ActivityMainBinding
import com.justappz.aniyomitv.presentation.fragments.ExploreFragment
import com.justappz.aniyomitv.presentation.fragments.HomeFragment
import com.justappz.aniyomitv.presentation.fragments.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    //region variables
    private lateinit var binding: ActivityMainBinding
    private lateinit var activity: Activity
    //endregion

    //region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activity = this

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
    }
    //endregion

    //region init
    private fun init() {
        binding.tvHome.setOnClickListener(this)
        binding.tvExplore.setOnClickListener(this)
        binding.tvSettings.setOnClickListener(this)
        loadFragment(HomeFragment(), binding.tvHome)
    }
    //endregion

    //region onClick
    override fun onClick(v: View?) {
        v?.let {
            when (v) {
                binding.tvHome -> {
                    loadFragment(HomeFragment(), binding.tvHome)
                }

                binding.tvExplore -> {
                    loadFragment(ExploreFragment(), binding.tvExplore)
                }

                binding.tvSettings -> {
                    loadFragment(SettingsFragment(), binding.tvSettings)
                }
            }
        }
    }
    //endregion

    //region loadFragment
    private fun loadFragment(fragment: Fragment, selectedView: View) {
        if (selectedView.isSelected) return
        binding.tvHome.isSelected = selectedView == binding.tvHome
        binding.tvExplore.isSelected = selectedView == binding.tvExplore
        binding.tvSettings.isSelected = selectedView == binding.tvSettings
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
    //endregion
}