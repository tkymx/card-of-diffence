package com.example.user;

import java.util.LinkedList;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.SpriteAnimation;
import com.example.glsurfaceview.Texture;
import com.example.glsurfaceview.Vector3;

public abstract class Charactor extends SpriteAnimation {

	//��Ԃ̊Ǘ�
	enum Charactor_State{ WALK_STATE , ATTACK_STATE , ATTACK_AFTER_STATE };
	
	private int lineNum;
		
	//�̗͂�U����
	int value_hp;
	int value_maxhp;
	int value_attack;
	int value_moveSpeed;

	//��Ԃ̊Ǘ�������
	Charactor_State state;
	
	//�U���Ώ�(�遄�G�̏���)
	Charactor attackTarget;
	Castle castleTarget;
	
	//�s���A�j���[�V����
	Texture walkTexture;
	Texture attackBeforeTexture;	
	Texture attackAfterTexture;	
	
	//�Z�b�^�[�ƃQ�b�^�[
	public int getValue_hp() {return value_hp;}
	public void setValue_hp(int value_hp) {this.value_hp = value_hp;}
	public int getValue_maxhp() {return value_maxhp;}
	public void setValue_maxhp(int value_maxhp){this.value_maxhp = value_maxhp;}
	public int getValue_attack() {return value_attack;}
	public void setValue_attack(int value_attack) {this.value_attack = value_attack;}	
	public int getValue_moveSpeed() {return value_moveSpeed;}
	public void setValue_moveSpeed(int value_moveSpeed) {this.value_moveSpeed = value_moveSpeed;}

	//��Ԃ̃Z�b�g
	public Charactor_State getState() {return state;}
	public void setState(Charactor_State state) 
	{
		this.state = state;
		
		//��Ԃɂ���ăe�N�X�`����ς���
		if( state == Charactor_State.WALK_STATE )
		{
			//�e�N�X�`���̃Z�b�g
			SetTexture(walkTexture);			
		}
		else if( state == Charactor_State.ATTACK_STATE )
		{
			//�U�����Z�b�g
			SetTexture(attackBeforeTexture);			
		}
		else if( state == Charactor_State.ATTACK_AFTER_STATE )
		{
			//�U������Z�b�g
			SetTexture(attackAfterTexture);			
		}
		
	}		
	
	
	//�R���X�g���N�^
	public Charactor( int hp , int attack , int speed , int walk_id , int attak_before_id , int attak_after_id )
	{
		//��{���ݒ�
		value_hp = value_maxhp = hp;
		value_attack = attack;
		value_moveSpeed = speed;
		
		//�������
		state = Charactor_State.WALK_STATE;
		
		//�U���Ώ�
		attackTarget = null;
		castleTarget = null;
		
		//�s���A�j���[�V����
		walkTexture = new Texture(walk_id);
		attackBeforeTexture = new Texture(attak_before_id);
		attackAfterTexture = new Texture(attak_after_id);
		
	}
	
	// ����������
	public void Init()
	{
	}	
	
