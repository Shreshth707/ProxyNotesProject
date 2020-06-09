package com.example.project;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(@NonNull Context context) {
        super(context);
        bounceEffect();
    }
    VideoView videoView = findViewById(R.id.video);
    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bounceEffect();
    }
    public void   bounceEffect(){
        setPageTransformer(true,new ViewPagerTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private MotionEvent SwipeXY(MotionEvent motionEvent){
        float x=getWidth();
        float y=getHeight();

        float NewX=(motionEvent.getY()/y)*y;
        float NewY=(motionEvent.getX()/x)*x;
        motionEvent.setLocation(NewX,NewY);

        return motionEvent;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept=super.onInterceptTouchEvent(SwipeXY(ev));
        SwipeXY(ev);
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(SwipeXY(ev));
    }

    public class ViewPagerTransformer implements ViewPager.PageTransformer{

        private static final float M_SCALE=0.65f;
        @Override
        public void transformPage(@NonNull View page, float position) {
            if (position<-1){
                page.setAlpha(0);
                videoView = page.findViewById(R.id.video);
                videoView.pause();
                Log.e("Position", String.valueOf((int)position));
            }else if (position<=0){
                page.setAlpha(1);
                page.setTranslationX(page.getWidth()*-position);
                page.setTranslationY(page.getHeight()*position);

                page.setScaleX(1);
                page.setScaleY(1);
                Log.e("Position", String.valueOf(position));
                if(position == 0){
                    videoView = page.findViewById(R.id.video);
                    videoView.setVisibility(View.VISIBLE);
                    videoView.start();
                }else {
                    videoView = page.findViewById(R.id.video);
                    videoView.pause();
                }

            }else if (position<=1){
                page.setAlpha(1-position);
                page.setTranslationX(page.getWidth()*-position);
                page.setTranslationY(0);
                float scale_factor=M_SCALE+(1-M_SCALE)*(1-Math.abs(position));
                page.setScaleX(scale_factor);
                page.setScaleY(scale_factor);
                Log.e("Position", String.valueOf((int)position));
                videoView = page.findViewById(R.id.video);
                videoView.pause();
                if(position == 1){
                    videoView.setVisibility(View.INVISIBLE);
                }else {
                    videoView.setVisibility(View.VISIBLE);
                }

            }else if (position>1){
                page.setAlpha(0);
                videoView = page.findViewById(R.id.video);
                videoView.pause();
                Log.e("Position", String.valueOf((int)position));
            }
        }
    }
}

