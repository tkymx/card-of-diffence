package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
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
	
	public Castle(int h)
	{
		hp = maxhp = h;
	}
	
	//�U�����󂯂�
	public void Damage( Charactor c )
	{
		//�̗͂����炷
		hp -= c.getValue_attack();		
	}	
}
