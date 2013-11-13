package com.example.glsurfaceview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import com.example.*;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Text;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeckSelect extends Activity{
	OpenGLSurfaceView surface;
	public static int width, height;
	Button okButton;//デッキ採用決定ボタン
	Button backButton;//戻るボタン
	ListView lv;//リストビュー　右のスクロールできるやつ
	RelativeLayout rl;//左の詳細情報の部分
	TextView cardNameText,atackText,defenceText,explainText;//テキストビュー達
	 private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;//?????
	 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		setContentView(ll);
		lv = new ListView(this);
		rl = new RelativeLayout(this);
		okButton = new Button(this);
		backButton = new Button(this);
		atackText = new TextView(this);
		defenceText = new TextView(this);
		explainText = new TextView(this);
		cardNameText = new TextView(this);
		
		ll.addView(rl,new LinearLayout.LayoutParams(Const.rx(0.4), Const.ry(1)));
		ll.addView(lv,new LinearLayout.LayoutParams(Const.rx(0.55), Const.ry(1)));
		ll.setBackgroundResource(R.drawable.background);
		okButton.setText("OK");
		backButton.setText("戻る");
		cardNameText.setText("ティガレックス");
		cardNameText.setBackgroundResource(R.drawable.name); 
		cardNameText.setTextSize(Const.ry(0.030));
		atackText.setText("攻撃力　53万");
		atackText.setTextSize(Const.ry(0.020));
		atackText.setBackgroundResource(R.drawable.atack); 
		defenceText.setText("防御力　1");
		defenceText.setTextSize(Const.ry(0.020));
		defenceText.setBackgroundResource(R.drawable.defence); 
		explainText.setText("やばいやつぐはははははは\nははははははははははは\nはははははははははははははは");
		explainText.setTextSize(Const.ry(0.010));
		explainText.setBackgroundResource(R.drawable.explain); 
		
		RelativeLayout.LayoutParams okParam = new RelativeLayout.LayoutParams(Const.rx(0.1), Const.ry(0.1));
		RelativeLayout.LayoutParams backParam = new RelativeLayout.LayoutParams(Const.rx(0.1), Const.ry(0.1));
		RelativeLayout.LayoutParams atackTextParam = new RelativeLayout.LayoutParams(Const.rx(0.15), Const.ry(0.1));
		RelativeLayout.LayoutParams defenceTextParam = new RelativeLayout.LayoutParams(Const.rx(0.15), Const.ry(0.1));
		RelativeLayout.LayoutParams explainTextParam = new RelativeLayout.LayoutParams(Const.rx(0.3), Const.ry(0.2));
		RelativeLayout.LayoutParams cardNameTextParam = new RelativeLayout.LayoutParams(Const.rx(0.3), Const.ry(0.1));
		RelativeLayout.LayoutParams cardParam = new RelativeLayout.LayoutParams(Const.rx(0.2), Const.ry(0.3));
		
		/*okParam.setMargins(Const.rx(0.05), Const.ry(0.8),Const.rx(0.15),Const.ry(0.995));
		backParam.setMargins(Const.rx(0.25), Const.ry(0.8),Const.rx(0.35),Const.ry(0.995));
		atackTextParam.setMargins(Const.rx(0.05), Const.ry(0.6),Const.rx(0.2),Const.ry(0.7));
		defenceTextParam.setMargins(Const.rx(0.25), Const.ry(0.6),Const.rx(0.4),Const.ry(0.7));
		explainTextParam.setMargins(Const.rx(0.05), Const.ry(0.7),Const.rx(0.25),Const.ry(0.8));
		cardNameTextParam.setMargins(Const.rx(0.05), Const.ry(0.25),Const.rx(0.35),Const.ry(0.55));
		cardParam.setMargins(Const.rx(0.1), Const.ry(0.1),Const.rx(0.35),Const.ry(0.55));*/
		
		cardNameTextParam.setMargins(Const.rx(0.05), Const.ry(0.05),0,0);
		cardParam.setMargins(Const.rx(0.1), Const.ry(0.2),0,0);
		atackTextParam.setMargins(Const.rx(0.05), Const.ry(0.50),0,0);
		defenceTextParam.setMargins(Const.rx(0.20), Const.ry(0.50),0,0);
		explainTextParam.setMargins(Const.rx(0.05), Const.ry(0.60),0,0);
		okParam.setMargins(Const.rx(0.05), Const.ry(0.8),0,0);
		backParam.setMargins(Const.rx(0.25), Const.ry(0.8),0,0);
		
		Bitmap image2;
		image2 = BitmapFactory.decodeResource(getResources(), R.drawable.card);	
		ImageView card = new ImageView(this);
		card.setImageBitmap(image2);
		atackText.setBackgroundResource(R.drawable.list);
		defenceText.setBackgroundResource(R.drawable.list); 
		explainText.setBackgroundResource(R.drawable.list); 
		
		
		rl.addView(cardNameText, cardNameTextParam);
		rl.addView(okButton, okParam);
		rl.addView(backButton,backParam);
		rl.addView(atackText,atackTextParam);
		rl.addView(defenceText,defenceTextParam);
		rl.addView(explainText,explainTextParam);
		
		rl.addView(card, cardParam);
		
		String[] name = {"車","バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","利光","河津",
						};
		Bitmap image1;
        image1 = BitmapFactory.decodeResource(getResources(), R.drawable.atack);
		Bitmap[] bmp = {image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,
						};
		String[] atack = {"攻撃力 1000","攻撃力 2000","攻撃力 3000","攻撃力 1000","攻撃力 2000","攻撃力 3000","攻撃力 1000","攻撃力 2000","攻撃力 3000","攻撃力 1000","攻撃力 1000"};
		String[] defence = {"防御力　500","防御力　600","防御力　700","防御力　500","防御力　600","防御力　700","防御力　500","防御力　600","防御力　700","防御力　500","防御力　500"};
		
		String[] explain = {"あああああああああじあああああああああじあああああああああじ",
								"バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","利光","河津",
							};			
		
				
		List<CardData> objects = new ArrayList<CardData>();
		for(int i=0;i<name.length;i++){
			CardData object = new CardData();
			object.setImagaData(bmp[i]);
			object.setNameData(name[i]);
			object.setAtackData(atack[i]);
			object.setDefenceData(defence[i]);
			object.setExplainData(explain[i]);
			
			objects.add(object);
		}
		//SampleAdapter ad = new SampleAdapter(this,android.R.layout.simple_list_item_1,str);
		CardDataAdapter ad = new CardDataAdapter(this,0,objects);
		lv.setAdapter(ad);
		
		
		
		okButton.setOnClickListener(new SampleClickListener());
	}
	class SampleClickListener implements android.view.View.OnClickListener{
		Intent it;
		public void onClick(View v) {
			it = new Intent();
			it.setAction(Intent.ACTION_VIEW);
			it.setData(Uri.parse("tel:12345678"));
			startActivity(it);
			
		}
		
	}
	class SampleAdapter extends ArrayAdapter{

		public SampleAdapter(Context context, int textViewResourceId,
				Object[] objects) {
			super(context, textViewResourceId, objects);
			// TODO 自動生成されたコンストラクター・スタブ
		}
		public View getView(int pos,View convView,ViewGroup parent){
			String s = "------"+(String)((ListView)parent).getItemAtPosition(pos);
			TextView t = new TextView(getBaseContext());
			t.setText(pos + s);
			
			if(pos%2==0){
				t.setBackgroundColor(Color.GRAY);
			}
			return t;
			
		}
		
	}
	
	  
	
	

}