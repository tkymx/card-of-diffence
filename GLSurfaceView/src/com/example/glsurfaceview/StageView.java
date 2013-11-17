package com.example.glsurfaceview;

import java.util.Random;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class StageView extends View {
	final int NUM =100;
	Ball[] bl = new Ball[NUM];
	Paint p = new Paint();
	
	//ステージ番号
	int stageNumber;	
	public int getStageNumber() {
		return stageNumber;
	}
	public void setStageNumber(int stageNumber) {
		this.stageNumber = stageNumber;
	}
	
	
	
	public StageView(Context cn,int sn){
		super(cn);
		Random rn = new Random();
		for(int i=0;i<NUM;i++){
			bl[i] = new Ball();
			bl[i].x=rn.nextInt(600);
			bl[i].y=rn.nextInt(800);
			bl[i].r=rn.nextInt(256);
			bl[i].g=rn.nextInt(256);
			bl[i].b=rn.nextInt(256);
		}
		
		//ステージ番号を入れる
		stageNumber = sn;
		
	}
	public void onDraw(Canvas cs){
		super.onDraw(cs);
		for(int i=0;i<NUM;i++){
			p.setColor(Color.rgb(bl[i].r, bl[i].g, bl[i].b));
			cs.drawCircle(bl[i].x, bl[i].y, 10, p);			
		}
		
		cs.drawText("ステージ"+getStageNumber(), 100, 100, new Paint());
		
	}
	class Ball{
		int x,y,r,g,b;
	}
	
	//ステージを移動する
	public void moveStage()
	{
		SceneManager.ChangeScene( SceneManager.gameKey );
	}
}


