package com.example.user;

import java.util.Random;

import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Vector3;

/**
 * 
 * プレイヤーの城の描画と体力がなくなったら終了　負け
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class PlayerCastle extends Castle {

	int time;
	
	Random r = null;	
	
	public PlayerCastle(int h) {
		super(h);
		// TODO Auto-generated constructor stub
		
		time = 0;
		
		//乱数の初期化
		r = new Random(System.currentTimeMillis());
		
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

			if( time >= 100 )
			{
				//ゲームクリアにする
				DataBase.setResult(true);
				DataBase.setWin(false);

				return false;						
			}
			else
			{
				//時間を進める
				time+=1;
				
				//左下の位置
				Vector3 p = new Vector3( getTrans() );
				//サイズ
				Vector3 s = new Vector3( GetWidth() , GetHeight() , 0 );
				
				for(int i = 0 ; i < 1; i ++)
				{
					//乱数でその範囲で座標を決める
					int x = r.nextInt((int)s.getX());
					int y = r.nextInt((int)s.getY());
					
					//ダメージエフェクト
					AnimationEffect.Create( 
							p.getX() + x - Const.rx(0.15)/2,
							p.getY() + y - Const.rx(0.15)/2,
							Const.rx(0.15), 
							Const.rx(0.15), 
							10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());	
					
				}				
				
			}
			
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
