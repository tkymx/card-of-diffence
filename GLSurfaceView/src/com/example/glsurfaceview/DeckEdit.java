package com.example.glsurfaceview;

import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeckEdit extends Activity {
	public static final int THE_NUMBER_OF_DECK = 11;//デッキの枚数　今11枚
	
	TextView title;//「デッキ編集画面」という文字
	TextView instruct;//「カードをタッチして、デッキを編集しよう！」
	List<CardData> deckList;//自分のデッキのリストデータ　今はCardDataクラスのList 下でArrayListとして生成してる
	ImageView[] member = new ImageView[THE_NUMBER_OF_DECK];//表示する11枚のImageView
	Button back;//戻る　ボタン
	
	LinearLayout ll;//全体を覆うLayout
	LinearLayout topRow;//上段のLayout
	LinearLayout bottomRow;//下段Layout
	
	LinearLayout.LayoutParams LayoutParam;
	LinearLayout.LayoutParams titleParam;//パラメーター
	LinearLayout.LayoutParams topParam;//パラメーター
	LinearLayout.LayoutParams bottomParam;//パラメーター
	LinearLayout.LayoutParams backParam;//パラメーター
	LinearLayout.LayoutParams imageParam;//パラメーター
	
	
	
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
		bottomRow = new LinearLayout(this);
		bottomRow.setOrientation(LinearLayout.HORIZONTAL);
		bottomRow.setGravity(Gravity.CENTER);
		
	//パラメータ
		//大きさ設定
			
		titleParam = new LinearLayout.LayoutParams(Const.rx(0.5), Const.ry(0.2));
		topParam = new LinearLayout.LayoutParams(Const.rx(0.9), Const.ry(0.3));
		bottomParam = new LinearLayout.LayoutParams(Const.rx(0.9), Const.ry(0.3));
		backParam = new LinearLayout.LayoutParams(Const.rx(0.3), Const.ry(0.1));
		imageParam = new LinearLayout.LayoutParams(Const.rx(0.1), Const.ry(0.25));
		//イメージの間隔設定
		imageParam.setMargins(Const.rx(0.05), Const.ry(0.05), 0, Const.ry(0.05));
		topParam.setMargins(Const.rx(0.05), Const.ry(0.05),Const.rx(0.05), Const.ry(0.05));
		bottomParam.setMargins(Const.rx(0.05), Const.ry(0.05),Const.rx(0.05), Const.ry(0.05));
		
		//他生成
		title = new TextView(this);
		instruct = new TextView(this);
		deckList = new ArrayList<CardData>();
		back = new Button(this);
		for(int i=0;i<THE_NUMBER_OF_DECK;i++){
			member[i] = new ImageView(this);
			member[i].setImageResource(R.drawable.list);
			if(i<5)topRow.addView(member[i],imageParam);
			if(i>4)bottomRow.addView(member[i],imageParam);
			
		}
		title.setText("デッキ編集画面");
		back.setText("戻る");
		
		
		
		//いろいろセット
		setContentView(ll);
		ll.addView(title,titleParam);
		ll.addView(topRow,topParam);
		ll.addView(bottomRow,bottomParam);
		ll.addView(back,backParam);
		
	
		
		
		
		
	}
}
