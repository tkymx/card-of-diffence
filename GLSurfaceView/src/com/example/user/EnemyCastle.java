package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.SceneManager;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * 敵のしろの描画、体力がなくなったら勝利
 * 
 * @author ultra-tkymx
 *
 */

public class EnemyCastle extends Castle {

	public EnemyCastle(int h) {
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
		//最終的には城にも状態を持たせて、城で爆発が起こった後に敗北などの文字が出現し、ゲームが終了する予定
		
		//死んでたら消す
		if( hp <= 0 )
		{
			//ゲームオーバー画面に移動
			SceneManager.ChangeScene( SceneManager.clearKey );
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
				c.getTrans().getX() + c.getM_width(), 
				c.getTrans().getY() + c.getM_height()/2 - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());		
	}
	
}
