package com.example.user;
import com.example.glsurfaceview.SpriteAnimation;

//�邪���󂵂������̃G�t�F�N�g��t����
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
		
		//�I�����Ă�����false��ς����B
		if( isEnd == true )return false;
		
		return true;
	}
}
