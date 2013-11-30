package com.example.glsurfaceview;

import com.example.data.DataBase;
import com.example.user.Stage;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class StageView extends View {
	
	Bitmap stageGraph = null;
	
	//ステージ番号//////////////////////////////////
	int stageNumber;		
	public int getStageNumber() {
		return stageNumber;
	}
	public void setStageNumber(int stageNumber) {
		this.stageNumber = stageNumber;
	}
	
	//ステージ情報///////////////////////////////////
	Stage stage = null;
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public StageView(Context cn,int sn,Stage s){
		super(cn);
		//ステージ番号を入れる
		stageNumber = sn;
		
		//ステージの設定
		stage = s;
		
		//画像取得
		stageGraph = BitmapFactory.decodeResource(getResources(), stage.getStage_select_image_id() );
		
	}
	public void onDraw(Canvas cs){
		super.onDraw(cs);

		//背景画像の表示
		cs.drawBitmap(stageGraph, new Rect(0, 0, stageGraph.getWidth(), stageGraph.getHeight() ), new Rect(0, 0, cs.getWidth(), cs.getHeight() ), new Paint());
		
		cs.drawText("ステージ"+getStageNumber(), 100, 100, new Paint());

	}
	
	//ステージを移動する
	public void moveStage()
	{
		
		//ステージの設定
		DataBase.setPresentStage(stage);
		
	}
}


