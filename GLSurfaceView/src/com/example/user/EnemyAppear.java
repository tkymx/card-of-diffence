package com.example.user;

import java.util.LinkedList;
import java.util.Random;

import com.example.data.CharactorInfomation;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public class EnemyAppear extends Sprite {
	
	long time;
	private int pline1, pline2, pline3;
	private int eline1, eline2, eline3;
	private int playerLineNum, enemyLineNum;
	private Random rand = new Random(); 
    private int ran; 
	
	// 初期化処理
	public void Init()
	{		
		time = 0;
		ran = rand.nextInt( 4 ); 
	}
	
	// 更新処理
	public boolean UpdateDamy()
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
	
	public boolean Update()
	{	
		if( System.currentTimeMillis() - time >= 6000 )
		{				
			// プレイヤーがどのラインに何体いるか調べる
			serchPlayerLine();
			
			// エネミーがどのラインに何体いるか調べる
			serchEnemyLine();
			
			// プレイヤーより数が少ない時
			if( enemyLineNum <= playerLineNum )
			{
				// 一番プレイヤーの多いラインを出す
				switch( serchPlayerLineMaxNum() )
				{
				case Const.LINE1:
					CreateEnemy( Const.LINE_1_Y , "enemy1" );
					break;
				case Const.LINE2:
					CreateEnemy( Const.LINE_2_Y , "enemy1" );
					break;
				case Const.LINE3:
					CreateEnemy( Const.LINE_3_Y , "enemy1" );
					break;
				}
			}
			
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
			enemy.setLineNum(Const.LINE1);
		}
		else if( liney == Const.LINE_2_Y)
		{
			enemy.Init( Const.LINE_RIGHT_2_X  , Const.LINE_2_Y , Const.LINE_2_W , Const.LINE_2_W , 3 , 1 , 5 , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
			enemy.setLineNum(Const.LINE2);
		}
		else if( liney == Const.LINE_3_Y)
		{
			enemy.Init( Const.LINE_RIGHT_3_X  , Const.LINE_3_Y , Const.LINE_3_W , Const.LINE_3_W , 3 , 1 , 5 , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
			enemy.setLineNum(Const.LINE3);
		}
	}	
	
	// プレイヤーのいるラインを調べる
	private void serchPlayerLine()
	{
		int playerSize = Const.SpriteType.TYPE_PLAYER.getValue();
		LinkedList<Sprite> list = Sprite.spriteList.get(playerSize);
		Player player;
		pline1 = pline2 = pline3 = 0;
		
		// プレイヤーサイズ分ループ
		for( int i = 0; i < Sprite.spriteList.get( playerSize ).size(); i++ )
		{
			player = ( Player )list.get(i);
			
			switch( player.getLineNum() )
			{
			case Const.LINE1:
				pline1++;
				break;
				
			case Const.LINE2:
				pline2++;
				break;
				
			case Const.LINE3:
				pline3++;
				break;
			}
			
			// ラインにいるプレイヤーの数
			playerLineNum = pline1 + pline2 + pline3;
		}
	}
	
	// エネミーのいるラインを調べる
	private void serchEnemyLine()
	{
		int enemySize = Const.SpriteType.TYPE_ENEMY.getValue();
		LinkedList<Sprite> list = Sprite.spriteList.get(enemySize);
		Enemy enemy;
		eline1 = eline2 = eline3 = 0;
		
		// エネミーーサイズ分ループ
		for( int i = 0; i < Sprite.spriteList.get( enemySize ).size(); i++ )
		{
			enemy = ( Enemy )list.get(i);
			
			switch( enemy.getLineNum() )
			{
			case Const.LINE1:
				eline1++;
				break;
				
			case Const.LINE2:
				eline2++;
				break;
				
			case Const.LINE3:
				eline3++;
				break;
			}
			
			// ラインにいるプレイヤーの数
			enemyLineNum = eline1 + eline2 + eline3;
		}
	}

	// プレイヤーの一番多いラインを調べる
	private int serchPlayerLineMaxNum()
	{
		// すべて同じ数のとき
		if( pline1 == pline2 && pline1 == pline3 )
		{
			ran = rand.nextInt(3);
			
			switch( ran )
			{
			case 0:
				return Const.LINE1;
				
			case 1:
				return Const.LINE2;
				
			case 2:
				return Const.LINE3;
			
			default:
				return Const.LINE1;
			}
		}
		// 1が多い時
		else if( pline1 >= pline2 )
		{
			// 1が多い時
			if( pline1 > pline3 )
			{
				// 1と2が同じ時
				if( pline1 == pline2 )
				{
					ran = rand.nextInt(2);
					
					switch(ran)
					{
					case 0:
						return Const.LINE1;
					
					case 1:
						return Const.LINE2;
						
					default:
						return Const.LINE2;
					}
				}
				
				return Const.LINE1;
			}
			else 
			{
				return Const.LINE3;
			}
		}
		else
		{
			// 2が大きいとき
			if( pline2 >= pline3 )
			{
				// 2と3が同じ時
				if( pline2 == pline3 )
				{
					ran = rand.nextInt(2);
					
					switch(ran)
					{
					case 0:
						return Const.LINE2;
					
					case 1:
						return Const.LINE3;
						
					default:
						return Const.LINE3;
					}
				}
				
				return Const.LINE2;
			}
			else
			{
				return Const.LINE3;
			}
		}
	}
	
}
