package com.example.mirimswshow2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class IdeaMaking extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    private TextView mTextView;

    private FirebaseUser mFirebaseUser;

    private EditText etContent;

    private FirebaseDatabase mFirebaseDatabase;

    // 아래 네 줄 추가한 부분임
    ArrayList<String> mArrayList = new ArrayList();
    ArrayList<String> mArrayList2 = new ArrayList();
    int r[] = new int[9];
    String reword[] = new String[9];


    String ranWord[]={"유치원","어린이","꽃","벽돌","커튼","전구","창문","시계","피카츄","펭귄","뉴스","교복","청소년"
            ,"이모티콘","거울","파우치","화장품","향수","무드등","고양이","동물","핸드폰","지하철","버스","손잡이","의자"
            ,"카드","울타리","블라인드","발","밤","신발","눈","이빨","책","과자","상어","초코","다이어리","수첩","장애인"
            ,"테이프","휴지","스태프","행사","부스","전시","학교","머리끈","학생","콘서트","덕질","영어","머리끈","손톱"
            ,"세계","액자","미술관","박물관","소셜","쇼핑","상품","옷","스탠드","독서실","공시생","노량진","카페"
            ,"음식점","고기","회","초밥","바다","산","놀이공원","병아리","집","설계도","이불","남자","여자","교통사고"
            ,"급식체","시계","포스트잇","종이","벽지","도매","거래","성형","응급실","스프링","캠프","그릇","컴싸","냄비"
            ,"냉장고","밥","티비","노트북","가방","파일","자전거","지도","핫팩","땀","숫자","점자","청각장애","시각장애"
            ,"수화","경보음","주말","공원","압정","졸업","티슈","충전","멀티탭","자동차","기름","주유소","고속도로","나무"
            ,"과일","문고리","기사","법","쇼핑백","아울렛","안내방송","동물실험","영상","저작권","청소","기호","전공","언어"
            ,"포스터","점퍼","방사선","종이컵","음료수","페트병","소파","정수기","간판","그림","디자인","공예물","의료용","신체"
            ,"혈압","화분","흙","우산","털","받침대","안경","시력","바람","선풍기","에어컨","초음파","DNA","고주파","자석"
            ,"자기장","현금","칩","CPU","새","주말","드라마","촬영","현장","사진","경찰","수사","자료","화상","습관"
            ,"바늘","로봇","커피","수술","곤충","영화","공원","밤","연애","배우","사랑","부모님","패션","식물","털","울타리"
            ,"유리","종이","조형물","얼음","가전","서랍","여자","남자","노인","성향","스피커","마스크","공기","피부","한옥"
            ,"비누","욕실","야식","체인점","화살표","피로","양말","만보기","가방","케이스","가족","주방","체육","대결","틀"
            ,"끈","알파벳","어린이집","연금","나라","국민","공유기","꼬리","컵","일회용","간식","책","문제집","단어"
            ,"전구","목재","공","라벨","판매","한정수량","마트","로봇","건설","강아지","고양이","물고기","규정","꽃","비누"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_making);
        getWindow().setStatusBarColor(Color.parseColor("BLACK"));
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();


//        final ArrayList<String> items = new ArrayList<String>() ;
//        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
//        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items) ;
//
//        // listview 생성 및 adapter 지정.
//        final ListView listview = (ListView) findViewById(R.id.listview1) ;
//        listview.setAdapter(adapter) ;

        final TextView title = (TextView) findViewById(R.id.insertTitle);
        final ImageButton button01 = (ImageButton) findViewById(R.id.refreshButton);
//        final EditText editText01 = (EditText) findViewById(R.id.editText01);
        final EditText[] editText;
        editText = new EditText[9];

        final CheckBox[] checkBox;
        checkBox = new CheckBox[9];

        editText[0]= (EditText) findViewById(R.id.editText01);

        //editText[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.memo));

        editText[1]= (EditText) findViewById(R.id.editText02);
        editText[2]= (EditText) findViewById(R.id.editText03);
        editText[3]= (EditText) findViewById(R.id.editText04);
        editText[4]= (EditText) findViewById(R.id.editText05);
        editText[5]= (EditText) findViewById(R.id.editText06);
        editText[6]= (EditText) findViewById(R.id.editText07);
        editText[7]= (EditText) findViewById(R.id.editText08);
        editText[8]= (EditText) findViewById(R.id.editText09);

        checkBox[0]=(CheckBox)findViewById(R.id.checkBox1);
        checkBox[1]=(CheckBox)findViewById(R.id.checkBox2);
        checkBox[2]=(CheckBox)findViewById(R.id.checkBox3);
        checkBox[3]=(CheckBox)findViewById(R.id.checkBox4);
        checkBox[4]=(CheckBox)findViewById(R.id.checkBox5);
        checkBox[5]=(CheckBox)findViewById(R.id.checkBox6);
        checkBox[6]=(CheckBox)findViewById(R.id.checkBox7);
        checkBox[7]=(CheckBox)findViewById(R.id.checkBox8);
        checkBox[8]=(CheckBox)findViewById(R.id.checkBox9);



