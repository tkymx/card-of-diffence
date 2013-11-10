package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * �������ɁAsprite�̃��\�b�h��p���āA�摜�̒ǉ����s��
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class Enemy extends Charactor {
	
	public Enemy(int hp, int attack, int speed) {
		super(hp, attack, speed);
		// TODO Auto-generated constructor stub
	}

	// ����������
	public void Init()
	{
		
	}
	
	@Override
	//�^�[�Q�b�g�̔��f
	protected boolean IsTarget(Sprite sp) {
		
		if( sp instanceof Player )return true;
		if( sp instanceof PlayerCastle )return true;
		
		return false;
	}		
		
	@Override
	//�L�����N�^�̍U�����󂯂���
	public void Damage( Charactor c )
	{
		super.Damage(c);

		//�_���[�W�G�t�F�N�g
		AnimationEffect.Create( 
				this.getTrans().getX() , 
				this.getTrans().getY() + m_height/2  - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());

	}	
	//�ʏ�̃_���[�W���󂯂���
	@Override	
	public void Damage( int c )
	{
		super.Damage(c);

		//�_���[�W�G�t�F�N�g
		AnimationEffect.Create( 
				this.getTrans().getX() + m_width/2  - Const.rx(0.1)/2, 
				this.getTrans().getY() + m_height/2  - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());

	}	
	

}
