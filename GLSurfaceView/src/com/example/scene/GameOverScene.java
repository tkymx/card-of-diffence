package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Const.SpriteType;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.SceneManager;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Touch;

public class GameOverScene extends Scene {

	@Override
	public void Init() {
		
		//”wŒi‰æ‘œ
		Sprite.Create(0, 0, Const.rx(1), Const.ry(1) , R.drawable.gameover, SpriteType.TYPE_BG.getValue());

		
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
