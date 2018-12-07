package com.example.administrator.tablayoutdemo.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.tablayoutdemo.R;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationBar extends TabLayout {
     private int layoutId,imageViewId,textViewId,textColorSelet,textColorUnSelet;
     private List<BarItem> barItems=new ArrayList();
    private  TabLayout.OnTabSelectedListener tabSelectedListener;
    public BottomNavigationBar(Context context) {
        super(context);
    }
    public BottomNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationBar);
        layoutId= typedArray.getResourceId(R.styleable.BottomNavigationBar_layoutId,0);
        imageViewId= typedArray.getResourceId(R.styleable.BottomNavigationBar_imageViewId,0);
        textViewId= typedArray.getResourceId(R.styleable.BottomNavigationBar_textViewId,0);
        textColorSelet= typedArray.getResourceId(R.styleable.BottomNavigationBar_textColorSelet,0);
        textColorUnSelet= typedArray.getResourceId(R.styleable.BottomNavigationBar_layoutId,0);
    }
    private void initView(){
    for (int i = 0;i < this.getTabCount(); i++) {
        TabLayout.Tab tab = this.getTabAt(i);
        View view=View.inflate(getContext(),layoutId,null);
        ImageView imageView=view.findViewById(imageViewId);
        TextView textView=view.findViewById(textViewId);
        textView.setText(barItems.get(i).title);
         if(i == 0){
          textView.setTextColor(textColorSelet);
          imageView.setImageDrawable(getResources().getDrawable(barItems.get(i).seletedImg));
          }else{
           textView.setTextColor(textColorUnSelet);
           imageView.setImageDrawable(getResources().getDrawable(barItems.get(i).unselectedImg));
        }
        tab.setCustomView(view);
    }
      tabSelectedListener=new SelectListenerWrapper();
      addOnTabSelectedListener(tabSelectedListener);
    }
    public void setBarItems(List<BarItem> barItems) {
        this.barItems = barItems;
    }
    public static class BarItem{
        String title;
        int seletedImg;
        int unselectedImg;

        public BarItem(String title, int seletedImg, int unselectedImg) {
            this.seletedImg = seletedImg;
            this.unselectedImg = unselectedImg;
        }
    }
    public  class SelectListenerWrapper implements TabLayout.OnTabSelectedListener{

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            View view = tab.getCustomView();
            TextView textView = view.findViewById(textViewId);
            textView.setTextColor(textColorSelet);
            ImageView imageView = view.findViewById(imageViewId);
            imageView.setImageDrawable(getResources().getDrawable(barItems.get(tab.getPosition()).seletedImg));
        }
        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            View view = tab.getCustomView();
            TextView textView = view.findViewById(textViewId);
            textView.setTextColor(textColorUnSelet);
            ImageView imageView = view.findViewById(imageViewId);
            imageView.setImageDrawable(getResources().getDrawable(barItems.get(tab.getPosition()).unselectedImg));
        }
        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
         initView();
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public void setImageViewId(int imageViewId) {
        this.imageViewId = imageViewId;
    }

    public void setTextViewId(int textViewId) {
        this.textViewId = textViewId;
    }

    public void setTextColorSelet(int textColorSelet) {
        this.textColorSelet = textColorSelet;
    }

    public void setTextColorUnSelet(int textColorUnSelet) {
        this.textColorUnSelet = textColorUnSelet;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d("123123131","onDetachedFromWindow");
        removeOnTabSelectedListener(tabSelectedListener);
     }
}
