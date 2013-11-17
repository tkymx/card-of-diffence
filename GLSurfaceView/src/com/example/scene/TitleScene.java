package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import android.content.Intent;

import com.example.glsurfaceview.OpenGLSurfaceView;
import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.SceneManager;
import com.example.glsurfaceview.Touch;

public class TitleScene extends Scene {

	@Override
	public void Init() {
		// TODO Auto-generated method stub

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
		}
	}

	@Override
	public void Draw(GL10 gl) {

		super.Draw(gl);
		
	}

}
