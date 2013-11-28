package com.example.user;

import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;

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
		//���HP��\��
		PlayerCastleHPBar.createPlayerCastleHPBar(this);
	}
	
	// �X�V����
	public boolean Update()
	{
		//�ŏI�I�ɂ͏�ɂ���Ԃ��������āA��Ŕ������N��������ɔs�k�Ȃǂ̕������o�����A�Q�[�����I������\��
		
		//����ł������
		if( hp <= 0 )
		{
			//�Q�[���I�[�o�[�ɂ���
			DataBase.setResult(true);
			DataBase.setWin(false);
			return false;		
		}
		
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
