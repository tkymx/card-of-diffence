package com.example.glsurfaceview;

import java.util.LinkedList;

import javax.microedition.khronos.opengles.GL10;

public class Game implements Scene {
	
	Sprite s;
	
	// �R���X�g���N�^
	public Game()
	{		
		// �e�̃R���X�g���N�^���Ă�
		super();
		
		// �X�v���C�g�̒ǉ�
		s = Sprite.Create( 0, 0, 600, 400, R.drawable.image1, Const.SpriteType.TYPE_OTHER.getValue() );
	}
	
	@Override
	public void Init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub
		Sprite.removeAll();
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
		for( int i = 0; i < Const.SpriteType.TYPE_MAX.getValue(); i++ )
		{
			LinkedList<Sprite> list = Sprite.spriteList.get(i);
			
			// ���X�g�ɓo�^����Ă���V�[���̐������[�v
			for( int j = 0; j < list.size(); j++ )
			{
				// �g�p���̂Ƃ�
				if( list.get(j).GetUse() == true )
				{
					Vector3 vec = new Vector3( 1.0f, 0, 0 );
					s.Translate(vec);
				}
			}
		}
	}

	@Override
	public void Draw(GL10 gl) {
		// TODO Auto-generated method stub
		
		for( int i = 0; i < Const.SpriteType.TYPE_MAX.getValue(); i++ )
		{
			LinkedList<Sprite> list = Sprite.spriteList.get(i);
			
			// ���X�g�ɓo�^����Ă���V�[���̐������[�v
			for( int j = 0; j < list.size(); j++ )
			{
				// �g�p���̂Ƃ�
				if( list.get(j).GetUse() == true )
				{
					list.get(j).DrawSprite(gl);
				}
			}
		}
	}
}