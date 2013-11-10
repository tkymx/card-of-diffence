package com.example.select;



import com.example.*;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Text;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeckSelect extends Activity{
	Button okButton;
	Button backButton;
	ListView lv;
	RelativeLayout rl;
	TextView atackText,defenceText,explainText;
	 private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
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
		
		ll.addView(rl);
		ll.addView(lv);
		
		atackText.setText("攻撃力　53万");
		defenceText.setText("防御力　1");
		explainText.setText("やばいやつぐははははははははははははははははははははははははははははははは");
		
		RelativeLayout.LayoutParams okParam = new RelativeLayout.LayoutParams(WC, WC);
		RelativeLayout.LayoutParams backParam = new RelativeLayout.LayoutParams(WC, WC);
		RelativeLayout.LayoutParams atackTextParam = new RelativeLayout.LayoutParams(WC, WC);
		RelativeLayout.LayoutParams defenceTextParam = new RelativeLayout.LayoutParams(WC, WC);
		RelativeLayout.LayoutParams explainTextParam = new RelativeLayout.LayoutParams(WC, WC);
		
		okParam.setMargins(Const.rx(0.05), Const.ry(0.9),Const.rx(0.1),Const.rx(0.995));
		backParam.setMargins(Const.rx(0.15), Const.ry(0.9),Const.rx(0.2),Const.rx(0.995));
		atackTextParam.setMargins(Const.rx(0.05), Const.ry(0.5),Const.rx(0.2),Const.rx(0.6));
		defenceTextParam.setMargins(Const.rx(0.05), Const.ry(0.6),Const.rx(0.2),Const.rx(0.7));
		explainTextParam.setMargins(Const.rx(0.05), Const.ry(0.7),Const.rx(0.2),Const.rx(0.9));
		
		rl.addView(okButton, okParam);
		rl.addView(backButton,backParam);
		rl.addView(atackText,atackTextParam);
		rl.addView(defenceText,defenceTextParam);
		rl.addView(explainText,explainTextParam);
		
		String[] str = {"車","バス","飛行機","タクシー","自転車","歩行","クラウン","小山","渡辺","徳光","河津"};
		SampleAdapter ad = new SampleAdapter(this,android.R.layout.simple_list_item_1,str);
		
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
