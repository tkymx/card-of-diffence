package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.MainActivity;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public abstract class Charactor extends Sprite {

	//��Ԃ̊Ǘ�
	enum Charactor_State{ WALK_STATE , ATTACK_STATE };
	
	
	//�̗͂�U����
	int value_hp;
	int value_maxhp;
	int value_attack;

	//��Ԃ̊Ǘ�������
	Charactor_State state;
	
	//�U���Ώ�
	Charactor attackTarget;

	//�Z�b�^�[�ƃQ�b�^�[
	public int getValue_hp() {return value_hp;}
	public void setValue_hp(int value_hp) {this.value_hp = value_hp;}
	public int getValue_maxhp() {return value_maxhp;}
	public void setValue_maxhp(int value_maxhp){this.value_maxhp = value_maxhp;}
	public int getValue_attack() {return value_attack;}
	public void setValue_attack(int value_attack) {this.value_attack = value_attack;}	
	public Charactor_State getState() {return state;}
	public void setState(Charactor_State state) {this.state = state;}		
	public Charactor getAttackTarget() {return attackTarget;}
	public void setAttackTarget(Charactor attackTarget) {this.attackTarget = attackTarget;}
	
	//�R���X�g���N�^
	public Charactor( int hp , int attack )
	{
		//��{���ݒ�
		value_hp = value_maxhp = hp;
		value_attack = attack;
		
		//�������
		state = Charactor_State.WALK_STATE;
		
		//�U���Ώ�
		attackTarget = null;
	}
	
	// �X�V����
	protected abstract void move_state();
	protected abstract void attack_state();
	public boolean Update()
	{
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
		
		return true;
	}		
	
	//�U��
	public void StartAttack( Charactor c )
	{
		attackTarget = c;
	}
	public boolean UpdateAttack()
	{
		attackTarget.Damage(this);
		return false;
	}
	
	public void Damage( Charactor c )
	{
		value_hp -= c.getValue_attack();	
	}

	//����ł��邩�ǂ���
	boolean isDead()
	{
		return value_hp <= 0;
	}
}
