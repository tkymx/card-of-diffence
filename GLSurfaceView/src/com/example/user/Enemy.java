package com.example.user;

import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * 生成時に、spriteのメソッドを用いて、画像の追加を行う
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class Enemy extends Charactor {
	
	public Enemy(String name,int hp, int attack, int speed,int wi,int abi,int afi) {
		super(name, hp, attack, speed, wi, abi, afi);
		// TODO Auto-generated constructor stub
	}

	// 初期化処理
	public void Init()
	{
		//プレイヤーのHPバーを取得
		EnemyHPBar.createEnemyHPBar(this);		
	}
	
	@Override
	public void Uninit() {
		super.Uninit();
		
		//スコア追加
		DataBase.addScore(100);
		
	}
	
	@Override
	//ターゲットの判断
	protected boolean IsTarget(Sprite sp) {
		
		if( sp instanceof Player )return true;
		if( sp instanceof PlayerCastle )return true;
		
		return false;
	}		
		
	@Override
	//キャラクタの攻撃を受けた時
	public void Damage( Charactor c )
	{
		super.Damage(c);

		//ダメージエフェクト
		AnimationEffect.Create( 
				this.getTrans().getX() , 
				this.getTrans().getY() + m_height/2  - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());

	}	
	//通常のダメージを受けた時
	@Override	
	public void Damage( int c )
	{
		super.Damage(c);

		//ダメージエフェクト
		AnimationEffect.Create( 
				this.getTrans().getX() + m_width/2  - Const.rx(0.1)/2, 
				this.getTrans().getY() + m_height/2  - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());

	}	
	

}
