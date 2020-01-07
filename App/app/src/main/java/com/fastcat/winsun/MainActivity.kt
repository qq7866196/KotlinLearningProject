package com.fastcat.winsun

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

/**
 * TabLayout可实现功能：
 * 1.均分tab,可滑动式
 * 2.点击颜色变更
 * 3.指示器高度及颜色
 * 4.添加图标，自定义视图
 */
class MainActivity : AppCompatActivity(), BlankFragment.OnFragmentInteractionListener {
    val titles:Array<String> = arrayOf("上海", "头条推荐", "生活", "娱乐八卦", "体育",
        "段子", "美食", "电影", "科技", "搞笑",
        "社会", "财经", "时尚", "汽车", "军事",
        "小说", "育儿", "职场", "萌宠", "游戏",
        "健康", "动漫", "互联网")
    override fun onFragmentInteraction(uri: Uri) {
    }

    var fragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentList.add(BlankFragment.newInstance("123", "321"))
        fragmentList.add(BlankFragment.newInstance("2", "21"))
        fragmentList.add(BlankFragment.newInstance("3", "31"))
        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        view_pager.adapter = adapter

     /*   for(i in 0..4) {
            //单独测试tab功能
            val item = tab_layout.newTab()
            item.tag = i
            item.text = mTitles[i]
            tab_layout.addTab(item)
        }*/

        tab_layout.setupWithViewPager(view_pager)
        tab_layout.tabMode = TabLayout.MODE_FIXED

        for(i in 0..tab_layout.tabCount) {
            val item = tab_layout.getTabAt(i)
            item?.tag = i
            item?.text = titles[i]
        }

        val cl = SingleInstance()
        cl.out_side = 24

    }


    inner class SimpleFragmentPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

    }


}
