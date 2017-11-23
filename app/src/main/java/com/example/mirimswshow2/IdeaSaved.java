package com.example.mirimswshow2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class IdeaSaved extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    private TextView mTextView;

    private FirebaseUser mFirebaseUser;

    private ListView listview;

    public TextView savedWord;

    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_saved);
        getWindow().setStatusBarColor(Color.parseColor("BLACK"));
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        savedWord = (TextView)findViewById(R.id.e1);

//        super.onBackPressed();


        final ArrayList<String> items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
//        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items) ;
        final ArrayAdapter adapter = new ImageAdapter(this,items);

        // listview 생성 및 adapter 지정.
        listview = (ListView) findViewById(R.id.listview1) ;
        listview .setDivider(null);
        listview.setAdapter(adapter) ;
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                String a= items.get(position);
                a=a.substring(a.indexOf("\t")+1);
                final String finalA = a;
                new AlertDialog.Builder(IdeaSaved.this)
                        .setTitle("삭제")
                        .setMessage("선택한 아이디어를 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mFirebaseDatabase
                                        .getReference("memos/"+mFirebaseUser.getUid()+"/" +finalA/*+"/"+key*/)
                                        .removeValue(new DatabaseReference.CompletionListener() {
                                            @Override
                                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                Toast.makeText(getApplicationContext(), "삭제가 완료되었습니다", Toast.LENGTH_LONG).show();
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                return true;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String a= items.get(position);
//                a=a.substring(a.indexOf("\t")+1);
//                final String finalA = a;
                //savedWord.setText("2");
//                String b;

//                Toast.makeText(getApplicationContext(), a, Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(getApplicationContext(),EditSavedIdea.class);
//                              //  intent.putExtra("words", memo.getTitle()+"\n"+memo.getCreateDate()+"\0"+memo.getTxt());
                intent.putExtra("words", a);
                ////Toast.makeText(IdeaSaved.this,"메모"+a, Toast.LENGTH_LONG).show();
                startActivity(intent);

            }
        });

        mFirebaseDatabase.getReference("memos/"+mFirebaseUser.getUid())
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Memo memo = dataSnapshot.getValue(Memo.class);
                        memo.setKey(dataSnapshot.getKey());
//                        Menu leftMenu;
//                        leftMenu = mTextView.getMenu();
//                        MenuItem item = leftMenu.add(memo.getTxt());
//                        View view = new View(getApplication());
//                        view.setTag(memo);
//                        item.setActionView(view);
                        // 아이템 추가.
                        items.add(memo.getTitle()+"\n"+memo.getCreateDate()+"\0"+memo.getTxt()+"\n\t"+dataSnapshot.getKey());

                        // listview 갱신
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        Memo memo = dataSnapshot.getValue(Memo.class);
                        memo.setKey(dataSnapshot.getKey());
//                        Menu leftMenu;
//                        leftMenu = mTextView.getMenu();
//                        MenuItem item = leftMenu.add(memo.getTxt());
//                        View view = new View(getApplication());
//                        view.setTag(memo);
//                        item.setActionView(view);
                        // 아이템 추가.
                        items.remove(memo.getTitle()+"\n"+memo.getCreateDate()+"\0"+memo.getTxt()+"\n\t"+dataSnapshot.getKey());

                        // listview 갱신
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }


}
