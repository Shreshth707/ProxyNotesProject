package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.project.Fragments.Fragment1;
import com.example.project.Fragments.Fragment2;
import com.example.project.Fragments.Fragment3;
import com.example.project.Fragments.Fragment4;
import com.example.project.Fragments.Fragment5;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter fragmentPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VerticalViewPager viewPager = findViewById(R.id.viewPager);
        fragmentPagerAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);

    }

    public static class PageAdapter extends FragmentPagerAdapter{

        public PageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    //Fragment1
                    return Fragment1.newInstance();
                case 1:
                    //Fragment2
                    return Fragment2.newInstance();
                case 2:
                    //Fragment2
                    return Fragment3.newInstance();
                case 3:
                    //Fragment4
                    return Fragment4.newInstance();
                case 4:
                    //Fragment5
                    return Fragment5.newInstance();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
