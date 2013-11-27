package com.example.user;

import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;

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
		//城のHPを表示
		PlayerCastleHPBar.createPlayerCastleHPBar(this);
	}
	
	// 更新処理
	public boolean Update()
	{
		//最終的には城にも状態を持たせて、城で爆発が起こった後に敗北などの文字が出現し、ゲームが終了する予定
		
		//死んでたら消す
		if( hp <= 0 )
		{
			//ゲームオーバーにする
			DataBase.setResult(true);
			DataBase.setWin(false);
			return false;		
		}
		
		return true;		
	}	
	
	
	//攻撃を受けた時
	public void Damage(Charactor c)
	{
		super.Damage(c);
		
		//ダメージエフェクト
		AnimationEffect.Create( 
				c.getTrans().getX() - Const.rx(0.1), 
				c.getTrans().getY() + c.getM_height()/2 - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());				
	}	
}
