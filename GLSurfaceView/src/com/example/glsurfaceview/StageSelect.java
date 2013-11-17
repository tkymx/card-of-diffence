package com.example.glsurfaceview;

import java.util.Random;

import com.example.glsurfaceview.DeckSelect.SampleClickListener;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.Button;

public class StageSelect extends Activity{
	public ViewFlipper vf;
	public float x;
	public StageView[] sv = new StageView[3];
	LinearLayout ll,secondll;
	TextView title;
	Button left,right,deck;
	
	public static int width, height;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		WindowManager windowmanager = (WindowManager)getSystemService(WINDOW_SERVICE);
		Point point = new Point(); 
		Display disp = windowmanager.getDefaultDisplay(); 
		disp.getSize(point);
		
		width = point.x;
		height = point.y;
		
		vf = new ViewFlipper(this);
		title = new TextView(this);
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		secondll = new LinearLayout(this);
		left = new Button(this);
		right = new Button(this);
		deck = new Button(this);
		LinearLayout.LayoutParams titleParam = new LinearLayout.LayoutParams(Const.rx(0.7), Const.ry(0.1));
		LinearLayout.LayoutParams leftParam = new LinearLayout.LayoutParams(Const.rx(0.1), Const.ry(0.6));
		LinearLayout.LayoutParams vfParam = new LinearLayout.LayoutParams(Const.rx(0.5), Const.ry(0.6));
		LinearLayout.LayoutParams rightParam = new LinearLayout.LayoutParams(Const.rx(0.1), Const.ry(0.6));
		LinearLayout.LayoutParams deckParam = new LinearLayout.LayoutParams(Const.rx(0.3), Const.ry(0.1));
		
		titleParam.setMargins(Const.rx(0.1), Const.ry(0.05),0,0);
		leftParam.setMargins(Const.rx(0.1), Const.ry(0.05),0,0);
		vfParam.setMargins(Const.rx(0.05), Const.ry(0.05),0,0);
		rightParam.setMargins(Const.rx(0.05), Const.ry(0.05),0,0);
		deckParam.setMargins(Const.rx(0.35), Const.ry(0.05),0,0);
		title.setTextSize(Const.ry(0.04));
		
		title.setBackgroundResource(R.drawable.list);
		ll.setBackgroundResource(R.drawable.background);
		right.setBackgroundResource(R.drawable.explain);
		left.setBackgroundResource(R.drawable.explain);
		deck.setBackgroundResource(R.drawable.explain);
		
		setContentView(ll);
		ll.addView(title,titleParam);
		ll.addView(secondll);
		ll.addView(deck,deckParam);
		secondll.addView(left,leftParam);
		secondll.addView(vf,vfParam);
		secondll.addView(right,rightParam);
		
		title.setText("ステージ選択画面");
		right.setText("右");
		left.setText("左");
		deck.setText("デッキ編集");
		
		for(int i=0;i<sv.length;i++){			
			sv[i] = new StageView(this,i+1);			
			//sv[i].setOnClickListener(new SampleStageViewClickLisnear());
			sv[i].setOnTouchListener(new SampleTL());
			vf.addView(sv[i]);						
		}
		
		//基本的なクリックリスナー
		
		//vf.setOnTouchListener(new SampleTL());
		right.setOnClickListener(new SampleClickListener());
		left.setOnClickListener(new SampleClickListener());
		deck.setOnClickListener(new SampleClickListener());
		
	}
	////
	
//ステージが押された時の処理
class SampleStageViewClickLisnear implements android.view.View.OnClickListener{	

	public void onClick(View v) {
		
		//ステージだったら移動して終了
		if( v instanceof StageView )
		{
			
			//ステージの移動
			((StageView)v).moveStage();
			
			//終了
			finish();
			
		}
		
	}
}	
	

class SampleClickListener implements android.view.View.OnClickListener{
	
	public void onClick(View v) 
	{
		if(v.equals(right))
		{
			TranslateAnimation inanim = new TranslateAnimation(-sv[0].getWidth(),0,0,0);
			TranslateAnimation outanim = new TranslateAnimation(0,sv[0].getWidth(),0,0);
			inanim.setDuration(100);
			outanim.setDuration(100);
			vf.setInAnimation(inanim);
			vf.setOutAnimation(outanim);
			vf.showPrevious();
		}
		else if(v.equals(left))
		{
			TranslateAnimation inanim = new TranslateAnimation(sv[0].getWidth(),0,0,0);
			TranslateAnimation outanim = new TranslateAnimation(0,-sv[0].getWidth(),0,0);
			inanim.setDuration(100);
			outanim.setDuration(100);
			vf.setInAnimation(inanim);
			vf.setOutAnimation(outanim);
			vf.showNext();			
		}
		else if( v.equals(deck) )
		{
			//デッキ表示のインテントの発行
			Intent intent = new Intent();
			//intent.setClass( OpenGLSurfaceView.c , com.example.glsurfaceview.DeckSelect.class);
			intent.setClass( OpenGLSurfaceView.c , com.example.glsurfaceview.DeckEdit.class);
			
			OpenGLSurfaceView.c.startActivity(intent);							
		}		
		
	}
	
}
	////
	 

class SampleTL implements OnTouchListener{
	
	@Override
	public boolean onTouch(View v, MotionEvent e) {
		if(e.getAction() ==MotionEvent.ACTION_DOWN){
			x=e.getX();
		}else if(e.getAction() ==MotionEvent.ACTION_UP){
			if(x-20>e.getX()){
				TranslateAnimation inanim = new TranslateAnimation(sv[0].getWidth(),0,0,0);
				TranslateAnimation outanim = new TranslateAnimation(0,-sv[0].getWidth(),0,0);
				inanim.setDuration(1000);
				outanim.setDuration(1000);
				vf.setInAnimation(inanim);
				vf.setOutAnimation(outanim);
				
				vf.showNext();
			}else if(x+20<e.getX()){
				TranslateAnimation inanim = new TranslateAnimation(-sv[0].getWidth(),0,0,0);
				TranslateAnimation outanim = new TranslateAnimation(0,sv[0].getWidth(),0,0);
				inanim.setDuration(1000);
				outanim.setDuration(1000);
				vf.setInAnimation(inanim);
				vf.setOutAnimation(outanim);
				vf.showPrevious();
			}else if(x-20<=e.getX() && x+20>=e.getX()){
				if( v instanceof StageView )
				{
					
					//ステージの移動
					((StageView)v).moveStage();
					
					//終了
					finish();
					
				}
			}
		}
		return true;
	}
	
}


}