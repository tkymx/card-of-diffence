package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.MainActivity;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public abstract class Charactor extends Sprite {

	//状態の管理
	enum Charactor_State{ WALK_STATE , ATTACK_STATE };
	
	
	//体力や攻撃力
	int value_hp;
	int value_maxhp;
	int value_attack;

	//状態の管理をする
	Charactor_State state;
	
	//攻撃対象
	Charactor attackTarget;

	//セッターとゲッター
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
	
	//コンストラクタ
	public Charactor( int hp , int attack )
	{
		//基本情報設定
		value_hp = value_maxhp = hp;
		value_attack = attack;
		
		//歩く状態
		state = Charactor_State.WALK_STATE;
		
		//攻撃対象
		attackTarget = null;
	}
	
	// 更新処理
	protected abstract void move_state();
	protected abstract void attack_state();
	public boolean Update()
	{
		//死んだらタスクから消える
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
	
	//攻撃
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

	//死んでいるかどうか
	boolean isDead()
	{
		return value_hp <= 0;
	}
}
