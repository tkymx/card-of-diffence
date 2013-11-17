package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.SceneManager;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * �G�̂���̕`��A�̗͂��Ȃ��Ȃ����珟��
 * 
 * @author ultra-tkymx
 *
 */

public class EnemyCastle extends Castle {

	public EnemyCastle(int h) {
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
		//�ŏI�I�ɂ͏�ɂ���Ԃ��������āA��Ŕ������N��������ɔs�k�Ȃǂ̕������o�����A�Q�[�����I������\��
		
		//����ł������
		if( hp <= 0 )
		{
			//�Q�[���I�[�o�[��ʂɈړ�
			SceneManager.ChangeScene( SceneManager.clearKey );
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
				c.getTrans().getX() + c.getM_width(), 
				c.getTrans().getY() + c.getM_height()/2 - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());		
	}
	
}
