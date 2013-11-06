package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
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
	
	public Castle(int h)
	{
		hp = maxhp = h;
	}
	
	//攻撃を受ける
	public void Damage( Charactor c )
	{
		//体力を減らす
		hp -= c.getValue_attack();		
	}	
}
