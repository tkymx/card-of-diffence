package com.example.user;

import com.example.glsurfaceview.Sprite;

/**
 * 
 * ��Ɋ�{�N���X�A�U�����󂯂�_���܂Ƃ߂Ă���
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class Castle extends Sprite {

	//�U��
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
	
	//����ł��邩�ǂ���
	boolean isDead()
	{
		return hp <= 0;
	}	
	
	//�U�����󂯂�
	public void Damage( Charactor c )
	{
		//�̗͂����炷
		hp -= c.getValue_attack();		
		
		if( hp < 0 )hp = 0;
	}	
}