	// �X�V����
	protected abstract boolean IsTarget( Sprite sp );
	protected void move_state()
	{
		//�������ǂ���
		boolean moveflag = true;
		
		//���X�g�̍쐬
		LinkedList<Sprite> list = new LinkedList<Sprite>();
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_ENEMY.getValue() ) )list.add(sp);
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_PLAYER.getValue() ) )list.add(sp);
		
		//�G��������~�܂�
		for( Sprite sp : list )
		{
			if( IsTarget(sp) )
			{
				
				//�����s��������
				if( sp.getTrans().getY() == this.getTrans().getY() )
				{			
					//�����𔻒f���Ď~�܂�B
					if( sp.Collission(this))
					{
						//�ړ������Ȃ�
						moveflag = false;
						
						//�U����Ԃɂ���
						StartAttack( (Charactor)sp );
						
						//�I��
						break;						
					}
				}			
			}
		}
		//�ړ�
		if(moveflag==true)
		{
			//�ڐG���Ă��Ȃ�������^�[�Q�b�g���Ȃ��ɂ���
			attackTarget = null;
			//�ړ���
			move();					
		}
	}
	private void move()
	{
	
		//��ɃA�^�b�N���邩�ǂ��������߂��t���O
		boolean castle_attack_flag = false;
		
		if( getTrans().getY() == Const.LINE_1_Y )
		{
			this.Translate( new Vector3( Const.LINE_1_SPEED*value_moveSpeed ,0,0) );
			
			//���W�K��(��܂Ői��)
			if( getTrans().getX() < Const.LINE_LEFT_1_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_1_X , Const.LINE_1_Y , 0 ) );
				//��t���O�𗧂Ă�
				castle_attack_flag = true;
			}			
			if( getTrans().getX() > Const.LINE_RIGHT_1_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_1_X , Const.LINE_1_Y , 0 ) );
				//��t���O�𗧂Ă�
				castle_attack_flag = true;
			}			
		}
		else if( getTrans().getY() == Const.LINE_2_Y )
		{
			this.Translate( new Vector3( Const.LINE_2_SPEED*value_moveSpeed ,0,0) );
			
			//���W�K��(��܂Ői��)
			if( getTrans().getX() < Const.LINE_LEFT_2_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_2_X , Const.LINE_2_Y , 0 ) );
				//��t���O�𗧂Ă�
				castle_attack_flag = true;
			}			
			if( getTrans().getX() > Const.LINE_RIGHT_2_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_2_X , Const.LINE_2_Y , 0 ) );
				//��t���O�𗧂Ă�
				castle_attack_flag = true;
			}			
	
		}
		else if( getTrans().getY() == Const.LINE_3_Y )
		{
			this.Translate( new Vector3( Const.LINE_3_SPEED*value_moveSpeed ,0,0) );
			
			//���W�K��(��܂Ői��)
			if( getTrans().getX() < Const.LINE_LEFT_3_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_3_X , Const.LINE_3_Y , 0 ) );
				//��t���O�𗧂Ă�
				castle_attack_flag = true;
			}			
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
				if( IsTarget(sprite) )
				{
					//����Z�b�g���čU����Ԃɂ���
					StartAttack( (Castle)sprite );
				}
			}
		}
		else
		{
			//��ԋ�����Ȃ����������U�����Ȃ�
			castleTarget = null;
		}
	}	
	protected void attack_state()
	{	
		//�I����Ă�����U�����Ď���
		if( isEnd )
		{
			ActionAttack();
			setState( Charactor_State.ATTACK_AFTER_STATE );
		}
	}
	protected void attack_after_state()
	{
		//�I����Ă�����U�����Ď���
		if( isEnd )
		{
			//������
			setState( Charactor_State.WALK_STATE );
			
			//�Ώۂ̕ύX
			attackTarget = null;
			castleTarget = null;
		}
	}
	
	@Override
	public boolean Update()
	{
		if(!super.Update())return false;
		
		//���񂾂�^�X�N���������
		if( isDead() )
			return false;	
		
		if( state == Charactor_State.WALK_STATE )
		{
			move_state();
		}
		else if( state == Charactor_State.ATTACK_STATE )
		{
			attack_state();
		}
		else if( state == Charactor_State.ATTACK_AFTER_STATE )
		{
			attack_after_state();
		}
		
		return true;
	}		
	
	//�U���n��
	public void StartAttack( Charactor c )
	{
		attackTarget = c;
		setState(Charactor_State.ATTACK_STATE);
	}
	public void StartAttack( Castle c )
	{
		castleTarget = c;
		setState(Charactor_State.ATTACK_STATE);		
	}
	//�U�����s
	public void ActionAttack()
	{
		//�G���D�揇�ʂ�����
		if( attackTarget != null )
		{
			attackTarget.Damage(this);			
		}
		else if( castleTarget != null )
		{
			castleTarget.Damage(this);
		}
	}
	
	public void Damage( Charactor c )
	{
		value_hp -= c.getValue_attack();	
	}
	public void Damage( int c )
	{
		value_hp -= c;	
	}
	

	//����ł��邩�ǂ���
	boolean isDead()
	{
		return value_hp <= 0;
	}
	
	// ���C���̃Z�b�g
	public void setLineNum( int line )
	{
		lineNum = line;
	}

	// ���C���̔ԍ��擾
	public int getLineNum()
	{
		return lineNum;
	}

}
