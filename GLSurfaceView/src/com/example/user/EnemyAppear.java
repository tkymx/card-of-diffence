package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public class EnemyAppear extends Sprite {
	
	// 初期化処理
	public void Init()
	{		
		CreateEnemy( Const.LINE_1_Y , "" );
		CreateEnemy( Const.LINE_2_Y , "" );
		CreateEnemy( Const.LINE_3_Y , "" );
	}
	
	// 更新処理
	public boolean Update()
	{
		return true;		
	}
	
	public static void CreateEnemy( int liney , String playername )
	{
		//名前で画像を判断する
		int id = R.drawable.image4;
		
		if( liney == Const.LINE_1_Y)
		{
			new Enemy().Init( Const.LINE_RIGHT_1_X  , Const.LINE_1_Y , Const.LINE_1_W , Const.LINE_1_W , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
		}
		else if( liney == Const.LINE_2_Y)
		{
			new Enemy().Init( Const.LINE_RIGHT_2_X  , Const.LINE_2_Y , Const.LINE_2_W , Const.LINE_2_W , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
		}
		else if( liney == Const.LINE_3_Y)
		{
			new Enemy().Init( Const.LINE_RIGHT_3_X  , Const.LINE_3_Y , Const.LINE_3_W , Const.LINE_3_W , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
		}
	}	
	
}
