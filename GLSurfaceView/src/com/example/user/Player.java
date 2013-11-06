package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;
import com.example.user.Charactor.Charactor_State;

public class Player extends Charactor {

	public Player(int hp, int attack) {
		super(hp, attack);
		// TODO Auto-generated constructor stub
	}

	// ����������
	public void Init()
	{
		
	}
	
	// �X�V����
	@Override	
	protected void move_state()
	{

		//�������ǂ���
		boolean moveflag = true;
		//�G��������~�܂�
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_ENEMY.getValue() ) )
		{
			if( sp instanceof Enemy )
			{
				Enemy enemy = (Enemy)sp;
				
				//�����s��������
				if( enemy.getTrans().getY() == this.getTrans().getY() )
				{			
					//�����𔻒f���Ď~�܂�B
					if( enemy.getTrans().getX() < this.getTrans().getX() + this.GetWidth())
					{
						//�ړ������Ȃ�
						moveflag = false;
						
						//�U����Ԃɂ���
						StartAttack( enemy );
						setState( Charactor_State.ATTACK_STATE );
						
						//�I��
						break;						
					}
				}			
			}
		}
		//�ړ�
		if(moveflag==true)move();
			
	}	
	
	private void move()
	{
		//��ɃA�^�b�N���邩�ǂ��������߂��t���O
		boolean castle_attack_flag = false;
		
		if( getTrans().getY() == Const.LINE_1_Y )
		{
			this.Translate( new Vector3( Const.LINE_1_SPEED ,0,0) );
			
			//���W�K��(��܂Ői��)
			if( getTrans().getX() > Const.LINE_RIGHT_1_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_1_X , Const.LINE_1_Y , 0 ) );
				//��t���O�𗧂Ă�
				castle_attack_flag = true;
			}			
		}
		else if( getTrans().getY() == Const.LINE_2_Y )
		{
			this.Translate( new Vector3( Const.LINE_2_SPEED ,0,0) );
			
			//���W�K��(��܂Ői��)
			if( getTrans().getX() > Const.LINE_RIGHT_2_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_2_X , Const.LINE_2_Y , 0 ) );
				//��t���O�𗧂Ă�
				castle_attack_flag = true;
			}			
		}
		else if( getTrans().getY() == Const.LINE_3_Y )
		{
			this.Translate( new Vector3( Const.LINE_3_SPEED ,0,0) );
			
			//���W�K��(��܂Ői��)
			if( getTrans().getX() > Const.LINE_RIGHT_3_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_3_X , Const.LINE_3_Y , 0 ) );
				//��t���O�𗧂Ă�
				castle_attack_flag = true;
			}			
		}		
		
		//�邾������
		if( castle_attack_flag )
		{
			//��Ń��[�v����
			for( Sprite sprite : Sprite.spriteList.get( Const.SpriteType.TYPE_CASLE.getValue() ) )
			{
				//�G�̏邾������U���ΏۂƂ��ăZ�b�g
				if( sprite instanceof EnemyCastle )
				{
					//����Z�b�g���čU����Ԃɂ���
					setCastleTarget( (EnemyCastle)sprite );
					setState( Charactor_State.ATTACK_STATE );
				}
			}
		}
		else
		{
			//��ԋ�����Ȃ����������U�����Ȃ�
			setCastleTarget(null);
		}
	}

	@Override
	protected void attack_state() {		
		
		//�U������
		if( !UpdateAttack() )
		{
			//�U���Ώۂ���O��
			setAttackTarget( null );		
			//���s��Ԃɂ���
			setState( Charactor_State.WALK_STATE );			
		}
	}
	
	@Override	
	public void Damage( Charactor c )
	{
		super.Damage(c);

		//�_���[�W�G�t�F�N�g
		AnimationEffect.Create( 
				this.getTrans().getX() + m_width - Const.rx(0.05), 
				this.getTrans().getY() + m_height/2  - Const.rx(0.05)/2, 
				Const.rx(0.05), 
				Const.rx(0.05), 
				3, 1, 10, R.drawable.image1, Const.SpriteType.TYPE_EFFECT.getValue());

	}	
	//�ʏ�̃_���[�W���󂯂���
	@Override	
	public void Damage( int c )
	{
		super.Damage(c);

		//�_���[�W�G�t�F�N�g
		AnimationEffect.Create( 
				this.getTrans().getX() + m_width/2  - Const.rx(0.05)/2, 
				this.getTrans().getY() + m_height/2  - Const.rx(0.05)/2, 
				Const.rx(0.05), 
				Const.rx(0.05), 
				3, 1, 10, R.drawable.image1, Const.SpriteType.TYPE_EFFECT.getValue());

	}		

}
