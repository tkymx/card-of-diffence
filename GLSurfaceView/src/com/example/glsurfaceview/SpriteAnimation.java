//////////////////////////////////////////////////////////////////
//	��������Ƃ��ɉ摜�����������邩�����߂Ă�������
//	��) ��:3	�c:1	�݂����Ȍ`�ł��B
//	���Ƃ͂Ȃ�t���[���ŉ摜���X�V����邩�̎��Ԃ��w�肵�Ă�������
//	��)	����:30	30�t���[���ōX�V
//	�������p�������N���X�����Ƃ���Update�ɂ�����Update��ǂ�ł�������
//////////////////////////////////////////////////////////////////
package com.example.glsurfaceview;

public class SpriteAnimation extends Sprite {
	
	private float animeUVX, animeUVY;
	private float[] animeUV = new float[2];
	private int animeTime, animeNowTime;
	private float[] UV = new float[8];
	
	// �R���X�g���N�^
	public SpriteAnimation()
	{
	}
	
	// ����
	public static SpriteAnimation Create( float left, float top, float width, float height, int UVWidth, int UVHeight, int animeTime, int id, int SpriteType )
	{
		SpriteAnimation s = new SpriteAnimation();
		
		s.Init( left, top, width, height, UVWidth, UVHeight, animeTime, id, SpriteType );
		
		return s;
	}
	
	// ����������
	public void Init( float left, float top, float width, float height, int UVWidth, int UVHeight, int animeTime, int id, int SpriteType )
	{
		super.Init(left, top, width, height, id, SpriteType);
		
		this.animeTime = animeTime;
		
		animeUV[0] = 0.0f;
		animeUV[1] = 0.0f;
		animeUVX = 1.0f / ( float )UVWidth;
		animeUVY = 1.0f / ( float )UVHeight;
		
		makeUV();
		
		texture.SetUV(UV);
	}
	
	// �X�V����
	@Override
	public boolean Update()
	{
		// �A�j���[�V�����̎��Ԃ���莞�Ԃ������Ƃ�
		if( ( animeNowTime % animeTime ) == 0 )
		{
			// �e�N�X�`���̍X�V
			animeUV[0] += animeUVX;
			animeUV[1] += animeUVY;
			
			// �A�j���[�V�������e�N�X�`���ő�𒴂����Ƃ�
			if( animeUV[0] >= 1.0f )
			{
				animeUV[0] = 0.0f;
			}
			
			// �A�j���[�V�������e�N�X�`���ő�𒴂����Ƃ�
			if( animeUV[1] >= 1.0f )
			{
				animeUV[1] = 0.0f;
			}
			
			makeUV();
			
			// UV�̃Z�b�g
			texture.SetUV( UV );
		}
		
		// ���Ԃ̃C���N�������g
		animeNowTime++;
		
		return true;
	}
	
	// �A�j���[�V�����̍��W��Ԃ�
	public float[] getAnime()
	{
		return animeUV;
	}
	
	// UV�̉����̎擾
	public float getAnimeWidth()
	{
		return animeUVX;
	}
	
	// UV�̏c���̎擾
	public float getAnimeHiehgt()
	{
		return animeUVY;
	}
	
	// UV���W�̐���
	private void makeUV( )
	{
		UV[0] =	animeUV[0];
		UV[1] = animeUV[1];
		UV[2] = animeUV[0];
		UV[3] = animeUV[1] + animeUVY;
		UV[4] = animeUV[0] + animeUVX;
		UV[5] = animeUV[1];
		UV[6] = animeUV[0] + animeUVX;
		UV[7] = animeUV[1] + animeUVY;
	}
	
}
