package com.example.helloviewpager1

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class BasicAdapter(mgr : FragmentManager, private val context : Context) : FragmentPagerAdapter(mgr) {

    override fun getCount() = 10

    override fun getItem(number: Int) = TextViewFragment.newInstance(number)

    override fun getPageTitle(number: Int) = TextViewFragment.getTitle(context, number)

}