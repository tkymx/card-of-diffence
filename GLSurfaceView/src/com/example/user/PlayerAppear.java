package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public class PlayerAppear extends Sprite {
	
	// èâä˙âªèàóù
	public void Init()
	{
		CreatePlayer( Const.LINE_1_Y , "" );
		CreatePlayer( Const.LINE_2_Y , "" );
		CreatePlayer( Const.LINE_3_Y , "" );
	}
	
	// çXêVèàóù
	public boolean Update()
	{
		return true;		
	}
	
	public static void CreatePlayer( int liney , String playername )
	{
		if( liney == Const.LINE_1_Y)
		{
			new Player().Init( Const.LINE_LEFT_1_X  , Const.LINE_1_Y , Const.LINE_1_W , Const.LINE_1_W , R.drawable.image1 , Const.SpriteType.TYPE_PLAYER.getValue() );			
		}
		else if( liney == Const.LINE_2_Y)
		{
			new Player().Init( Const.LINE_LEFT_2_X  , Const.LINE_2_Y , Const.LINE_2_W , Const.LINE_2_W , R.drawable.image1 , Const.SpriteType.TYPE_PLAYER.getValue() );			
		}
		else if( liney == Const.LINE_3_Y)
		{
			new Player().Init( Const.LINE_LEFT_3_X  , Const.LINE_3_Y , Const.LINE_3_W , Const.LINE_3_W , R.drawable.image1 , Const.SpriteType.TYPE_PLAYER.getValue() );			
		}
	}
	
}
