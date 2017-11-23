package com.example.mirimswshow2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditSavedIdea extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    private TextView mTextView;

    private FirebaseUser mFirebaseUser;

    private DatabaseReference mDatabase;

    private EditText etContent;

    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_saved_idea);
        getWindow().setStatusBarColor(Color.parseColor("BLACK"));
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final String[] key = new String[1];
        final String[] str = new String[1];
        final Memo[] memo = new Memo[1];

        final TextView textview1 = (TextView) findViewById(R.id.e1);
        final TextView textview2 = (TextView) findViewById(R.id.e11);
        final TextView textview3 = (TextView) findViewById(R.id.e12);
        final EditText editText = (EditText) findViewById(R.id.e2);

        Intent intent = getIntent();

        String words=intent.getStringExtra("words");
//        words= words.substring(words.indexOf("\0"));

       // mFirebaseDatabase.getReference("memos/"+mFirebaseUser.getUid());
        textview1.setText(intent.getStringExtra("words").substring(0,words.indexOf("\n")));
        textview2.setText(intent.getStringExtra("words").substring(words.indexOf("\n")+1,words.indexOf("#")-1));
        textview3.setText(intent.getStringExtra("words").substring(words.indexOf("#"),words.indexOf("\n\t")));
//        editText.setText(intent.getStringExtra("words").substring(words.indexOf("\t\0")+4));
        key[0]=words.substring(words.indexOf("\n\t")+2);
        mFirebaseDatabase.getReference("memos/"+mFirebaseUser.getUid()+"/"+key[0])
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        memo[0] = dataSnapshot.getValue(Memo.class);
                        str[0]=memo[0].getEditTxt();
                        editText.setText(str[0]);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        mFirebaseDatabase.getReference("memos/"+mFirebaseUser.getUid())
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                        memo[0] = dataSnapshot.getValue(Memo.class);
//                        key[0] = dataSnapshot.getKey();
//                        memo[0].setKey(dataSnapshot.getKey());
//                        str[0]=memo[0].getEditTxt();
//                        editText.setText(str[0]);
//                        key[0] = dataSnapshot.getKey();
//                        editText.setText(memo[0].getEditTxt());
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                        memo[0] = dataSnapshot.getValue(Memo.class);
//                        memo[0].setKey(dataSnapshot.getKey());
//                        key[0] = dataSnapshot.getKey();
//                        editText.setText(memo[0].getEditTxt());
//                        str[0]=memo[0].getEditTxt();
//                        editText.setText(str[0]);
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        ImageButton imageButton = (ImageButton)findViewById(R.id.saveEditButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String txt=editText.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm", Locale.KOREA );
                Date currentTime = new Date ( );
                String dTime = formatter.format ( currentTime );
                Memo memo1 = new Memo();
//                String title=memo[0].getTitle();
                memo1.setEditTxt(editText.getText().toString());
                memo1.setTitle(memo[0].getTitle());
                memo1.setTxt(memo[0].getTxt());
                memo1.setCreateDate(dTime);
                //memo.setKey(key[0]);
                //Toast.makeText(EditSavedIdea.this,"메모가 저장되었습니다", Toast.LENGTH_LONG).show();

                mFirebaseDatabase
                        .getReference("memos/"+mFirebaseUser.getUid()+"/"+key[0])
                        //.push()
                        .setValue(memo1)
                        .addOnSuccessListener(/*EditSavedIdea.this,*/ new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Snackbar.make(etContent,"메모가 저장되었습니다",Snackbar.LENGTH_LONG).show();
                                Toast.makeText(EditSavedIdea.this,"아이디어가 저장되었습니다", Toast.LENGTH_LONG).show();
                                finish();
                                Intent intent = new Intent(getApplicationContext(),IdeaSaved.class);
                                startActivity(intent);
                            }
                        });

//                Map<String, Object> childUpdates = new HashMap<>();
//                childUpdates.put("memos/"+mFirebaseUser.getUid())
//                String txt=editText.getText().toString();
//                memo.setTitle(txt);
//                mFirebaseDatabase.getReference("memos/"+mFirebaseUser.getUid())
//                        .addChildEventListener(new ChildEventListener() {
//                            @Override
//                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                Memo memo = dataSnapshot.getValue(Memo.class);
//                                memo.setKey(dataSnapshot.getKey());
//                                String txt=editText.getText().toString();
//                                memo.setEditTxt(txt);
//                            }
//
//                            @Override
//                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                                Memo memo = dataSnapshot.getValue(Memo.class);
//                                memo.setKey(dataSnapshot.getKey());
//                                String txt=editText.getText().toString();
//                                memo.setEditTxt(txt);
//                            }
//
//                            @Override
//                            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                            }
//
//                            @Override
//                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });

            }}
        );

    }
    private void SaveMemo(EditText editText, Memo memo){
        String text=editText.getText().toString();
        if(text==null){
            //Toast.makeText(IdeaMaking.this,"haha"+i,Toast.LENGTH_LONG).show();
            return;
        }
        memo.setEditTxt(text);


    }
}
