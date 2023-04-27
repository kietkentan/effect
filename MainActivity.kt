package com.khtn.chatp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.khtn.chatp.R
import com.khtn.chatp.adapter.ViewPagerAdapter
import com.khtn.chatp.databinding.ActivityMainBinding
import com.khtn.chatp.fragment.BlankFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentList = mutableListOf<Fragment>()
    private val titleList = mutableListOf<String>()

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        titleList.add("text one".uppercase())
        titleList.add("text two".uppercase())
        titleList.add("text three".uppercase())
        fragmentList.add(BlankFragment())
        fragmentList.add(BlankFragment())
        fragmentList.add(BlankFragment())

        binding.viewPager.adapter = ViewPagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { _, _ ->

        }.attach()

        for (i in 0 until titleList.size) {
            val tv = LayoutInflater.from(baseContext).inflate(R.layout.tab, null)
                    as TextView
            tv.text = titleList[i]
            binding.tabLayout.getTabAt(i)?.customView = tv
        }

        setContentView(binding.root)
    }
}