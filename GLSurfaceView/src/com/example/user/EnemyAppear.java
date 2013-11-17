package com.example.user;

import com.example.data.CharactorInfomation;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public class EnemyAppear extends Sprite {
	
	long time;
	
	// 初期化処理
	public void Init()
	{		
		time = 0;
	}
	
	// 更新処理
	public boolean Update()
	{
		if( System.currentTimeMillis() - time >= 10000 )
		{
			//敵キャラを配置
			CreateEnemy( Const.LINE_1_Y , "enemy1" );
			CreateEnemy( Const.LINE_2_Y , "enemy1" );
			CreateEnemy( Const.LINE_3_Y , "enemy1" );					
			
			time = System.currentTimeMillis();
		}
		return true;		
	}
	
	public static void CreateEnemy( int liney , String playername )
	{
		//名前で画像を判断する
		int id = R.drawable.walk_enemy;
		//敵情報
		Enemy enemy = CharactorInfomation.GetEnemyFromName(playername);
		
		if( liney == Const.LINE_1_Y)
		{
			enemy.Init( Const.LINE_RIGHT_1_X  , Const.LINE_1_Y , Const.LINE_1_W , Const.LINE_1_W , 3 , 1 , 5 , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
		}
		else if( liney == Const.LINE_2_Y)
		{
			enemy.Init( Const.LINE_RIGHT_2_X  , Const.LINE_2_Y , Const.LINE_2_W , Const.LINE_2_W , 3 , 1 , 5 , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
		}
		else if( liney == Const.LINE_3_Y)
		{
			enemy.Init( Const.LINE_RIGHT_3_X  , Const.LINE_3_Y , Const.LINE_3_W , Const.LINE_3_W , 3 , 1 , 5 , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
		}
	}	
	
}
