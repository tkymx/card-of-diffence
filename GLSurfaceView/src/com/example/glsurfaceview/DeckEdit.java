package com.example.glsurfaceview;

import java.util.ArrayList;
import java.util.List;

import com.example.data.CardInformation;
import com.example.data.DataBase;

import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeckEdit extends Activity {
	
	/*ここでは、CardDataは書いてはあるけど何にも使ってないです*/
	
	public static final int THE_NUMBER_OF_DECK = 11;		//デッキの枚数　今11枚
	
	TextView title;			//「デッキ編集画面」という文字
	TextView instruct;		//「カードをタッチして、デッキを編集しよう！」
	List<CardData> deckList;//自分のデッキのリストデータ　今はCardDataクラスのList 下でArrayListとして生成してる
	ImageView[] member = new ImageView[THE_NUMBER_OF_DECK];	//表示する11枚のImageView
	String[] memberName = new String[THE_NUMBER_OF_DECK];  //Deckにはいってるカードの名前をいれる
	Button back;			//戻る　ボタン
	
	LinearLayout ll;		//全体を覆うLayout
	LinearLayout topRow;	//デッキ上段のLayout
	LinearLayout middleRow;	//デッキ下段Layout
	LinearLayout bottomRow;	//画面の一番下のLayout
	
	
	LinearLayout.LayoutParams titleParam;//パラメーター
	LinearLayout.LayoutParams topParam;//パラメーター
	LinearLayout.LayoutParams middleParam;//パラメーター
	LinearLayout.LayoutParams bottomParam;//パラメーター
	LinearLayout.LayoutParams backParam;//パラメーター
	LinearLayout.LayoutParams imageParam;//パラメーター
	LinearLayout.LayoutParams instructParam;//パラメーター
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
	//いろいろ生成する
		//レイアウトの生成&設定
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		topRow = new LinearLayout(this);
		topRow.setOrientation(LinearLayout.HORIZONTAL);
		topRow.setGravity(Gravity.CENTER);
		middleRow = new LinearLayout(this);
		middleRow.setOrientation(LinearLayout.HORIZONTAL);
		middleRow.setGravity(Gravity.CENTER);
		bottomRow = new LinearLayout(this);
		bottomRow.setOrientation(LinearLayout.HORIZONTAL);
		
	//パラメータ
		//大きさ設定
		titleParam = new LinearLayout.LayoutParams(Const.rx(0.5), Const.ry(0.2));
		topParam = new LinearLayout.LayoutParams(Const.rx(0.9), Const.ry(0.3));
		middleParam = new LinearLayout.LayoutParams(Const.rx(0.9), Const.ry(0.3));
		bottomParam = new LinearLayout.LayoutParams(Const.rx(0.95), Const.ry(0.1));
		instructParam = new LinearLayout.LayoutParams(Const.rx(0.6), Const.ry(0.1));
		backParam = new LinearLayout.LayoutParams(Const.rx(0.2), Const.ry(0.1));
		imageParam = new LinearLayout.LayoutParams(Const.rx(0.1), Const.ry(0.25));
		
		//イメージの間隔設定
		imageParam.setMargins(Const.rx(0.05), Const.ry(0.05), 0, Const.ry(0.05));
		//他間隔設定
		titleParam.setMargins(Const.rx(0.025),Const.ry(0.01),0, 0);
		topParam.setMargins(Const.rx(0.03), 0,Const.rx(0.05), 0);
		middleParam.setMargins(Const.rx(0.03), 0,Const.rx(0.05), 0);
		bottomParam.setMargins(0, 0,Const.rx(0.05), 0);
		instructParam.setMargins(Const.rx(0.1), 0,Const.rx(0.05), 0);
		
	//他生成
		title = new TextView(this);
		instruct = new TextView(this);
		deckList = new ArrayList<CardData>();//今はこれ使ってないです
		back = new Button(this);
		for(int i=0;i<THE_NUMBER_OF_DECK;i++){
			member[i] = new ImageView(this);
			
			String selectedCard = DataBase.GetMyDeck(i);
			member[i].setImageResource(CardInformation.GetCardInformaionFromName(selectedCard).getCard_id());
			memberName[i] = CardInformation.GetCardInformaionFromName(selectedCard).getName();
			
			if(i<5)topRow.addView(member[i],imageParam);   //始めの5枚を上の段に
			if(i>4)middleRow.addView(member[i],imageParam);//残りを下の段に表示
			member[i].setOnClickListener(new SampleClickListener());
		
		}
		back.setOnClickListener(new SampleClickListener());
		
		//テキストのサイズとか
		title.setText("デッキ編集画面");
		title.setTextSize(Const.rx(0.035));
		instruct.setText("カードをタッチして、デッキを編集しよう！");
		instruct.setTextSize(Const.rx(0.015));
		back.setText("戻る");
		back.setTextSize(Const.rx(0.02));
		
		
		
		//いろいろセット
		setContentView(ll);
		ll.addView(title,titleParam);
		ll.addView(topRow,topParam);
		ll.addView(middleRow,middleParam);
		ll.addView(bottomRow,bottomParam);
		bottomRow.addView(instruct,instructParam);
		bottomRow.addView(back,backParam);
		
		//バックの写真セット
		ll.setBackgroundResource(R.drawable.background);
		topRow.setBackgroundResource(R.drawable.explain);
		middleRow.setBackgroundResource(R.drawable.explain);
		instruct.setBackgroundResource(R.drawable.back);
		
	}
	class SampleClickListener implements android.view.View.OnClickListener{
		
		public void onClick(View v) 
		{
			if(v.equals(back))finish();
			for(int i=0;i<THE_NUMBER_OF_DECK;i++){
			
				if(v.equals(member[i]) ){
					Intent intent = new Intent();
					intent.setClass( OpenGLSurfaceView.c , com.example.glsurfaceview.DeckSelect.class);
					intent.putExtra("name", memberName[i]);		//どのカードをタッチしたかの情報を、DeckSelectクラスに送る
					intent.putExtra("num", i);
					OpenGLSurfaceView.c.startActivity(intent);
					finish();
				}
			}
		}
		
	}
}
