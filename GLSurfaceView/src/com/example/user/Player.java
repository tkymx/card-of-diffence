package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;

public class Player extends Sprite {

	// 初期化処理
	public void Init()
	{
		
	}
	
	// 更新処理
	public boolean Update()
	{

		//歩くかどうか
		boolean moveflag = true;
		//敵がいたら止まる
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_ENEMY.getValue() ) )
		{
			if( sp instanceof Enemy )
			{
				Enemy enemy = (Enemy)sp;
				
				//同じ行だったら
				if( enemy.getTrans().getY() == this.getTrans().getY() )
				{			
					//距離を判断して止まる。
					if( enemy.getTrans().getX() < this.getTrans().getX() + this.GetWidth())
					{
						moveflag = false;
					}
				}			
			}
		}
		//移動
		if(moveflag==true)move();
		
		return true;		
	}	
	
	
	private void move()
	{
		if( getTrans().getY() == Const.LINE_1_Y )
		{
			this.Translate( new Vector3( Const.LINE_1_SPEED ,0,0) );
			
			//座標規制
			if( getTrans().getX() > Const.LINE_RIGHT_1_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_1_X , Const.LINE_1_Y , 0 ) );
			}			
		}
		else if( getTrans().getY() == Const.LINE_2_Y )
		{
			this.Translate( new Vector3( Const.LINE_2_SPEED ,0,0) );
			
			//座標規制
			if( getTrans().getX() > Const.LINE_RIGHT_2_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_2_X , Const.LINE_2_Y , 0 ) );
			}			
		}
		else if( getTrans().getY() == Const.LINE_3_Y )
		{
			this.Translate( new Vector3( Const.LINE_3_SPEED ,0,0) );
			
			//座標規制
			if( getTrans().getX() > Const.LINE_RIGHT_3_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_3_X , Const.LINE_3_Y , 0 ) );
			}			
		}		
	}
	
}
