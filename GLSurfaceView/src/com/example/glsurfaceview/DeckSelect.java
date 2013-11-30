package com.example.glsurfaceview;

import java.util.List;
import com.example.data.CardInformation;
import com.example.data.DataBase;
import com.example.glsurfaceview.Const;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DeckSelect extends Activity{
	
	Button okButton;		//デッキ採用決定ボタン
	Button backButton;		//戻るボタン
	ListView lv;			//リストビュー　右のスクロールできるやつ
	TextView cardNameText,atackText,defenceText,explainText;	//左の詳細画面に使うテキストビュー達
	ImageView card;			//左の詳細画面に使うキャラのImageView
	String selectedDeckCard;//DeckEdit画面で、タッチしたデッキのカード　の名前
	String selectedNewDeckCard;//これから選択する、新たにデッキに追加するカード　の名前
	
	List<CardData> objects;	//CardDataクラスのリスト これをCardInfomation使った何かに変える
	List<CardInformation> cardList;
	
	LinearLayout ll;		//全体を覆うレイアウト
	RelativeLayout rl;		//左の詳細情報の部分のレイアウト
	
	RelativeLayout.LayoutParams okParam;//パラメータ
	RelativeLayout.LayoutParams backParam;
	RelativeLayout.LayoutParams atackTextParam;
	RelativeLayout.LayoutParams defenceTextParam;
	RelativeLayout.LayoutParams explainTextParam;
	RelativeLayout.LayoutParams cardNameTextParam;
	RelativeLayout.LayoutParams cardParam;
	 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//いろいろ生成
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		lv = new ListView(this);
		rl = new RelativeLayout(this);
		cardNameText = new TextView(this);
		card = new ImageView(this);
		atackText = new TextView(this);
		defenceText = new TextView(this);
		explainText = new TextView(this);
		okButton = new Button(this);
		backButton = new Button(this);
		selectedDeckCard = getIntent().getStringExtra("name");
		
		//左画面の初期設定
		String initCard = DataBase.GetMyCards(0);
		selectedNewDeckCard=initCard;
		
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), CardInformation.GetCardInformaionFromName(initCard).getCard_id());
		bmp = Bitmap.createScaledBitmap(bmp, Const.card_width, Const.card_height , false);		
		card.setImageBitmap(bmp);		
		
		atackText.setText(CardInformation.GetCardInformaionFromName(initCard).getParameter1());		
		defenceText.setText(CardInformation.GetCardInformaionFromName(initCard).getparameter2());
		explainText.setText(CardInformation.GetCardInformaionFromName(initCard).getExplain());
		cardNameText.setText(CardInformation.GetCardInformaionFromName(initCard).getName());
		
		
		//テキストサイズ設定
		cardNameText.setTextSize(Const.ry(0.036));
		cardNameText.setTextAlignment( View.TEXT_ALIGNMENT_CENTER );
		atackText.setTextSize(Const.ry(0.020));			
		defenceText.setTextSize(Const.ry(0.020));		
		explainText.setTextSize(Const.ry(0.020));
		
		okButton.setText("OK");
		backButton.setText("戻る");
		backButton.setAlpha(1.0f);
		
		//パラメータ生成、大きさの設定
		cardNameTextParam = new RelativeLayout.LayoutParams(Const.rx(0.3), Const.ry(0.1));
		cardParam = new RelativeLayout.LayoutParams(Const.rx(0.2), Const.ry(0.3));
		atackTextParam = new RelativeLayout.LayoutParams(Const.rx(0.15), Const.ry(0.06));
		defenceTextParam = new RelativeLayout.LayoutParams(Const.rx(0.15), Const.ry(0.06));
		explainTextParam = new RelativeLayout.LayoutParams(Const.rx(0.3), Const.ry(0.2));
		okParam = new RelativeLayout.LayoutParams(Const.rx(0.1), Const.ry(0.1));
		backParam = new RelativeLayout.LayoutParams(Const.rx(0.1), Const.ry(0.1));
		
		//パラメータで場所の指定
		cardNameTextParam.setMargins(Const.rx(0.05), Const.ry(0.05),0,0);
		cardParam.setMargins(Const.rx(0.1), Const.ry(0.2),0,Const.ry(0.05));
		atackTextParam.setMargins(Const.rx(0.05), Const.ry(0.54),0,0);
		defenceTextParam.setMargins(Const.rx(0.20), Const.ry(0.54),0,0);
		explainTextParam.setMargins(Const.rx(0.05), Const.ry(0.60),0,0);
		okParam.setMargins(Const.rx(0.05), Const.ry(0.8),0,0);
		backParam.setMargins(Const.rx(0.25), Const.ry(0.8),0,0);
		
		//アダプターセット
		CardDataAdapter ad = new CardDataAdapter(this,0,DataBase.GetMyCards());
		lv.setAdapter(ad);
	
	
	/*とりあえず適当にデータベース代わりの配列を作った*/
		/*String[] name = {"車","バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","利光","河津",};
		Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.atack);
		Bitmap[] bmp = {image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,};
		String[] atack = {"攻撃力 1000","攻撃力 2000","攻撃力 3000","攻撃力 1000","攻撃力 2000","攻撃力 3000","攻撃力 1000","攻撃力 2000","攻撃力 3000","攻撃力 1000","攻撃力 1000"};
		String[] defence = {"防御力　500","防御力　600","防御力　700","防御力　500","防御力　600","防御力　700","防御力　500","防御力　600","防御力　700","防御力　500","防御力　500"};
		String[] explain = {"あああああああああじあああああああああじあああああああああじ","バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","利光","河津",};			
		objects = new ArrayList<CardData>();
		for(int i=0;i<name.length;i++){
			CardData object = new CardData();
			object.setImagaData(bmp[i]);
			object.setNameData(name[i]);
			object.setAtackData(atack[i]);
			object.setDefenceData(defence[i]);
			object.setExplainData(explain[i]);
			objects.add(object);//上の配列からCardDataクラスのリストを生成
		}
	/*データベース終わり*/
		
		
		//バックグラウンド画像設定
		ll.setBackgroundResource(R.drawable.back_deckselect_back);

		cardNameText.setBackgroundResource(R.drawable.pause_base);
		cardNameText.setAlpha(0.7f);
		
		atackText.setBackgroundResource(R.drawable.pause_base);
		atackText.setAlpha(0.7f);
		
		defenceText.setBackgroundResource(R.drawable.pause_base);
		defenceText.setAlpha(0.7f);
		
		explainText.setBackgroundResource(R.drawable.pause_base);
		explainText.setAlpha(0.7f);
		
		
		//いろいろセット
		setContentView(ll);
		ll.addView(rl,new LinearLayout.LayoutParams(Const.rx(0.4), Const.ry(1)));
		ll.addView(lv,new LinearLayout.LayoutParams(Const.rx(0.6), Const.ry(0.95)));
		rl.addView(cardNameText, cardNameTextParam);
		rl.addView(card, cardParam);
		rl.addView(atackText,atackTextParam);
		rl.addView(defenceText,defenceTextParam);
		rl.addView(explainText,explainTextParam);
		rl.addView(okButton, okParam);
		rl.addView(backButton,backParam);
		
		okButton.setOnClickListener(new SampleClickListener());
		backButton.setOnClickListener(new SampleClickListener());
		lv.setOnItemClickListener(new SampleItemClickListener());
	}
	
	
	///////////////////////以下、リスナ///////////////////////////////
	class SampleItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			
			String selectedCard = DataBase.GetMyCards(arg2);
			selectedNewDeckCard = selectedCard;
			
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), CardInformation.GetCardInformaionFromName(selectedCard).getCard_id());
			bmp = Bitmap.createScaledBitmap(bmp, Const.card_width, Const.card_height , false);		
			card.setImageBitmap(bmp);		
			
			atackText.setText(CardInformation.GetCardInformaionFromName(selectedCard).getParameter1());		
			defenceText.setText(CardInformation.GetCardInformaionFromName(selectedCard).getparameter2());
			explainText.setText(CardInformation.GetCardInformaionFromName(selectedCard).getExplain());
			cardNameText.setText(CardInformation.GetCardInformaionFromName(selectedCard).getName());	
			
		}
	}
	
	
	class SampleClickListener implements android.view.View.OnClickListener{
		public void onClick(View v) {
			if(v.equals(okButton))
			{
				Intent intent = new Intent();
				int d=0;
				intent.setClass( OpenGLSurfaceView.c , com.example.glsurfaceview.DeckEdit.class);
				for(int i=0;i<Const.THE_NUMBER_OF_DECK;i++){
					
					if(DataBase.GetMyDeck(i).equals(selectedDeckCard))d=i;
					
				}
				d=getIntent().getIntExtra("num",0);
					int c=DataBase.GetMyCards().indexOf(selectedNewDeckCard);
					DataBase.SwapMyDecksFromMyCards(d,c);
				
				OpenGLSurfaceView.c.startActivity(intent);
				finish();
			}
			else if(v.equals(backButton))
			{
				Intent intent = new Intent();
				intent.setClass( OpenGLSurfaceView.c , com.example.glsurfaceview.DeckEdit.class);
				OpenGLSurfaceView.c.startActivity(intent);
				
				finish();
			}
		}
		
	}

	
	  
	
	

}