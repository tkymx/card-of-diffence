//////////////////////////////////////////////////////////////////
//	�������p�����Ďg���ꍇ��game.java���Q�l�ɍ���Ă��������B
//////////////////////////////////////////////////////////////////
package com.example.glsurfaceview;

import java.util.LinkedList;

import javax.microedition.khronos.opengles.GL10;

public abstract class Scene {

	public abstract void Init();
	public abstract void Uninit();
	
	public abstract void onUpdate();
	public void Update()
	{
		for( int i = 0; i < Const.SpriteType.TYPE_MAX.getValue(); i++ )
		{
			LinkedList<Sprite> list = Sprite.spriteList.get(i);
			
			// ���X�g�ɓo�^����Ă���V�[���̐������[�v
			for( int j = 0; j < list.size(); j++ )
			{
				// �g�p���̂Ƃ�
				if( list.get(j).GetUse() == true )
				{
					Sprite sp = list.get(j);
					
					//fasle���A���Ă��������
					if(sp.Update()==false)
					{
						sp.remove();
					}
				}
			}
		}		
		onUpdate();
	}
	public abstract void onDraw( GL10 gl );
	public void Draw( GL10 gl )
	{
		for( int i = 0; i < Const.SpriteType.TYPE_MAX.getValue(); i++ )
		{
			LinkedList<Sprite> list = Sprite.spriteList.get(i);
			
			// ���X�g�ɓo�^����Ă���V�[���̐������[�v
			for( int j = 0; j < list.size(); j++ )
			{
				// �g�p���̂Ƃ�
				if( list.get(j).GetUse() == true )
				{
					Sprite sp = list.get(j);
					sp.DrawSprite(gl);
				}
			}
		}			
		onDraw(gl);
	}
}