//        for(int i=0;i<checkBox.length;i++){
//            checkBox[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    if(b){//체크 활성화
//                        compoundButton.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
//                    } else {//체크 비활성화
//                        compoundButton.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#757575")));
//                    }
//                }
//            });
//        }
//        for(int i=0;i<editText.length;i++){
//        }
//
//        View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//
//            }
//        }


        button01.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View v) {
                   //Toast.makeText(getApplicationContext(), "클릭 성공", Toast.LENGTH_LONG).show();
//                   for(int q=0;q<editText.length;q++){
                   for (int i = 0; i < ranWord.length; i++) {
                       mArrayList.add(ranWord[i]); // 리스트에 단어 넣기!
                   }

                   for (int i = 0; i < r.length; i++) { // 9칸 중복되지 않는 랜덤 숫자 넣기
                       if (checkBox[i].isChecked()) { // 체크선택 > 아무 것도 하지 말고 그냥 넘어감
                           continue;
                       }
                       r[i] = (int) (Math.random() * mArrayList.size()); // 랜덤 숫자 넣기
                       for (int j = 0; j < i; j++) { // 중복 제거
                           if (r[i] == r[j]) { // 만약 지금까지의 숫자와 이번 숫자가 똑같다?
                               i--; // 중복이 되지 않을 때까지
                               break; // 다시 재입력 받기 위해서
                           }
                       }
                   }

                   for (int i = 0; i < r.length; i++) { // 진짜 목록에 넣기
                       if (checkBox[i].isChecked()) { // 체크선택 > 아무 것도 하지 말고 그냥 넘어감
                           continue;
                       }
                       mArrayList2.add(mArrayList.get(r[i]));  // 선택된 단어를 진짜 리스트에 담기
                       reword[i] = mArrayList.get(r[i]); // 선택된 단어 삭제 전에 미리 넣어두기

                       editText[i].setText(reword[i]); // 에디트텍스트에 단어 넣기
                   }

                   for (int i = 0; i < r.length; i++) { // 원래 리스트에서 삭제하는 부분!!
                       for (int j = 0; j < mArrayList.size(); j++) {
                           if (reword[i].equals(mArrayList.get(j))) {
                               mArrayList.remove(j);
                           }
                       }
                   }

                   mArrayList2.clear(); // 2에 있는 단어 모두 삭제하기!
                   for (int i = 0; i < mArrayList2.size(); i++) { // 삭제했던 단어 추가
                       if (checkBox[i].isChecked()) { // 체크선택 > 아무 것도 하지 말고 그냥 넘어감
                           continue;
                       }
                       mArrayList.add(reword[i]);

//                   getRanWord(editText[1], checkBox[1]);
//                       editText[1].setIncludeFontPadding(false);
//               }
                   }}
           }
        );
//        button01.setOnTouchListener(new View.OnTouchListener() { //버튼 터치시 이벤트
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN) // 버튼을 누르고 있을 때
//                    button01.setBackgroundResource(R.drawable.randomm);
//                if(event.getAction() == MotionEvent.ACTION_UP){ //버튼에서 손을 떼었을 때
//                    button01.setBackgroundResource(R.drawable.random);
//                }
//                return false;
//            }
//        });

        final ImageButton button02 = (ImageButton) findViewById(R.id.saveButton);
        button02.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String titleText=title.getText().toString();
                    int count=0;
                    if(Objects.equals(titleText, "")){
                        Toast.makeText(getApplicationContext(), "제목을 입력하세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for(int i=0;i<editText.length;i++){
                        count=(checkBox[i].isChecked())?count+1:count;
                    }
                    if(count==0){
                        Toast.makeText(getApplicationContext(), "최소 하나의 키워드를 선택해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm", Locale.KOREA );
                    Date currentTime = new Date ( );
                    String dTime = formatter.format ( currentTime );
                    Memo memo = new Memo();
                    //Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_LONG).show();
                    for(int i=0;i<editText.length;i++){
                        SaveMemo(editText[i],checkBox[i],memo);
                    }
                    memo.setTitle(titleText);
                    memo.setEditTxt("");
                    memo.setCreateDate(dTime);
                    mFirebaseDatabase
                            .getReference("memos/"+mFirebaseUser.getUid())
                            .push()
                            .setValue(memo)
                            .addOnSuccessListener(IdeaMaking.this, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //Snackbar.make(etContent,"메모가 저장되었습니다",Snackbar.LENGTH_LONG).show();
                                    Toast.makeText(IdeaMaking.this,"아이디어가 저장되었습니다", Toast.LENGTH_LONG).show();
                                    finish();
                                    Intent intent = new Intent(getApplicationContext(),IdeaSaved.class);
                                    startActivity(intent);
                                }
                            });
                }
             }
        );
//
//        button02.setOnTouchListener(new View.OnTouchListener() { //버튼 터치시 이벤트
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN) // 버튼을 누르고 있을 때
//                    button02.setBackgroundResource(R.drawable.savee);
//                if(event.getAction() == MotionEvent.ACTION_UP){ //버튼에서 손을 떼었을 때
//                    button02.setBackgroundResource(R.drawable.save);
//                }
//                return false;
//            }
//        });


    }

    public void getRanWord(EditText editText,CheckBox checkBox) {
        if(checkBox.isChecked()){
            //Toast.makeText(IdeaMaking.this,"haha"+i,Toast.LENGTH_LONG).show();
            return;
        }
        int r = (int)(Math.random()*ranWord.length);
        editText.setText(ranWord[r]);
    }
    private void SaveMemo(EditText editText,CheckBox checkBox,Memo memo){
        String text=editText.getText().toString();
        if(!checkBox.isChecked()){
            //Toast.makeText(IdeaMaking.this,"haha"+i,Toast.LENGTH_LONG).show();
            return;
        }
        memo.setTxt(text);


    }

}

