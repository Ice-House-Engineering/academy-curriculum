package com.example.helloanimation17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

private const val NUM_PAGES = 5

class MainActivity : AppCompatActivity() {

    private lateinit var mPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPager = findViewById(R.id.pager)

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(SimplePageTransformer())
    }

    private inner class ScreenSlidePagerAdapter(fa: AppCompatActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment.newInstance(position.toString())
    }
}
