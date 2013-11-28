package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import android.content.Intent;

import com.example.glsurfaceview.BGMSound;
import com.example.glsurfaceview.BGMSound.SOUND;
import com.example.glsurfaceview.Const.SpriteType;
import com.example.glsurfaceview.MainActivity;
import com.example.glsurfaceview.OpenGLSurfaceView;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Touch;

public class TitleScene extends Scene {

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		Sprite.Create(0, 0, MainActivity.width, MainActivity.height, R.drawable.bg, SpriteType.TYPE_OTHER.getValue());
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Update() {

		super.Update();		
		
		Touch touch = Touch.getInstance();
		
		if( touch.IsTouch() )
		{
			Intent intent = new Intent();
			intent.setClass( OpenGLSurfaceView.c , com.example.glsurfaceview.StageSelect.class);
			OpenGLSurfaceView.c.startActivity(intent);
			
			BGMSound.BGMChoose[SOUND.SOUND_ENTER.getValue()].Play();
		}
	}

	@Override
	public void Draw(GL10 gl) {

		super.Draw(gl);
		
	}

}
