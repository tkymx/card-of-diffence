package com.example.user;
import com.example.glsurfaceview.SpriteAnimation;

//城が崩壊したら崩壊のエフェクトを付ける
public class Collapse extends SpriteAnimation{
	public static Collapse Create( float left, float top, float width, float height, int UVWidth, int UVHeight, int animeTime, int id, int SpriteType ){
		Collapse s = new Collapse();
		s.Init( left, top, width, height, UVWidth, UVHeight, animeTime, id, SpriteType );		
		return s;
	}
	
	@Override
	public boolean Update()
	{
		super.Update();
		
		//終了していたらfalseを変えす。
		if( isEnd == true )return false;
		
		return true;
	}
}
