package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Const.SpriteType;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * 接触した敵に攻撃を与えるダメージを設置する
 * 
 * @author ultra-tkymx
 *
 */

public class DamageTrap extends Trap {

	int damage;
	
	// 生成 これ推奨
	public static DamageTrap CreateDamageTrap( int line, int x )
	{		
		DamageTrap trap = new DamageTrap();
		
		//ラインの選択
		int width = 0;
		int height = 0;
		if(line == Const.LINE_1_Y){width=Const.LINE_1_W;height=Const.LINE_1_W;}
		else if(line == Const.LINE_2_Y){width=Const.LINE_2_W;height=Const.LINE_2_W;}
		else if(line == Const.LINE_3_Y){width=Const.LINE_3_W;height=Const.LINE_3_W;}
		
		//初期化
		trap.Init(x, line, width, height, R.drawable.trap , SpriteType.TYPE_TRAP.getValue());

		return trap;
	}	
	
	//コンストラクタ
	protected DamageTrap(){
		super();
		
		//攻撃力のセット
		damage = 10;
	}
	
	@Override
	public boolean TrapUpdate() {
		
		//敵をループして、そのなかであった敵に攻撃を与える
		//押された時に全体にダメージを与える
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_ENEMY.getValue() ) )
		{
			if( sp instanceof Enemy )
			{
				//敵情報
				Enemy enemy = (Enemy)sp;
				
				//もし接触していたら
				if( enemy.Collission(this) )
				{
					//敵に攻撃
					enemy.Damage( damage );	
					
					//あたったら消す
					return false;
				}				
			}
		}	
		
		return true;
	}

}
