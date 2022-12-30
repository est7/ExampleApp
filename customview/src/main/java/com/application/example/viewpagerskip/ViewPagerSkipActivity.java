package com.application.example.viewpagerskip;

import android.os.Handler;

import androidx.viewpager.widget.ViewPager;

import com.application.example.customview.R;
import com.application.example.viewpagerskip.adapter.MyPagerAdapter;
import com.application.example.viewpagerskip.transformer.DoubleAddPageTransformer;
import com.application.example.viewpagerskip.viewpager.ViewPagerSkip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPagerSkipActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    private Integer[] colors = new Integer[]{

            R.drawable.rank_banner_avatar_1,
            R.drawable.rank_banner_avatar_2,
            R.drawable.rank_banner_avatar_3,
            R.drawable.rank_banner_avatar_4,
            R.drawable.rank_banner_avatar_5,
            R.drawable.rank_banner_avatar_6,
            R.drawable.rank_banner_avatar_7,
            R.drawable.rank_banner_avatar_1,
            R.drawable.rank_banner_avatar_2,
            R.drawable.rank_banner_avatar_3,
            R.drawable.rank_banner_avatar_4,
            R.drawable.rank_banner_avatar_5,
            R.drawable.rank_banner_avatar_6,
            R.drawable.rank_banner_avatar_7,
    };
    private ViewPagerSkip  viewPager04;
    private List<Integer> colorList;

    private long mDelayedTime = 2000l;

    private int currentPosition1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewpagerskip;
    }

    @Override
    public void initView() {
        initPager();
    }

    private void initPager() {
        colorList = new ArrayList<>();
        colorList.addAll(Arrays.asList(colors));
        viewPager04 = (ViewPagerSkip) findViewById(R.id.viewPager04);
        viewPager04.setScrollDuration(500);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(colorList, this);
        viewPager04.setOffscreenPageLimit(3);
        viewPager04.setPageMargin(20);
        viewPager04.setPageTransformer(true, new DoubleAddPageTransformer(3, 40));
        viewPager04.setAdapter(pagerAdapter);
        viewPager04.setCurrentItem(3);
        viewPager04.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                currentPosition1 = i;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //验证当前的滑动是否结束
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (currentPosition1 == 10) {
                        viewPager04.setCurrentItem(3, false);//切换，不要动画效果
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始轮播
        mHandler.postDelayed(mLoopRunnable, mDelayedTime);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止轮播
        mHandler.removeCallbacks(mLoopRunnable);
    }


    private final Runnable mLoopRunnable = new Runnable() {
        @Override
        public void run() {
            currentPosition1 = viewPager04.getCurrentItem();
            //不需要为了循环轮播来判断是否到达最后一页，在监听器中已经为我们做了此操作
            viewPager04.setCurrentItem(currentPosition1 + 1);
            viewPager04.postDelayed(this, mDelayedTime);
        }
    };

    @Override
    public void initData() {

    }
}
