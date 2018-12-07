package com.example.administrator.tablayoutdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import com.example.administrator.tablayoutdemo.viewpager.BottomNavigationBar.BarItem
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity(){
     val barList by lazy {
         listOf(
                 BarItem("主页",R.mipmap.ic_launcher,R.mipmap.cancale),
                 BarItem("我的消息",R.mipmap.ic_launcher,R.mipmap.cancale),
                 BarItem("我的订单",R.mipmap.ic_launcher,R.mipmap.cancale))
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         initView()
    }
private fun initView(){
    bottomNavigationBar.setBarItems(barList)
    bottomNavigationBar.setLayoutId(R.layout.otc_bottom_navigation)
    bottomNavigationBar.setImageViewId(R.id.iv_bottom_na)
    bottomNavigationBar.setTextViewId(R.id.tv_bottom_na)
    bottomNavigationBar.setTextColorSelet(resources.getColor(R.color.design_default_color_primary_dark))
    bottomNavigationBar.setTextColorUnSelet(resources.getColor(R.color.design_bottom_navigation_shadow_color))
    vp.adapter=MyAdapter(supportFragmentManager)
    bottomNavigationBar.setupWithViewPager(vp)
}
   inner class MyAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){
         override fun getItem(p0: Int): Fragment {
               return  BlankFragment()
         }
         override fun getCount(): Int {
            return barList.size
         }
     }

     override fun onPause() {
         super.onPause()
         Log.d("123123131", "onPause")
     }
     override fun onStop() {
         super.onStop()
         Log.d("123123131", "onStop")
     }
     override fun onDestroy() {
         super.onDestroy()
         Log.d("123123131", "onDestroy")
     }
}
