package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * プレイヤーの城の描画と体力がなくなったら終了　負け
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class PlayerCastle extends Castle {

	public PlayerCastle(int h) {
		super(h);
		// TODO Auto-generated constructor stub
	}

	// 初期化処理
	public void Init()
	{
	}
	
	// 更新処理
	public boolean Update()
	{
		//死んでたら消す
		if( hp <= 0 )return false;		
		
		return true;		
	}	
	
	
	//攻撃を受けた時
	public void Damage(Charactor c)
	{
		super.Damage(c);
		
		//ダメージエフェクト
		AnimationEffect.Create( 
				c.getTrans().getX() - Const.rx(0.05), 
				c.getTrans().getY() + c.getM_height()/2 - Const.rx(0.05)/2, 
				Const.rx(0.05), 
				Const.rx(0.05), 
				3, 1, 10, R.drawable.image1, Const.SpriteType.TYPE_ENEMY.getValue());				
	}	
}
