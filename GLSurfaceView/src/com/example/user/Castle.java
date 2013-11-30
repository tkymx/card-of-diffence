package com.example.user;

import com.example.glsurfaceview.Sprite;

/**
 * 
 * 城に基本クラス、攻撃を受ける点をまとめておく
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class Castle extends Sprite {

	//攻撃
	protected int hp;
	protected int maxhp;
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public Castle(int h)
	{
		hp = maxhp = h;
	}
	
	//死んでいるかどうか
	boolean isDead()
	{
		return hp <= 0;
	}	
	
	//攻撃を受ける
	public void Damage( Charactor c )
	{
		//体力を減らす
		hp -= c.getValue_attack();		
		
		if( hp < 0 )hp = 0;
	}	
}
