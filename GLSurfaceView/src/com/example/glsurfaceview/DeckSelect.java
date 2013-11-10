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
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeckSelect extends Activity{
	
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
		
		ll.addView(rl);
		ll.addView(lv);
		cardNameText.setText("ティガレックス");
		cardNameText.setTextSize(Const.rx(0.1));
		atackText.setText("攻撃力　53万");
		defenceText.setText("防御力　1");
		explainText.setText("やばいやつぐはははははは\nははははははははははは\nはははははははははははははは");
		
		RelativeLayout.LayoutParams okParam = new RelativeLayout.LayoutParams(WC, WC);
		RelativeLayout.LayoutParams backParam = new RelativeLayout.LayoutParams(WC, WC);
		RelativeLayout.LayoutParams atackTextParam = new RelativeLayout.LayoutParams(WC, WC);
		RelativeLayout.LayoutParams defenceTextParam = new RelativeLayout.LayoutParams(WC, WC);
		RelativeLayout.LayoutParams explainTextParam = new RelativeLayout.LayoutParams(WC, WC);
		RelativeLayout.LayoutParams cardNameTextParam = new RelativeLayout.LayoutParams(WC, WC);
		
		okParam.setMargins(Const.rx(0.05), Const.ry(0.9),Const.rx(0.1),Const.rx(0.995));
		backParam.setMargins(Const.rx(0.15), Const.ry(0.9),Const.rx(0.2),Const.rx(0.995));
		atackTextParam.setMargins(Const.rx(0.05), Const.ry(0.5),Const.rx(0.2),Const.rx(0.6));
		defenceTextParam.setMargins(Const.rx(0.05), Const.ry(0.6),Const.rx(0.2),Const.rx(0.7));
		explainTextParam.setMargins(Const.rx(0.05), Const.ry(0.7),Const.rx(0.2),Const.rx(0.9));
		cardNameTextParam.setMargins(Const.rx(0.05), Const.ry(0.1),Const.rx(0.5),Const.rx(0.4));
		
		rl.addView(okButton, okParam);
		rl.addView(backButton,backParam);
		rl.addView(atackText,atackTextParam);
		rl.addView(defenceText,defenceTextParam);
		rl.addView(explainText,explainTextParam);
		rl.addView(cardNameText, cardNameTextParam);
		
		String[] str = {"車","バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","利光","河津",
						"車","バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","利光","河津",
						"車","バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","利光","河津",
						"車","バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","利光","河津",
						"車","バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","利光","河津"};
		Bitmap image1;
        image1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Bitmap[] bmp = {image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,
						image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,
						image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,
						image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,
						image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,image1};
		
		List<CardData> objects = new ArrayList<CardData>();
		for(int i=0;i<str.length;i++){
			CardData object = new CardData();
			object.setImagaData(bmp[i]);
			object.setTextData(str[i]);
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