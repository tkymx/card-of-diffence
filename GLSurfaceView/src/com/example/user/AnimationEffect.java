package com.example.user;

/**
 * 
 * �A�j���[�V����������Ɏ������������n
 * 
 */

import com.example.glsurfaceview.SpriteAnimation;

public class AnimationEffect extends SpriteAnimation {

	// ����
	public static AnimationEffect Create( float left, float top, float width, float height, int UVWidth, int UVHeight, int animeTime, int id, int SpriteType )
	{
		AnimationEffect s = new AnimationEffect();		
		s.Init( left, top, width, height, UVWidth, UVHeight, animeTime, id, SpriteType );		
		return s;
	}
	
	
	
	// �X�V����
	@Override
	public boolean Update()
	{
		super.Update();
		
		//�I�����Ă�����false��ς����B
		if( isEnd == true )return false;
		
		return true;
	}
	
}
