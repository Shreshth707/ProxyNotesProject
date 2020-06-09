package com.example.project.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.project.R;

public class UserProfile1 extends AppCompatActivity {

    private ImageView mBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent.getSerializableExtra("requestCode").equals("1")){
            setContentView(R.layout.activity_user_profile1);
        }else if(intent.getSerializableExtra("requestCode").equals("2")) {
            setContentView(R.layout.activity_user_profile2);
        }else if(intent.getSerializableExtra("requestCode").equals("3")){
            setContentView(R.layout.activity_user_profile3);
        }else if(intent.getSerializableExtra("requestCode").equals("4")){
            setContentView(R.layout.activity_user_profile4);
        }else if(intent.getSerializableExtra("requestCode").equals("5")){
            setContentView(R.layout.activity_user_profile5);
        }

        mBackBtn = findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killActivity();
            }
        });
    }

    private void killActivity() {
        setResult(1);
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(1);
        finish();
    }
}
