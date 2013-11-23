package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;
import com.example.user.Charactor.Charactor_State;

public class Player extends Charactor {

	public Player(int hp, int attack, int speed,int wi,int abi,int afi) {
		super(hp, attack, speed, wi, abi, afi);
		// TODO Auto-generated constructor stub
	}

	// ����������
	public void Init()
	{
		//�v���C���[��HP�o�[���擾
		PlayerHPBar.createPlayerHPBar(this);
	}
	

	@Override
	//�^�[�Q�b�g�̔��f
	protected boolean IsTarget(Sprite sp) {
		
		if( sp instanceof Enemy )return true;
		if( sp instanceof EnemyCastle )return true;
		
		return false;
	}		
		
	@Override	
	public void Damage( Charactor c )
	{
		super.Damage(c);

		//�_���[�W�G�t�F�N�g
		AnimationEffect.Create( 
				this.getTrans().getX() + m_width - Const.rx(0.1), 
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
