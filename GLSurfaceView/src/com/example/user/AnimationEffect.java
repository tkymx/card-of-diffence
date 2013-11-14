package com.example.user;

/**
 * 
 * アニメーションした後に自分から消える系
 * 
 */

import com.example.glsurfaceview.SpriteAnimation;

public class AnimationEffect extends SpriteAnimation {

	// 生成
	public static AnimationEffect Create( float left, float top, float width, float height, int UVWidth, int UVHeight, int animeTime, int id, int SpriteType )
	{
		AnimationEffect s = new AnimationEffect();		
		s.Init( left, top, width, height, UVWidth, UVHeight, animeTime, id, SpriteType );		
		return s;
	}
	
	
	
	// 更新処理
	@Override
	public boolean Update()
	{
		super.Update();
		
		//終了していたらfalseを変えす。
		if( isEnd == true )return false;
		
		return true;
	}
	
}
