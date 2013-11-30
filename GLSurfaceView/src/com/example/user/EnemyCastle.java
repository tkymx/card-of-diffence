package com.example.user;

import java.util.Random;

import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Vector3;

/**
 * 
 * �G�̂���̕`��A�̗͂��Ȃ��Ȃ����珟��
 * 
 * @author ultra-tkymx
 *
 */

public class EnemyCastle extends Castle {

	int time;
	
	Random r = null;
	
	public EnemyCastle(int h) {
		super(h);
		// TODO Auto-generated constructor stub
		
		time = 0;
		
		//�����̏�����
		r = new Random(System.currentTimeMillis());
		
	}

	// ����������
	public void Init()
	{
		EnemyCastleHPBar.createEnemyCastleHPBar(this);
	}
	
	// �X�V����
	public boolean Update()
	{
		//����ł������
		//�ŏI�I�ɂ͏�ɂ���Ԃ��������āA��Ŕ������N��������ɔs�k�Ȃǂ̕������o�����A�Q�[�����I������\��
		
		//����ł������
		if( hp <= 0 )
		{

			if( time >= 100 )
			{
				//�Q�[���N���A�ɂ���
				DataBase.setResult(true);
				DataBase.setWin(true);

				return false;						
			}
			else
			{
				//���Ԃ�i�߂�
				time+=1;
				
				//�����̈ʒu
				Vector3 p = new Vector3( getTrans() );
				//�T�C�Y
				Vector3 s = new Vector3( GetWidth() , GetHeight() , 0 );
				
				for(int i = 0 ; i < 1; i ++)
				{
					//�����ł��͈̔͂ō��W�����߂�
					int x = r.nextInt((int)s.getX());
					int y = r.nextInt((int)s.getY());
					
					//�_���[�W�G�t�F�N�g
					AnimationEffect.Create( 
							p.getX() + x - Const.rx(0.15)/2,
							p.getY() + y - Const.rx(0.15)/2,
							Const.rx(0.15), 
							Const.rx(0.15), 
							10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());	
					
				}				
				
			}
			
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
