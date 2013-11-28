package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.OpenGLSurfaceView;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public class StartNotify extends Sprite {
	
	float time;
	float timev;
	
	// ¶¬ ‚±‚ê„§
	public static StartNotify Create()
	{
		StartNotify s = new StartNotify();
		
		// ‰Šú‰»
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
		
		//ˆÚ“®
		timev+=0.0003;
		time-=timev;		
		GetTexture().SetColor(1, 1, 1, time);
		
		//ˆê’èˆÊ’u‚ÅI—¹
		if( time < 0 )
		{
			OpenGLSurfaceView.GameStart();
			
			return false;
		}
		
		return true;
	}

}
