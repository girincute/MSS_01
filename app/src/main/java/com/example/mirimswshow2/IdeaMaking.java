package com.example.mirimswshow2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class IdeaMaking extends AppCompatActivity {
    String ranWord[]={"유치원","어린이","꽃","벽돌","커튼","전구","창문","시계","피카츄","펭귄","뉴스","교복","청소년"
            ,"이모티콘","거울","파우치","화장품","향수","무드등","고양이","동물","핸드폰","지하철","버스","손잡이","의자"
            ,"카드","울타리","블라인드","발","밤","신발","눈","이빨","책","과자","상어","초코","다이어리","수첩","장애인"
            ,"테이프","휴지","스태프","행사","부스","전시","학교","머리끈","학생","콘서트","덕질","영어","머리끈","손톱"
            ,"세계","액자","미술관","박물관","소셜","쇼핑","상품","옷","스탠드","독서실","공시생","노량진","카페","신림"
            ,"음식점","고기","회","초밥","바다","산","놀이공원","병아리","집","설계도","이불","남자","여자","교통사고"
            ,"급식체","시계","포스트잇","종이","벽지","도매","거래","성형","응급실","스프링","캠프","그릇","컴싸","냄비"
            ,"냉장고","밥","티비","노트북","가방","파일","자전거","지도","핫팩","땀","숫자","점자","청각장애인","시각장애인"
            ,"수화","경보음","주말","공원","압정","졸업","티슈","충전","멀티탭","자동차","기름","주유소","고속도로","나무"
            ,"과일","문고리","기사","법","쇼핑백","아울렛","안내방송","동물실험","영상","저작권","청소","기호","전공","언어"
            ,"포스터","점퍼","방사선","종이컵","음료수","페트병","소파","정수기","간판","그림","디자인","공예물","의료용","신체"
            ,"혈압","화분","흙","우산","털","받침대","안경","시력","바람","선풍기","에어컨","초음파","DNA","고주파","자석"
            ,"자기장","현금","칩","CPU","새","주말","드라마","촬영","현장","사진","경찰","수사","자료","화상","습관"
            ,"바늘","로봇","커피","수술","곤충"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_making);

        ImageButton button01 = (ImageButton) findViewById(R.id.refreshButton);
//        final EditText editText01 = (EditText) findViewById(R.id.editText01);
        final EditText editText[]=new EditText[9];

        editText[0]= (EditText) findViewById(R.id.editText01);
        editText[1]= (EditText) findViewById(R.id.editText02);
        editText[2]= (EditText) findViewById(R.id.editText03);
        editText[3]= (EditText) findViewById(R.id.editText04);
        editText[4]= (EditText) findViewById(R.id.editText05);
        editText[5]= (EditText) findViewById(R.id.editText06);
        editText[6]= (EditText) findViewById(R.id.editText07);
        editText[7]= (EditText) findViewById(R.id.editText08);
        editText[8]= (EditText) findViewById(R.id.editText09);

        button01.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View v) {
                   //Toast.makeText(getApplicationContext(), "클릭 성공", Toast.LENGTH_LONG).show();
                   for(int i=0;i<editText.length;i++){
                       getRanWord(editText[i]);
                       editText[i].setIncludeFontPadding(false);
                   }
               }
           }
        );


    }

    public void getRanWord(EditText editText) {
        int r = (int)(Math.random()*ranWord.length);
        editText.setText(ranWord[r]);
    }
}

