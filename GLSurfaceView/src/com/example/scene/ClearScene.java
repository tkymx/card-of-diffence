package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.SceneManager;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Touch;
import com.example.glsurfaceview.Const.SpriteType;

public class ClearScene extends Scene {

	@Override
	public void Init() {
		
		//背景画像
		Sprite.Create(0, 0, Const.rx(1), Const.ry(1) , R.drawable.clear, SpriteType.TYPE_BG.getValue());

		//クリア後はステージ数の更新を行う
		DataBase.setPresentStageNum( DataBase.getPresentStage().getClear_stage_number() ); 
		
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() {
		
		super.Update();
		
		if( Touch.getInstance().IsTouch() )
		{
			
			SceneManager.ChangeScene( SceneManager.titleKey );
		}
		
	}

	@Override
	public void Draw(GL10 gl) {
		
		super.Draw(gl);
		
	}

}
