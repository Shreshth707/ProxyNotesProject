package com.example.project.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.R;
import com.example.project.UserProfile.UserProfile1;


import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    VideoView videoView;
    private static final String TAG = "Swipe Position";
    private static float x1,x2,y1,y2;
    private static int MIN_DISTANCE = 300;
    CircleImageView mUser;
    ImageView mUnlike,mLike,mShare,mNotSubscribed,mSubscribed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        videoView = view.findViewById(R.id.video);
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.video2);
        videoView.setVideoURI(uri);

        mUser = view.findViewById(R.id.user);
        mUnlike = view.findViewById(R.id.unlike);
        mLike = view.findViewById(R.id.like);
        mShare = view.findViewById(R.id.share);
        mNotSubscribed = view.findViewById(R.id.not_subscribed);
        mSubscribed = view.findViewById(R.id.subscribed);

        mUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(), UserProfile1.class);
                intent.putExtra("requestCode", "2");
                startActivityForResult(intent,2);
                getActivity().getFragmentManager().popBackStack();
            }
        });
        mUnlike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                mUnlike.setVisibility(View.INVISIBLE);
                mLike.setVisibility(View.VISIBLE);
            }
        });
        mLike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                mLike.setVisibility(View.INVISIBLE);
                mUnlike.setVisibility(View.VISIBLE);
            }
        });
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mNotSubscribed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotSubscribed.setVisibility(View.INVISIBLE);
                mSubscribed.setVisibility(View.VISIBLE);
            }
        });
        mSubscribed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubscribed.setVisibility(View.INVISIBLE);
                mNotSubscribed.setVisibility(View.VISIBLE);
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //do something
                    x1 = event.getX();
                    y1 = event.getY();
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    x2 = event.getX();
                    y2= event.getY();
                    float valueX = Math.abs(x2 - x1);
                    float valueY = Math.abs(y2 - y1);
                    if(valueX > MIN_DISTANCE){
                        if(x2 > x1){
                            Log.e(TAG, "Swipe Right");
                            if(mSubscribed.getVisibility() == View.INVISIBLE){
                                showSubscriptionToast(1);
                            }else {
                                showSubscriptionToast(0);
                            }
                        }else {
                            Log.e(TAG,"Swipe Left");
                            Intent intent = new Intent (getActivity().getApplicationContext(), UserProfile1.class);
                            intent.putExtra("requestCode", "2");
                            startActivityForResult(intent,2);
                            getActivity().getFragmentManager().popBackStack();
                        }
                    }else if(valueY > MIN_DISTANCE){
                        return false;
                    }
                }
                return true;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        return view;
    }

    private void showSubscriptionToast(int code) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.subscription_toast_layout , (ViewGroup) getActivity().findViewById(R.id.toast_root));

        Toast toast = new Toast(getActivity().getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        ImageView imageView = layout.findViewById(R.id.imageView);
        if(code == 0){
            imageView.setImageResource(R.drawable.subscribe);
            mNotSubscribed.setVisibility(View.VISIBLE);
            mSubscribed.setVisibility(View.INVISIBLE);
        }else {
            imageView.setImageResource(R.drawable.subscribe_colored);
            mNotSubscribed.setVisibility(View.INVISIBLE);
            mSubscribed.setVisibility(View.VISIBLE);
        }
        toast.setView(layout);
        toast.show();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == resultCode){
            videoView.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}
