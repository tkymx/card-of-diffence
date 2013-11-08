package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * �v���C���[�̏�̕`��Ƒ̗͂��Ȃ��Ȃ�����I���@����
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class PlayerCastle extends Castle {

	public PlayerCastle(int h) {
		super(h);
		// TODO Auto-generated constructor stub
	}

	// ����������
	public void Init()
	{
	}
	
	// �X�V����
	public boolean Update()
	{
		//����ł������
		if( hp <= 0 )return false;		
		
		return true;		
	}	
	
	
	//�U�����󂯂���
	public void Damage(Charactor c)
	{
		super.Damage(c);
		
		//�_���[�W�G�t�F�N�g
		AnimationEffect.Create( 
				c.getTrans().getX() - Const.rx(0.1), 
				c.getTrans().getY() + c.getM_height()/2 - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());				
	}	
}
