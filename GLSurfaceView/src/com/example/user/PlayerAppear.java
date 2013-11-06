package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Touch;

public class PlayerAppear extends Sprite {
	
	// 初期化処理
	public void Init()
	{
	}
	
	// 更新処理
	public boolean Update()
	{		
		return true;		
	}
	
	public static Player CreatePlayer( int liney , String playername )
	{
		//そのうちに名前で判断したプレイヤーを表示するようにする。
		Player player = new Player(10,1);
		
		if( liney == Const.LINE_1_Y)
		{
			player.Init( Const.LINE_LEFT_1_X  , Const.LINE_1_Y , Const.LINE_1_W , Const.LINE_1_W , R.drawable.image1 , Const.SpriteType.TYPE_PLAYER.getValue() );			
		}
		else if( liney == Const.LINE_2_Y)
		{
			player.Init( Const.LINE_LEFT_2_X  , Const.LINE_2_Y , Const.LINE_2_W , Const.LINE_2_W , R.drawable.image1 , Const.SpriteType.TYPE_PLAYER.getValue() );			
		}
		else if( liney == Const.LINE_3_Y)
		{
			player.Init( Const.LINE_LEFT_3_X  , Const.LINE_3_Y , Const.LINE_3_W , Const.LINE_3_W , R.drawable.image1 , Const.SpriteType.TYPE_PLAYER.getValue() );			
		}
		
		return player;
	}
	
}
