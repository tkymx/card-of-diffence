package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.OpenGLSurfaceView;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public class StartNotify extends Sprite {
	
	float time;
	float timev;
	
	// 生成 これ推奨
	public static StartNotify Create()
	{
		StartNotify s = new StartNotify();
		
		// 初期化
		s.Init( Const.rx(0.3), Const.ry(0.4) , Const.rx(0.4), Const.ry(0.4) , R.drawable.game_start ,Const.SpriteType.TYPE_EFFECT.getValue() );
		
		return s;
	}	
	
	private StartNotify()
	{		
		
		time = 2.0f;
		timev = 0.0f;
	}

	public void Init()
	{
	}	
	
	public boolean Update()
	{
		if(super.Update()==false)return false;
		
		//移動
		timev+=0.0003;
		time-=timev;		
		GetTexture().SetColor(1, 1, 1, time);
		
		//一定位置で終了
		if( time < 0 )
		{
			OpenGLSurfaceView.GameStart();
			
			return false;
		}
		
		return true;
	}

}
