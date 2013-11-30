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
	
	//�X�e�[�W�ԍ�//////////////////////////////////
	int stageNumber;		
	public int getStageNumber() {
		return stageNumber;
	}
	public void setStageNumber(int stageNumber) {
		this.stageNumber = stageNumber;
	}
	
	//�X�e�[�W���///////////////////////////////////
	Stage stage = null;
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public StageView(Context cn,int sn,Stage s){
		super(cn);
		//�X�e�[�W�ԍ�������
		stageNumber = sn;
		
		//�X�e�[�W�̐ݒ�
		stage = s;
		
		//�摜�擾
		stageGraph = BitmapFactory.decodeResource(getResources(), stage.getStage_select_image_id() );
		
	}
	public void onDraw(Canvas cs){
		super.onDraw(cs);

		//�w�i�摜�̕\��
		cs.drawBitmap(stageGraph, new Rect(0, 0, stageGraph.getWidth(), stageGraph.getHeight() ), new Rect(0, 0, cs.getWidth(), cs.getHeight() ), new Paint());
		
		cs.drawText("�X�e�[�W"+getStageNumber(), 100, 100, new Paint());

	}
	
	//�X�e�[�W���ړ�����
	public void moveStage()
	{
		
		//�X�e�[�W�̐ݒ�
		DataBase.setPresentStage(stage);
		
	}
}


