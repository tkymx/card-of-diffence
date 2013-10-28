package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.SceneManager;

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
	public void onUpdate() {
		// TODO Auto-generated method stub
		
		SceneManager.ChangeScene( SceneManager.gameKey );
		
	}

	@Override
	public void onDraw(GL10 gl) {
		// TODO Auto-generated method stub
		
	}

}
