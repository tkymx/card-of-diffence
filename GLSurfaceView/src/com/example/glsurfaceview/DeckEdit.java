package com.example.glsurfaceview;

import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeckEdit extends Activity {
	public static final int THE_NUMBER_OF_DECK = 11;//デッキの枚数　今11枚
	
	TextView title;//「デッキ編集画面」という文字
	TextView instruct;//「カードをタッチして、デッキを編集しよう！」
	List<CardData> deckList;//自分のデッキのリストデータ　今はCardDataクラスのList 下でArrayListとして生成してる
	ImageView[] member = new ImageView[THE_NUMBER_OF_DECK];//表示する11枚のImageView
	Button back;//戻る　ボタン
	
	LinearLayout ll = new LinearLayout(this);//全体を覆うLayout
	LinearLayout ll2 = new LinearLayout(this);//全体を覆うLayout
	LinearLayout ll3 = new LinearLayout(this);//全体を覆うLayout
	
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//いろいろ生成する
		title = new TextView(this);
		instruct = new TextView(this);
		deckList = new ArrayList<CardData>();
		back = new Button(this);
		for(int i=0;i<THE_NUMBER_OF_DECK;i++){
			member[i] = new ImageView(this);
		}
		
		
		
	
	}
}
