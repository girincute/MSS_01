package com.example.mirimswshow2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    private FirebaseUser mFirebaseUser;

    private FirebaseDatabase mFirebaseDatabase;

    private TextView txtEmail, txtName;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startActivity(new Intent(this, SplashActivity.class));
        setContentView(R.layout.activity_main);
//        getWindow().setStatusBarColor(Color.parseColor("#7F5B13"));
        getWindow().setStatusBarColor(Color.parseColor("BLACK"));
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //etContent = (EditText)findViewById(R.id.content);

        if( mFirebaseUser == null ){
            startActivity(new Intent(MainActivity.this,AuthActivity.class));
            finish();
            return;
        }

        txtEmail = (TextView)findViewById(R.id.txtEmail);
        txtName = (TextView)findViewById(R.id.txtName);
        profileUpdate();



        final ImageButton button1 = (ImageButton) findViewById(R.id.makingButton);
        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "제작소", Toast.LENGTH_LONG).show();

                // 액티비티 전환 코드
                Intent intent = new Intent(getApplicationContext(), IdeaMaking.class);
                startActivity(intent);
             }
            }
        );
        button1.setOnTouchListener(new View.OnTouchListener() { //버튼 터치시 이벤트
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) // 버튼을 누르고 있을 때
                    button1.setBackgroundResource(R.drawable.jejaksobottonn);
                if(event.getAction() == MotionEvent.ACTION_UP){ //버튼에서 손을 떼었을 때
                    button1.setBackgroundResource(R.drawable.jejaksobotton);
                }
                return false;
            }
        });

        final ImageButton button2 = (ImageButton) findViewById(R.id.savedButton);
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "저장소", Toast.LENGTH_LONG).show();

                // 액티비티 전환 코드
                Intent intent = new Intent(getApplicationContext(), IdeaSaved.class);
                startActivity(intent);
             }
            }
        );

        button2.setOnTouchListener(new View.OnTouchListener() { //버튼 터치시 이벤트
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) // 버튼을 누르고 있을 때
                    button2.setBackgroundResource(R.drawable.jeojangsobottonn);
                if(event.getAction() == MotionEvent.ACTION_UP){ //버튼에서 손을 떼었을 때
                    button2.setBackgroundResource(R.drawable.jeojangsobotton);
                }
                return false;
            }
        });

        final ImageButton button3 = (ImageButton) findViewById(R.id.logoutButton);
        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그아웃되었습니다", Toast.LENGTH_LONG).show();

                // 로그아웃버튼
                mFirebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this,AuthActivity.class));
                finish();
                return;
             }
            }
        );

        button3.setOnTouchListener(new View.OnTouchListener() { //버튼 터치시 이벤트
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) // 버튼을 누르고 있을 때
                     button3.setBackgroundResource(R.drawable.logoutt);
                    if(event.getAction() == MotionEvent.ACTION_UP){ //버튼에서 손을 떼었을 때
                        button3.setBackgroundResource(R.drawable.logout);
                    }
                return false;
            }
        });

    }

    private void profileUpdate(){
        txtEmail.setText(mFirebaseUser.getEmail());
        txtName.setText(mFirebaseUser.getDisplayName());


    }


}