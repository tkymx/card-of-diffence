package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.Sprite;
import com.example.user.Player;

public class GameScene extends Scene {

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		
		Player player = new Player();
		player.Init( 500, 500, 100, 400, R.drawable.image1, Const.SpriteType.TYPE_OTHER.getValue() );		

	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(GL10 gl) {
		// TODO Auto-generated method stub
		
	}

}
